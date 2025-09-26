package freelance.new_syria_v2.article.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.SectionDto;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Section;
import freelance.new_syria_v2.article.repository.SectionRepository;
import freelance.new_syria_v2.article.utils.ImageUtil;
import freelance.new_syria_v2.article.utils.SectionUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SectionService {

	private final SectionRepository sectionRepository;
	private final ArticleService articleService;
	
	@Transactional
	public String save(SectionDto dto,
			MultipartFile file,String articleId) {
		//fetch the article that section belong to 
		Article article = this.articleService.findById(articleId); 
		//create the photo and save it in db then put it in section
		Image image = ImageUtil.from(file);
		//put it all intio section shape
		Section section = SectionUtil.from(dto, article, image);
		
		Section savedSection = this.sectionRepository.save(section); 
		return "section with header is saved "+savedSection.getHeader();
	}
	
	public List<Section> findAll(){
			return this.sectionRepository.findAll();	
	}
}
