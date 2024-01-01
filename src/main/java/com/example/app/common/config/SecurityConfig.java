package com.example.app.common.config;

import com.example.app.common.jwt.JwtTokenFilter;
import com.example.app.user.enums.ERole;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtTokenFilter jwtTokenFilter;

	public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
		this.jwtTokenFilter = jwtTokenFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/api/v1/auth/**").permitAll()
						.requestMatchers("/api/v1/product/all").permitAll()
						.requestMatchers("/api/v1/product/one/**").permitAll()
						.requestMatchers("/v3/api-docs/**").permitAll() //swagger http://localhost:8080/v3/api-docs
						.requestMatchers("/swagger-ui/**").permitAll() //swagger http://localhost:8080/swagger-ui/index.html
						.requestMatchers("/api/v1/user/admin/**").hasAuthority(ERole.ROLE_ADMIN.toString())
						.requestMatchers("/api/v1/product/**").hasAuthority(ERole.ROLE_ADMIN.toString())
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable());
		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.build();
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		return objectMapper;
	}
}
