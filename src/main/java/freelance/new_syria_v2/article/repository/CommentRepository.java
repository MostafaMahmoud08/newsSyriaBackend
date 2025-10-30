package freelance.new_syria_v2.article.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.article.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
	Page<Comment> findByArticleId(UUID articleId, Pageable pageable);

}
