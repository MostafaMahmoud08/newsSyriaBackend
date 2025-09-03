package freelance.new_syria_v2.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.dto.RegistrationDto;
import freelance.new_syria_v2.auth.entity.User;
import lombok.AllArgsConstructor;

@Service
public class RegistrationService {

	private final CustomUserDetailsService userService;
	private final PasswordEncoder encoder;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(RegistrationService.class);
			
	public RegistrationService(CustomUserDetailsService userService,PasswordEncoder encoder) {
		super();
		this.userService = userService;
		this.encoder=encoder;
	}



	//function to take registerDto convert it into user entity in db
	public String register(RegistrationDto registerDto) {
		//create the user entity
		User user=new User();
		user.setEmail(registerDto.getEmail());
		user.setPassword(encoder.encode(registerDto.getPassword()));
		user.setUserName(registerDto.getUserName());
		User isExisting = this.userService.findByEmail(user.getEmail());
		
		if(isExisting !=null) {
			return "User with this "+user.getEmail()+" already in new_syria";
		}
		User savedUser = this.userService.save(user);
		
		LOGGER.debug("USER WITH THIS EMAIL IS SAVED IN DB {}",savedUser.getEmail());
		
		return savedUser.getEmail();
	}
	
}
