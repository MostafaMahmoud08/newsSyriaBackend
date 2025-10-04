package freelance.new_syria_v2.article.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.SectionDto;
import freelance.new_syria_v2.article.entity.Section;
import freelance.new_syria_v2.article.service.SectionService;
import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/sections")
@AllArgsConstructor
@CrossOrigin("*")
public class SectionController {

	private final SectionService service;

	@PostMapping("{articleId}")
	public ResponseEntity<String> save(@RequestPart() SectionDto dto, @RequestPart() MultipartFile file,
			@PathVariable("articleId") UUID articleId) {
		String res = this.service.save(dto, file, articleId);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@GetMapping()
	public ResponseEntity<List<Section>> findAll() {
		List<Section> all = this.service.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}

}
