package freelance.new_syria_v2.article.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Status;

public interface ArticleRepository extends JpaRepository<Article, String>{

	//find aricles by status
	@Query("select a from Article a where a.status=:status order by a.createdAt")
	Page<Article>findByStatus(Status status,Pageable pageable);
	
	//find Articles related to one author
	@Query("select a from Article a where a.author.email=:email order by a.createdAt")
	Page<Article>findByAuthorEmail(String email,Pageable pageable);
	
	@Query("select a.thumbnail.id from Article a where a.id=:id")
	String findThumbnailId(String id);
}
