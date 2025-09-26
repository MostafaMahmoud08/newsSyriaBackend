package freelance.new_syria_v2.article.dto;

import java.util.List;

import freelance.new_syria_v2.article.entity.Author;
import freelance.new_syria_v2.article.entity.Comment;
import freelance.new_syria_v2.article.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleDtoResponse {

	private String thumball;
	private String header;
	private Status status;
	private List<Comment>comments;
	private Author author;
}
