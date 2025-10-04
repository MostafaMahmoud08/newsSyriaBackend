package freelance.new_syria_v2.categories.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import freelance.new_syria_v2.categories.entitiy.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByName(String name);
	
	@Query("select c from Category c where c.name =:name")
	Optional<Category>findByName(String name);

}
