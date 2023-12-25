package com.example.demo.auth.jwt;

import com.example.demo.student.Student;
import com.example.demo.student.StudentsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private StudentsService studentsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = jwtTokenService.resolveToken(request);
		if (token != null) {
			String studentEmail = jwtTokenService.getSubjectFromToken(token);
			if (jwtTokenService.validateToken(token)) {
				Student student = studentsService.getStudentByEmail(studentEmail);
				if (student != null && student.getId() != null) {
					authenticate(student);
				}
			}
		}

		chain.doFilter(request, response);
	}

	private void authenticate(Student student) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(student, null, emptyList());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
