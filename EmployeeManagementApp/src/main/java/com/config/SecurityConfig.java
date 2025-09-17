package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain security(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.headers(headers -> headers.frameOptions(frame -> frame.disable())) // for H2 console
			.authorizeHttpRequests(auth -> auth
				   .requestMatchers("/api/health", "/h2-console/**", "/api/employees/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				   .anyRequest().permitAll())
			.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
