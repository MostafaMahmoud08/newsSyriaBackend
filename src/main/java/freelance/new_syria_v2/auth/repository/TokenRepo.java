package freelance.new_syria_v2.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;

public interface TokenRepo extends JpaRepository<Token, String> {
	@Query("select t from Token t where t.token=:token")
	Optional<Token> findByToken(String token);
	
	@Modifying
	@Query("delete from Token t where t.user = :user and t.confirmedAt is null")
	void deleteAllUnConfirmedTokens(@Param("user") User user);
	
	@Query("SELECT t FROM Token t WHERE t.user.email = :email ORDER BY t.createdAt DESC LIMIT 1")
	Optional<Token> findLatestTokenByUserEmail(@Param("email") String email);

}
