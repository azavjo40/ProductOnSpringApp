package com.example.demo.common.jwt;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Collections.emptyList;

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
			String userEmail = jwtTokenService.getSubjectFromToken(token);
			if (jwtTokenService.validateToken(token)) {
				User user = userService.getUserByEmail(userEmail);
				if (user != null && user.getId() != null) {
					authenticate(user);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private void authenticate(User user) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, emptyList());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
