package freelance.new_syria_v2.article.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.AuthorDto;
import freelance.new_syria_v2.article.entity.Author;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.repository.AuthorRepo;
import freelance.new_syria_v2.article.utils.AuthorUtil;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.exceptions.exception.UserInCompleteException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorService {

	private AuthorRepo repository;
	
	    public Author save(AuthorDto dto, MultipartFile file) {
	        // validate user profile
	        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) {
	            throw new UserInCompleteException("please complete phone number");
	        }
	        if (dto.getCountryName() == null) {
	            throw new UserInCompleteException("please choose your country");
	        }
	        if (file == null || file.isEmpty()) {
	            throw new UserInCompleteException("please upload a profile image");
	        }
	        // make image entity
	        Image image = ImageUtil.from(file);
	        // convert user to author
	        Author author = AuthorUtil.from(dto, image);
	        // save author into db
	        return repository.save(author);
	    }
}

	
	

