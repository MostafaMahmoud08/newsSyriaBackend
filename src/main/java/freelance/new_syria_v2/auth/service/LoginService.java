package freelance.new_syria_v2.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.dto.LoginDto;
import freelance.new_syria_v2.auth.dto.LoginResponse;
import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.jwt.JwtUtils;
import freelance.new_syria_v2.auth.repository.TokenRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {
    private final CustomUserDetailsService userService;
    private final JwtUtils utils;
    private final PasswordEncoder encoder;
    private final TokenRepo tokenRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    public LoginResponse login(LoginDto dto) {
        // load user
        UserDetails userDetails = this.userService.loadUserByUsername(dto.getEmail());

        // check password
        if (!encoder.matches(dto.getPassword(), userDetails.getPassword())) {
            LOGGER.warn("Invalid login attempt for email: {}", dto.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }

        // generate token
        String generatedToken = this.utils.generateToken(userDetails);

        // fetch actual User entity
        Optional<User> savedUserOpt = userService.findOptionalByEmail(userDetails.getUsername());
        User savedUser = savedUserOpt.orElseThrow(() -> 
            new IllegalStateException("User entity not found for email: " + userDetails.getUsername())
        );

        // build token entity
        Token token = new Token();
        token.setUser(savedUser);
        token.setToken(generatedToken);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiredAt(LocalDateTime.now().plusMinutes(15));

        // save token
        tokenRepo.save(token);

        LOGGER.info("User logged in: {}, token created", savedUser.getEmail());

        // return structured response
        return LoginResponse.from(token, savedUser);
    }
}
