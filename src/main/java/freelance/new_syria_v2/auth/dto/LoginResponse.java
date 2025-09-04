package freelance.new_syria_v2.auth.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private List<String> roles;

    public static LoginResponse from(String token, Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = authorities.stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .toList();
        return new LoginResponse(token, roles);
    }
}

