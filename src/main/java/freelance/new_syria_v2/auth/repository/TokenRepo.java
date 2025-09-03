package freelance.new_syria_v2.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import freelance.new_syria_v2.auth.entity.Token;

public interface TokenRepo extends JpaRepository<Token, String> {

}
