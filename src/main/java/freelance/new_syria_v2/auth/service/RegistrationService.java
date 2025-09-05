package freelance.new_syria_v2.auth.service;

import java.util.Date;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.dto.RegistrationDto;
import freelance.new_syria_v2.auth.email.BrevoEmailService;
import freelance.new_syria_v2.auth.email.EmailBuilder;
import freelance.new_syria_v2.auth.email.EmailService;
import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.service.TokenService.ReturnFromToken;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final CustomUserDetailsService userService;
    private final PasswordEncoder encoder;
    private final TokenService service;
    private final BrevoEmailService emailService;
    
    @Value("${spring.app.servername}")
    private  String serverLink;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    public String register(RegistrationDto registerDto) {
    	boolean present = this.userService.isPresent(registerDto.getEmail());
    	if(present) {
    		throw new BadCredentialsException("Email you try to register with already in the system");
    	}
    	UserDetails existingUser = userService.loadUserByUsername(registerDto.getEmail());
    	
        // Create new user
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setUserName(registerDto.getUserName());
        User savedUser = userService.save(user);
  
        service.deleteAllUnConfirmedTokens(user);

        //create the token 
        ReturnFromToken response = this.service.save(user);
        
        String url = serverLink + "/auth/confirm?token=" + response.Token();
        //send email to vefy user
		emailService.sendEmail(registerDto.getEmail(),registerDto.getUserName(),"verfecation email",EmailBuilder.buildEmail(registerDto.getUserName(),url) );
        
        LOGGER.info("New user registered with email: {}", savedUser.getEmail());
        return savedUser.getEmail();
    }

    public String confirmEmail(String unConfirmedToken) {
        Token token = this.service.findByToken(unConfirmedToken)
                .orElseThrow(() -> new NotFoundException("Token not found in DB"));

        if (token.getExpiredAt().before(new Date(System.currentTimeMillis()))) {
            throw new IllegalStateException("Token expired");
        }

        if (token.getConfirmedAt() != null) {
            return "Email already confirmed!";
        }

        token.setConfirmedAt(new Date(System.currentTimeMillis()));
       
        User user = token.getUser();
        user.setEnabled(true);
        this.userService.save(user);
     
        token.setUser(user);
        this.service.updateToken(token)
        ;
        return "Email confirmed successfully for user: " + user.getEmail();
    }

}
