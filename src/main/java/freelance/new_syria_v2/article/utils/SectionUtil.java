package freelance.new_syria_v2.article.utils;

import freelance.new_syria_v2.article.dto.ArticleDtoResponse;
import freelance.new_syria_v2.article.dto.SectionDto;
import freelance.new_syria_v2.article.entity.Article;
import freelance.new_syria_v2.article.entity.Image;
import freelance.new_syria_v2.article.entity.Section;

public class SectionUtil {

	public static Section from (SectionDto dto,ArticleDtoResponse article,Image image) {
		Article article2=new Article();
		Section section=new Section();
		section.setHeader(dto.getHeader());
		section.setContent(dto.getContent());
		section.setCoverImage(image);
		section.setArticle(article2);
		return section;
	}
}
