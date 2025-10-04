package freelance.new_syria_v2.categories.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateCategoryDto {

	private String name;

	private String description;

	private String nameAr;

	private String descriptionAr;
}
