package freelance.new_syria_v2.auth.jwt;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import freelance.new_syria_v2.auth.entity.User;
import freelance.new_syria_v2.auth.service.CustomUserDetails;
import freelance.new_syria_v2.auth.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

	private final JwtUtils jwtUtils;
	private final CustomUserDetailsService userDetailsService;

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		LOGGER.debug("Authorization request {}", request.getRequestURI());

		try {
			String jwt = parseToken(request);
			LOGGER.debug("Token returned from client: {}", jwt);

			if (jwt != null && jwtUtils.validateToken(jwt)) {
				String email = jwtUtils.getEmailFromToken(jwt);

				User userEntity = userDetailsService.findOptionalByEmail(email);
				CustomUserDetails userDetails = new CustomUserDetails(userEntity);

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContext context = SecurityContextHolder.createEmptyContext();
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);

				LOGGER.debug("✅ User authenticated: {}", userDetails.getUsername());
			}

		} catch (Exception e) {
			LOGGER.error("Cannot authenticate user: {}", e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private String parseToken(HttpServletRequest request) {
		return this.jwtUtils.getJwtFromHeader(request);
	}
}
