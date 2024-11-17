package com.vaibhav.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SpringSecurity {

	@Value("app.url")
	private String appUrl;

	private final UserDetailsService userDetailsService;
	private final HandlerMappingIntrospector introspector;

	@Autowired
	public SpringSecurity(UserDetailsService userDetailsService, HandlerMappingIntrospector introspector) {
		this.userDetailsService = userDetailsService;
		this.introspector = introspector;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(request -> request
						.requestMatchers(new MvcRequestMatcher(introspector, "/public/**")).permitAll()
						.requestMatchers(new MvcRequestMatcher(introspector, "/css/**")).permitAll()
					.anyRequest().authenticated())
				.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/perform-login").defaultSuccessUrl("https://aspirantsclub.netlify.app/").permitAll())

//				.oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("https://aspirantsclub.netlify.app/redirect", true))
				.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
