package freelance.new_syria_v2.auth.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> isExisted = this.userRepository.findByEmail(email);

        if (isExisted.isPresent()) {
            User user = isExisted.get();
            LOGGER.debug("The user with email {} fetched from db", email);
            
            return new CustomUserDetails(user);
        }

        LOGGER.error("The email {} you tried to fetch is not present in db", email);
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
    
    @Transactional
    public User save(User user) {
    	return this.userRepository.save(user);
    }
    
    public Optional<User> findOptionalByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    
}
