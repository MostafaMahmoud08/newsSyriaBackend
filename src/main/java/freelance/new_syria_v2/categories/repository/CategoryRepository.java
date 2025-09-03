package freelance.new_syria_v2.categories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.categories.entitiy.Category;
import freelance.new_syria_v2.categories.entitiy.CategoryType;

public interface CategoryRepository extends JpaRepository<Category, String> {

	boolean existsByNameAndType(String name, CategoryType type);

}
