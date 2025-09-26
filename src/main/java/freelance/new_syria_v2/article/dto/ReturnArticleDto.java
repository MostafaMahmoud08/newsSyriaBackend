package freelance.new_syria_v2.article.dto;

import java.time.LocalDate;

import freelance.new_syria_v2.article.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnArticleDto {
    	private String id;
	    private String header;
	    private String authorName;
	    private Status status;
	    private LocalDate createdAt;
	    private String thumbnailUrl; 
}
