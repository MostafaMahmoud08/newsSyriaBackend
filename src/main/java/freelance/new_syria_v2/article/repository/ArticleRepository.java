package freelance.new_syria_v2.article.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Status;

public interface ArticleRepository extends JpaRepository<Article, UUID>, JpaSpecificationExecutor<Article> {

	// find aricles by status for the admon
	@Query("select a from Article a where a.status=:status")
	Page<Article> findByStatus(Status status, Pageable pageable);

	Page<Article> findByCategory_NameAndStatus(String name, Status status, Pageable pageable);


}
