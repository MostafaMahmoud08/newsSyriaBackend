package freelance.new_syria_v2.country;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freelance.new_syria_v2.country.entity.Country;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

	private final CountryService countryService;
	
	@GetMapping
	public ResponseEntity<List<Country>>findAll(){
		List<Country>countries=this.countryService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(countries);
	}
}
