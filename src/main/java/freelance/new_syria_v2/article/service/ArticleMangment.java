package freelance.new_syria_v2.article.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Comment;
import freelance.new_syria_v2.article.entity.Status;
import freelance.new_syria_v2.article.repository.CommentRepository;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.service.CustomUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleMangment {
	public record commentDto(String commentContent, Status commentStatus) {
	}

	private final CommentRepository repository;

	private final ArticleService articleService;

	private final CustomUserDetailsService userService;

	@Transactional
	public commentDto createComment(UUID articleId, UUID userId, String commentContent) {
		// Fetch the Article
		Article article = this.articleService.findById(articleId);

		// fetch the user
		User user = userService.findUser(userId);

		Comment comment = new Comment();
		// connect the comment to article
		comment.setArticle(article);
		// connect the user with article
		comment.setUser(user);
		// set time
		comment.setCommentDate(LocalDateTime.now());
		// SET status
		comment.setCommentStatus(Status.PENDING);
		// set content
		comment.setCommentContent(commentContent);
		// create comment entity
		Comment saveComment = this.repository.save(comment);

		return new commentDto(saveComment.getCommentContent(), saveComment.getCommentStatus());
	}

	public Page<Comment> getCommentsByArticle(UUID articleId, int page, int size) {
		// make sure article is present
		articleService.findById(articleId);
		// pagenation
		Pageable pageable = PageRequest.of(page, size, Sort.by("commentDate").descending());
		return repository.findByArticleId(articleId, pageable);
	}
}
