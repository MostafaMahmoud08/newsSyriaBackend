package freelance.new_syria_v2.auth.service;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import freelance.new_syria_v2.auth.entity.Role;
import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.jwt.JwtUtils;
import freelance.new_syria_v2.auth.jwt.JwtUtils.JwtTokenData;
import freelance.new_syria_v2.auth.repository.TokenRepo;
import freelance.new_syria_v2.exceptions.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {
	public record ReturnFromToken(String Token,Role role) {}
    private final TokenRepo repo;
    private final JwtUtils utils;
    private final CustomUserDetailsService service;

    public ReturnFromToken save(User user) {
    	UserDetails details = this.service.loadUserByUsername(user.getEmail());
        // 1. Generate token string from JwtUtils
    	JwtTokenData tokenJwt = this.utils.generateToken(details);
    	  
        // 3. Build Token entity
        Token token = new Token();
        token.setToken(tokenJwt.token());
        token.setUser(user);
        token.setCreatedAt(tokenJwt.issuedAt());
        token.setExpiredAt(tokenJwt.expiration()); 
        
        Token savedToken = this.repo.save(token);
        // 4. Save to DB
        return new ReturnFromToken(savedToken.getToken(),user.getRole());
    }
    @Transactional
    public void deleteAllUnConfirmedTokens(User user) {
    	this.repo.deleteAllUnConfirmedTokens(user);
    }
    public Optional<Token> findByToken(String token) {
    	return this.repo.findByToken(token);
    }
    public Token updateToken(Token token){
    	return this.repo.save(token);
    }
    public Optional<Token> findLatestTokenByEmail(String email) {
        return repo.findLatestTokenByUserEmail(email);
    }
  
}
