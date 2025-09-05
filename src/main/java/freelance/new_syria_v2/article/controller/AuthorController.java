package freelance.new_syria_v2.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.AuthorDto;
import freelance.new_syria_v2.article.entity.Author;
import freelance.new_syria_v2.article.service.AuthorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
	
	private AuthorService authorService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Author>save(@RequestPart("user")AuthorDto dto,
			@RequestPart("file")MultipartFile file){
			Author author = authorService.save(dto,file);
			return ResponseEntity.status(HttpStatus.CREATED).body(author) ;
		
	}
}
