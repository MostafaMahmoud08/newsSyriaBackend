package freelance.new_syria_v2.article.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Section;
import freelance.new_syria_v2.article.repository.SectionRepository;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.article.utils.SectionUtil;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SectionService {

	// Record for editing a section (used for update operations)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public record SectionDto(UUID id, String header, String imageUrl, String content) {}

	// Dependencies injected via constructor
	private final SectionRepository sectionRepository;
	private final ArticleService articleService;
	private final ImageUtil imageUtil;

	/**
	 * Save a new section to a specific article. Steps: 1. Fetch the article that
	 * the section belongs to. 2. Convert the uploaded file into an Image entity. 3.
	 * Generate an accessible image URL. 4. Convert the SectionDto into a Section
	 * entity. 5. Save the new Section in the database. 6. Return the saved section
	 * as a DTO.
	 */
	@Transactional
	public SectionDto save(SectionDto dto, MultipartFile file, UUID articleId) {
		// Fetch the related article
		Article article = articleService.findById(articleId);

		// Create and store image
		Image image = imageUtil.from(file);

		// Generate public image URL
		String imageUrl = imageUtil.imageUrl(image.getId());

		// Map DTO → Entity
		Section section = SectionUtil.from(dto, article, imageUrl);

		// Save new section
		Section savedSection = sectionRepository.save(section);

		// Return as DTO
		return new SectionDto(savedSection.getId(), savedSection.getHeader(), savedSection.getImageUrl(),
				savedSection.getContent());
	}

	/**
	 * ✅ Retrieve all sections from the database.
	 */
	public List<Section> findAll() {
		return sectionRepository.findAll();
	}

	/**
	 * ✅ Delete a section by its ID. Checks if the section exists before deletion.
	 */
	@Transactional
	public String deleteById(UUID sectionId) {
		if (!sectionRepository.existsById(sectionId)) {
			throw new NotFoundException("Section with ID " + sectionId + " not found");
		}
		sectionRepository.deleteById(sectionId);
		return "Section with ID " + sectionId + " deleted successfully.";
	}

	/**
	 * ✅ Edit an existing section. Updates header, content, and optionally the
	 * image.
	 */
	@Transactional
	public SectionDto editSection(SectionDto sectionDto, MultipartFile file) {
		// Find existing section or throw an exception
		Section section = sectionRepository.findById(sectionDto.id())
				.orElseThrow(() -> new NotFoundException("Section with ID " + sectionDto.id() + " not found"));

		// Update fields
		updateSectionEntity(section, sectionDto, file);

		// Save changes
		Section savedSection = sectionRepository.save(section);

		// Return updated DTO
		return new SectionDto(savedSection.getId(), savedSection.getHeader(), savedSection.getImageUrl(),
				savedSection.getContent());
	}

	/**
	 * ✅ Update an existing Section entity from DTO data. Does not save directly —
	 * only modifies the in-memory entity.
	 */
	private void updateSectionEntity(Section sectionDb, SectionDto dto, MultipartFile file) {
		// Update text fields
		if (dto.header() != null) {
			sectionDb.setHeader(dto.header());
		}
		if (dto.content() != null) {
			sectionDb.setContent(dto.content());
		}
		// Update image only if a new file is provided
		if (file != null && !file.isEmpty()) {
			String imageUrlPre = sectionDb.getImageUrl();
			UUID id = UUID.fromString(imageUrlPre.substring(imageUrlPre.lastIndexOf("/") + 1));
			this.imageUtil.removeImage(id);
			Image image = imageUtil.from(file);
			String imageUrl = imageUtil.imageUrl(image.getId());
			sectionDb.setImageUrl(imageUrl);
		}
	}
}
