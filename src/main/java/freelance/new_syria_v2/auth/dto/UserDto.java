package freelance.new_syria_v2.auth.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

	private UUID id;
	private String email;
	private String role;
	private String userName;
}
