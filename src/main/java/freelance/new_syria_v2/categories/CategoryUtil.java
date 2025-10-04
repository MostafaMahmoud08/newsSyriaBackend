package freelance.new_syria_v2.categories;

import freelance.new_syria_v2.categories.dto.CreateCategoryDto;
import freelance.new_syria_v2.categories.entitiy.Category;

public class CategoryUtil {

	public static Category from(CreateCategoryDto dto) {
			Category category=new Category();
			category.setName(dto.getName());
			category.setDescription(dto.getDescription());
			category.setDescriptionAr(dto.getDescriptionAr());
			category.setNameAr(dto.getNameAr());
			return category;
	}
	public static CreateCategoryDto to (Category category) {
		CreateCategoryDto dto=new 
		CreateCategoryDto(category.getName(),category.getDescription(),category.getNameAr(), category.getDescriptionAr());
		return dto;
	}
}
