package freelance.new_syria_v2.article.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.entity.Section;
import freelance.new_syria_v2.article.service.SectionService;
import freelance.new_syria_v2.article.service.SectionService.SectionDto;
import lombok.AllArgsConstructor;

@RestController()
@RequestMapping("/sections")
@AllArgsConstructor
@CrossOrigin("*")
public class SectionController {
	public record CreateSectionDto(String header,String content,MultipartFile file) {}
	private final SectionService service;
	
	@PostMapping("{articleId}")
	public ResponseEntity<SectionDto> save(@ModelAttribute() CreateSectionDto dto,
			@PathVariable("articleId") UUID articleId) {
		SectionDto res = this.service.save(dto, articleId);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@GetMapping()
	public ResponseEntity<List<Section>> findAll() {
		List<Section> all = this.service.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}

	@DeleteMapping("{sectionId}")
	public ResponseEntity<String> deleteSection(@PathVariable("sectionId") UUID sectionId) {
		String res = this.service.deleteById(sectionId);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> editSection(@RequestPart(name = "file", required = false) MultipartFile file,
		    @ModelAttribute SectionDto sectionDto) {
		SectionDto sectionRes = this.service.editSection(sectionDto, file);
		return ResponseEntity.status(HttpStatus.OK).body(sectionRes);
	}

}
