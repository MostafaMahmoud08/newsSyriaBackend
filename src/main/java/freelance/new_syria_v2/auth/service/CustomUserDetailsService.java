package freelance.new_syria_v2.auth.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.CompleteProfileDto;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.utils.AuthorUtil;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.repository.UserRepository;
import freelance.new_syria_v2.country.CountryService;
import freelance.new_syria_v2.country.entity.Country;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CountryService countryService;

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
       return null;
    }
    @Transactional
    public User save(User user) {
    	return this.userRepository.save(user);
    }
    
    public Optional<User> findOptionalByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    
    public boolean isPresent(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
    public User findUser(String id) {
    	return this.userRepository.findById(id).orElseThrow(()->new NotFoundException("user with this id not found"));
    }
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	public void deleteUser(String id) {
		 this.userRepository.deleteById(id);
	}
	public User completeProfile(String id, CompleteProfileDto dto, MultipartFile file) {
	    User user = this.findUser(id);
	    Country country = this.countryService.findByCountryName(dto.getCountryName());
	    Image image = ImageUtil.from(file);

	    AuthorUtil.completeProfile(dto, image, user, country);

	    return this.save(user);
	}
    
}
