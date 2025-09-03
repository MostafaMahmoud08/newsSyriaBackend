package freelance.new_syria_v2.auth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import freelance.new_syria_v2.auth.entity.User;

public class CustomUserDetails implements UserDetails {

	private final User user;
		
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	public boolean isEnabled() {
		return user.isEnabled();
	}
	
}
