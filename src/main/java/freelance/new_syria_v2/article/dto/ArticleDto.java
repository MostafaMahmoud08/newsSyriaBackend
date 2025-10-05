package freelance.new_syria_v2.article.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArticleDto {

	private String header;

	private String bio;

	private String categoryName;
	
	private MultipartFile file;
}
