package freelance.new_syria_v2.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RegistrationDto {
	@NotBlank()
	@Size(min = 3,max = 40)
    @NotBlank(message = "userName is required")
	private String userName;
	
	@Email
    @NotBlank(message = "Email is required")
	private String email;
	
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
	private String password;
}
