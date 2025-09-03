package freelance.new_syria_v2.auth.dto;

import freelance.new_syria_v2.auth.entity.Role;
import freelance.new_syria_v2.auth.entity.Token;
import freelance.new_syria_v2.auth.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String token;
	
	private Role role;
	
	public static LoginResponse from(Token token,User user) {
		LoginResponse response=new LoginResponse();
		response.setToken(token.getToken());
		response.setRole(user.getRole());
		return  response;
	}
}
