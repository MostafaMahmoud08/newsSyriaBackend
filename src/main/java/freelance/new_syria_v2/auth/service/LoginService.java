package freelance.new_syria_v2.auth.service;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;
import javax.security.auth.login.CredentialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER=LoggerFactory.getLogger(LoginService.class);
	
	public LoginResponse login(LoginDto dto)  {
		UserDetails user = this.userService.loadUserByUsername(dto.getEmail());
		String password = dto.getPassword();
		if(encoder.matches(password, user.getPassword())) {
			
			String generatedToken = this.utils.generateToken(user);
			User savedUser = userService.findByEmail(user.getUsername());
			Token token=new Token();
			token.setUser(savedUser);
			token.setToken(generatedToken);
			token.setCreatedAt(LocalDateTime.now());
			token.setExpiredAt(LocalDateTime.now().plusMinutes(15));
			
			this.tokenRepo.save(token);
			
			return LoginResponse.from(token, savedUser);
		}
		return null;
	}
}
