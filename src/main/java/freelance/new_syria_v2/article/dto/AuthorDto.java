package freelance.new_syria_v2.article.dto;

import freelance.new_syria_v2.article.entity.Author;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AuthorDto {

	private String userName;
	
	private String email;
	
	private String phoneNumber;
	
	private String countryName;
}
