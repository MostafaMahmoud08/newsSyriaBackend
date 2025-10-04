package freelance.new_syria_v2.categories.service;

import java.util.List;

import org.springframework.stereotype.Service;

import freelance.new_syria_v2.categories.CategoryUtil;
import freelance.new_syria_v2.categories.dto.CreateCategoryDto;
import freelance.new_syria_v2.categories.entitiy.Category;
import freelance.new_syria_v2.categories.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private final CategoryRepository repository;
	
	@Transactional
	public String save(CreateCategoryDto dto) {		
		if (repository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Category with the same name and type already exists");
        }
		Category category = CategoryUtil.from(dto);
		Category savedCategory = this.repository.save(category);
		return savedCategory.getName();
	}
	public List<CreateCategoryDto> findAll() {
		List<Category> categories = this.repository.findAll();
		List<CreateCategoryDto> dtos= categories.stream().map((category)->CategoryUtil.to(category)).toList();
		return dtos;
	}
}
