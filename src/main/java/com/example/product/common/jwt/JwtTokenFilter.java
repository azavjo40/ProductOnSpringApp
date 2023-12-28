package com.example.product.common.jwt;

import com.example.product.user.User;
import com.example.product.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenService jwtTokenService;

	private UserService userService;

	@Autowired
	public JwtTokenFilter(@Lazy UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = jwtTokenService.resolveToken(request);
		if (token != null) {
			if (jwtTokenService.validateToken(token)) {
				Claims claims = jwtTokenService.getBodyOfToken(token);
				String userEmail = claims.getSubject();
				List<String> roles = claims.get("roles", List.class);
				User user = userService.getUserByEmail(userEmail);
				if (user != null && user.getId() != null) {
					authenticate(user, roles);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private void authenticate(User user, List<String> roles) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			System.out.println(role);
			authorities.add(new SimpleGrantedAuthority(role));
		}

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user, null, authorities
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
