package freelance.new_syria_v2.article.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.ArticleDto;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Status;
import freelance.new_syria_v2.article.service.ArticleService;
import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/articles")
@AllArgsConstructor
@CrossOrigin("*")
public class ArticleController {

	private final ArticleService service;

	// make an article for user or admin
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<String> save(@ModelAttribute("dto") ArticleDto dto, @RequestPart("file") MultipartFile file) {
		System.out.println(dto.toString());
		String res = this.service.save(dto, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	// admin can review the post and make it approved or rejected
	@PutMapping("/{id}/review")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> reviewPosts(@PathVariable("id") UUID id, @RequestParam("status") boolean status) {
		String res = this.service.reviewPosts(id, status);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	// for the admin to show what he approved and what no
	@GetMapping("/status")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<Article>> findAllByStatus(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "sort", defaultValue = "desc", required = false) String sort) {
		Status statusOfArticle = Status.from(status);
		Page<Article> articles = this.service.findByStatus(statusOfArticle, page, size, sort);
		return ResponseEntity.ok(articles);
	}

//	@GetMapping()
//	public ResponseEntity<List<Article>> findAll(
//			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
//			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
//			@RequestParam(name = "status",required = false) String status,
//			@RequestParam(name = "sort", defaultValue = "desc",required =  false) String sort) {
//		Status statusOfArticle = Status.from(status);
//		List<Article> articles = this.service.findAll();
//		return ResponseEntity.ok(articles);
//	}
//	
	// find an article by id
	@GetMapping("{id}")
	public ResponseEntity<Article> findArticleById(@PathVariable("id") UUID id) {
		Article article = this.service.findById(id);
		return ResponseEntity.ok(article);
	}

	// find articles by category
	@GetMapping("category")
	public ResponseEntity<Page<Article>> findArticlesByCategory(@RequestParam("category") String categoryName,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size) {
		return ResponseEntity.ok(this.service.findArticlesByCategory(categoryName, page, size));
	}

}
