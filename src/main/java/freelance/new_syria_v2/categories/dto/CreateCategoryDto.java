package freelance.new_syria_v2.categories.dto;

import freelance.new_syria_v2.categories.entitiy.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateCategoryDto {
	
	private String name;
	
	private String description;
	
	private CategoryType type;
	
	private String nameAr;
	
	private String descriptionAr;
}
