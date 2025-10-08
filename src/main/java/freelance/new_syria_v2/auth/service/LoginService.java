package freelance.new_syria_v2.auth.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.dto.LoginDto;
import freelance.new_syria_v2.auth.dto.LoginResponse;
import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginService {
	private final CustomUserDetailsService userService;
	private final PasswordEncoder encoder;
	private final TokenService tokenService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	public LoginResponse login(LoginDto dto) {

		boolean present = this.userService.isPresent(dto.getEmail());
		if (present) {
			CustomUserDetails userDetails = (CustomUserDetails) this.userService.loadUserByUsername(dto.getEmail());
			// check password
			if (!encoder.matches(dto.getPassword(), userDetails.getPassword())) {
				LOGGER.warn("Invalid login attempt for email: {}", dto.getEmail());
				throw new BadCredentialsException("Invalid email or password");
			}

			// check account status
			if (!userDetails.isEnabled()) {
				LOGGER.error("User {} tried to login but is not enabled. Check your email.", userDetails.getUsername());
				throw new BadCredentialsException("Your account is not enabled. Please check your email.");
			}

			Optional<Token> token = this.tokenService.findLatestTokenByEmail(dto.getEmail());
			if (token.isEmpty()) {
				User user = this.userService.findOptionalByEmail(userDetails.getUsername());
				this.tokenService.save(user);
			}
			var res = new LoginResponse().from(token.get().getToken(), userDetails.getAuthorities());
			res.setUserId(userDetails.getUser().getId());
			res.setEmail(userDetails.getUser().getEmail());
			return res;
		}
		throw new NotFoundException("email you try to login with is not present");
	}
}
