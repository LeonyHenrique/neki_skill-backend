package com.neki_skill.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@Component 
public class SecurityConfig {


	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {		
	    return httpSecurity.csrf(csrf -> csrf.disable())	    		
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(authorize -> authorize
	            		
	                    .requestMatchers("/h2-console/**").permitAll()
	                    .requestMatchers("/v3/api-docs/**").permitAll()
	                    .requestMatchers("/swagger-ui/index.html").permitAll()
	                    .requestMatchers("/swagger-ui/**").permitAll()
	                    .requestMatchers("/swagger-ui.html").permitAll()
	                    .requestMatchers("/skills/{id}/foto").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/**").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
	                    .requestMatchers(HttpMethod.POST, "/skills/cadastrar").hasRole("ADMIN")
	                    .anyRequest().authenticated())
	            .cors().and()
	            .headers(headers -> headers.frameOptions().sameOrigin())
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
