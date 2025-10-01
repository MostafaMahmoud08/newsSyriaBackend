package freelance.new_syria_v2.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.ArticleDto;
import freelance.new_syria_v2.article.dto.ArticleDtoResponse;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Status;
import freelance.new_syria_v2.article.repository.ArticleRepository;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final ImageUtil imageUtil;

	public Article findById(String id) {
		 Article article = this.articleRepository.findById(id) 
		.orElseThrow(()->new NotFoundException("the article with id "+id+" is not found"));
		 return article;
	} 
	@Transactional
	public String save(ArticleDto dto,MultipartFile file) {
		//get role from context
	    String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
	    
	    //make an image
	    Image image = imageUtil.from(file);
		
	    //make the article
	    Article article=new Article();
		article.setHeader(dto.getHeader());
		 if (role.equals("ROLE_ADMIN")) {
			 article.setStatus(Status.APPROVED);
		  } else if (role.equals("ROLE_USER")) {
	         article.setStatus(Status.PENDING);
		 }
		article.setThumbnail(image);
		
		Article savedArticle = this.articleRepository.save(article);

		return "article with header "+savedArticle.getHeader()+" is created " + savedArticle;
	}	
	@Transactional
	public String reviewPosts(String id, boolean status) {
		Article article = this.articleRepository.findById(id).orElseThrow(()->new NotFoundException("Article with id "+id+" not found"));
		if(status) {
			article.setStatus(Status.APPROVED);
		}else {
			article.setStatus(Status.REJECTED);
		}
		Article savedArticle = this.articleRepository.save(article);
		return savedArticle.getStatus().name();
	}
	
	public Page<Article> findByStatus(Status status, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return this.articleRepository.findByStatus(status, pageable);
	}
	public Page<Article> findAll(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return this.articleRepository.findAll(pageable);
	}
	public String getArticleThumbnail(String id){
		return	this.articleRepository.findThumbnailId(id);
	}
}