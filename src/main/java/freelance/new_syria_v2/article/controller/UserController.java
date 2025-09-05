package freelance.new_syria_v2.article.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

	private final CustomUserDetailsService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<User>findUser(@PathVariable("id")String id){
		User user = this.userService.findUser(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(user);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>>findUsers(){
		List<User> users = this.userService.findAll();
		if(users==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(users);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id")String id){
		this.userService.deleteUser(id);
		return ResponseEntity.ok("user deleted succefully");
	}
	@PostMapping("/{id}/complete-profile")
	public ResponseEntity<User>completeProfile(@RequestPart("user")CompleteProfileDto user
			,@RequestPart("file")MultipartFile file,@PathVariable("id")String id){
			System.out.println("country name : "+user.getCountryName());
			User completedProfileUser = this.userService.completeProfile(id,user,file);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(completedProfileUser);
	}
}
