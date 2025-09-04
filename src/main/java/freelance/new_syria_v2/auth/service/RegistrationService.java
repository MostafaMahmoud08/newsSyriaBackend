package freelance.new_syria_v2.auth.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.dto.RegistrationDto;
import freelance.new_syria_v2.auth.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final CustomUserDetailsService userService;
    private final PasswordEncoder encoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    public String register(RegistrationDto registerDto) {
        // Check if user already exists
    	Optional<User> existingUser = userService.findOptionalByEmail(registerDto.getEmail());
        
        if (existingUser.isPresent()) {
            LOGGER.warn("Attempted registration with existing email: {}", registerDto.getEmail());
            return "User with email " + registerDto.getEmail() + " already exists.";
        }

        // Create new user
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setUserName(registerDto.getUserName());

        User savedUser = userService.save(user);
        LOGGER.info("New user registered with email: {}", savedUser.getEmail());

        return savedUser.getEmail();
    }
}
