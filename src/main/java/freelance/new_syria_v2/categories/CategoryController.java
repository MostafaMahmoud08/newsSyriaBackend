package freelance.new_syria_v2.categories;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freelance.new_syria_v2.categories.dto.CreateCategoryDto;
import freelance.new_syria_v2.categories.service.CategoryService;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

	private final CategoryService service;
	
	@PostMapping
	public ResponseEntity<String>create(@RequestBody()CreateCategoryDto dto){
		String type = this.service.save(dto);
		return new ResponseEntity<String>(type,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CreateCategoryDto>> findAll(){
		List<CreateCategoryDto> allCategories = this.service.findAll();
	    if (allCategories.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList()); 
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(allCategories);
	}
}
