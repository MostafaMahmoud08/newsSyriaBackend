package freelance.new_syria_v2.article.controller;

import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.repository.ImageRepository;
import freelance.new_syria_v2.article.service.ArticleService;
import freelance.new_syria_v2.auth.annotaions.IsPublic;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
@CrossOrigin("*")
public class ImageController {

	private final ImageRepository imageRepository;
	private final ArticleService articleService;

	@GetMapping("/{id}")
    @IsPublic()
	public ResponseEntity<byte[]> getImage(@PathVariable UUID id) {
		Image image = this.imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found"));

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getFileName() + "\"")
				.body(image.getImageData());
	}

}
