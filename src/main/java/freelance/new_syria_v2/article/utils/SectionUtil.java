package freelance.new_syria_v2.article.utils;

import freelance.new_syria_v2.article.dto.SectionDto;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Section;

public class SectionUtil {

	public static Section from (SectionDto dto,Article article,Image image) {
		Section section=new Section();
		section.setHeader(dto.getHeader());
		section.setContent(dto.getContent());
		section.setCoverImage(image);
		section.setArticle(article);
		return section;
	}
}
