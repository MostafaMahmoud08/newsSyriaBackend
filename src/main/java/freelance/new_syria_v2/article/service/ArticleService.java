package freelance.new_syria_v2.article.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.controller.ArticleController.ArticleCreated;
import freelance.new_syria_v2.article.dto.ArticleDto;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Status;
import freelance.new_syria_v2.article.repository.ArticleRepository;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.auth.dto.UserDto;
import freelance.new_syria_v2.auth.service.CustomUserDetailsService;
import freelance.new_syria_v2.categories.entitiy.Category;
import freelance.new_syria_v2.categories.repository.CategoryRepository;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final ImageUtil imageUtil;
	private final CategoryRepository categoryRepository;

	public Article findById(UUID id) {
		Article article = this.articleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("the article with id " + id + " is not found"));
		return article;
	}

	@Transactional
	public ArticleCreated save(ArticleDto dto) {
		UserDto user = CustomUserDetailsService.from();

		// make an image
		Image image = imageUtil.from(dto.getFile());

		// make the article
		Article article = new Article();
		article.setHeader(dto.getHeader());
		if (user.getRole().equals("ADMIN")) {
			article.setStatus(Status.APPROVED);
		} else if (user.getRole().equals("USER")) {
			article.setStatus(Status.PENDING);
		}
		article.setImageUrl(imageUtil.imageUrl(image.getId()));

		// get the article category
		Category category = categoryRepository.findByName(dto.getCategoryName())
				.orElseThrow(() -> new IllegalArgumentException("category not found"));
		article.setCategory(category);
		article.setBio(dto.getBio());
		Article savedArticle = this.articleRepository.save(article);

		return new ArticleCreated(savedArticle.getId(), savedArticle.getImageUrl(),
				savedArticle.getCategory().getName(), savedArticle.getBio(), savedArticle.getHeader(),savedArticle.getStatus());
	}

	@Transactional
	public String reviewPosts(UUID id, boolean status) {
		Article article = this.articleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Article with id " + id + " not found"));
		if (status) {
			article.setStatus(Status.APPROVED);
		} else {
			article.setStatus(Status.REJECTED);
		}
		Article savedArticle = this.articleRepository.save(article);
		return savedArticle.getStatus().name();
	}

	@Transactional
	public Page<Article> findByStatus(Status status, int page, int size, String sort) {
		Pageable pageable;
		if ("desc".equalsIgnoreCase(sort)) {
			pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		} else {
			pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
		}
		if (status != null) {
			return this.articleRepository.findByStatus(status, pageable);
		}

		return this.articleRepository.findAll(pageable);

	}

//	@Transactional
//	public List<Article> findAll() {
//		return this.articleRepository.findAllByStatus("news");
//	}

	@Transactional
	public Page<Article> findArticlesByCategory(String categoryName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return articleRepository.findByCategory_NameAndStatus(categoryName, Status.APPROVED, pageable);
	}
}