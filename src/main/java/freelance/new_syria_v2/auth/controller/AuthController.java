package freelance.new_syria_v2.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import freelance.new_syria_v2.auth.dto.LoginDto;
import freelance.new_syria_v2.auth.dto.LoginResponse;
import freelance.new_syria_v2.auth.dto.RegistrationDto;
import freelance.new_syria_v2.auth.jwt.JwtUtils;
import freelance.new_syria_v2.auth.service.LoginService;
import freelance.new_syria_v2.auth.service.RegistrationService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

	private final LoginService loginService;
	
	private final RegistrationService registrationService;
	
	public AuthController(LoginService loginService, RegistrationService registrationService) {
		super();
		this.loginService = loginService;
		this.registrationService = registrationService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody()LoginDto dto) {
		
		return new ResponseEntity<LoginResponse>(this.loginService.login(dto),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/register")
	public String register(@RequestBody()RegistrationDto dto) {
		return this.registrationService.register(dto);
	}
	
}
