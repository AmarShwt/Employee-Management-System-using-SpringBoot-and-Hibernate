package in.employeeManagement.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.employeeManagement.utility.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOGGER.info("Inside JWTRequestFilter class >> doFilterInternal method!");
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		// Removing Bearer from the Token and get only Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			LOGGER.info("Recived Token without Bearer String!");
			jwtToken = requestTokenHeader.substring(7); // Only Token
			try {
				LOGGER.info("Got Username from Token!");
				JwtUtil jwtTokenUtil= JwtUtil.getInstance();
				username = jwtTokenUtil.extractUsername(jwtToken);
			} catch (IllegalArgumentException exp) {
				LOGGER.error("Unable to get JWT Token");
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException exp) {
				LOGGER.error("JWT Token has expired!");
				System.out.println("JWT token has expired!");
			}
		} else {
			LOGGER.error("JWT token does not begin with Bearer!");
			System.out.println("JWT token does not begin with Bearer!");
		}
		
		//Token Validation
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			JwtUtil jwtTokenUtil = JwtUtil.getInstance();
			if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);

	}
}
