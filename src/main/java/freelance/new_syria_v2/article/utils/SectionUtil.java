package freelance.new_syria_v2.article.utils;

import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Section;
import freelance.new_syria_v2.article.service.SectionService.SectionDto;

public class SectionUtil {

	public static Section from(SectionDto dto, Article article, String imageUrl) {
		Section section = new Section();
		section.setHeader(dto.header());
		section.setContent(dto.content());
		section.setImageUrl(imageUrl);
		section.setArticle(article);
		return section;
	}
}
