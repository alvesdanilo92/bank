package br.com.bank.authenticator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final UserDetailsService jwtUserDetailsService;
	private final JwtRequestFilter jwtRequestFilter;

	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Value("${jwt.get.token.uri}")
	private String uri;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(jwtUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(uri, "/authenticator/create")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

}
