package freelance.new_syria_v2.article.utils;

import freelance.new_syria_v2.article.dto.AuthorDto;
import freelance.new_syria_v2.article.dto.CompleteProfileDto;
import freelance.new_syria_v2.article.entity.Author;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.country.entity.Country;

public class AuthorUtil {

	public static Author from (AuthorDto dto, Image image) {
		Author author=new Author();
		author.setUserName(dto.getUserName());
		author.setEmail(dto.getEmail());
		author.setPhoneNumber(dto.getPhoneNumber());
		author.setCountryName(dto.getCountryName());
		author.setCover(image);
		return author;
}
	public static void completeProfile(CompleteProfileDto dto, Image image, User inDb, Country country) {
	    inDb.setCountry(country);
	    inDb.setPhoneNumber(dto.getPhoneNumber());
	    inDb.setCoverImage(image);

	    boolean isComplete = inDb.getCountry() != null
	            && inDb.getPhoneNumber() != null
	            && inDb.getCoverImage() != null;

	    inDb.setCompletedProfile(isComplete);
	}

}
