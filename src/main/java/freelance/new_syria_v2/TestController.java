package freelance.new_syria_v2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getHello() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return  "hello";
	}
}
