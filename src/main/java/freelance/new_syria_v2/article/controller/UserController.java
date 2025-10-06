package freelance.new_syria_v2.article.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import freelance.new_syria_v2.article.dto.CompleteProfileDto;
import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
	private final CustomUserDetailsService userService;

	@GetMapping("{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") UUID id) {
		User user = this.userService.findUser(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}

	@GetMapping()
	public ResponseEntity<List<User>> findUsers() {
		List<User> users = this.userService.findAll();
		if (users == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(users);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
		this.userService.deleteUser(id);
		return ResponseEntity.ok("user deleted succefully");
	}

	@PostMapping("/{id}/complete-profile")
	public ResponseEntity<String> completeProfile(@ModelAttribute()CompleteProfileDto dto,@PathVariable("id") UUID id) {
		 this.userService.completeProfile(id, dto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("user profile has completed");
	}
}
