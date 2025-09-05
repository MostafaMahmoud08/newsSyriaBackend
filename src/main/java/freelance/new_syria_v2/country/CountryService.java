package freelance.new_syria_v2.country;

import java.util.List;

import org.springframework.stereotype.Service;

import freelance.new_syria_v2.country.entity.Country;
import freelance.new_syria_v2.country.repository.CountryRepo;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {

	private final CountryRepo repository;
	
	public Country findByCountryName(String countryName) {
		System.out.println(countryName);
		return this.repository.findByCountryName(countryName).orElseThrow(()->new NotFoundException("country with this name is not found"));
	}

	public List<Country> findAll() {
		return this.repository.findAll();
	}
}
