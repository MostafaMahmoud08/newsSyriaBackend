package freelance.new_syria_v2.country.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import freelance.new_syria_v2.country.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Long> {

	@Query("select c from Country c where c.countryName=:countryName")
	Optional<Country>findByCountryName(String countryName);
}
