//package com.vaibhav.data;
//
//import java.util.HashMap;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//@Profile({"dev"})
//@EnableWebSecurity
//@Configuration
//
//public class SecurityConfigDev {
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http,
//			HandlerMappingIntrospector requestMappingHandlerMapping) throws Exception {
//		// Create MvcRequestMatcher for specific endpoints
//		MvcRequestMatcher loginMatcher = new MvcRequestMatcher(requestMappingHandlerMapping, "/login");
//		MvcRequestMatcher oauth2Matcher = new MvcRequestMatcher(requestMappingHandlerMapping, "/oauth2/**");
//		http.csrf(csrf -> csrf.disable());
////	    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
////	        .requestMatchers(loginMatcher, oauth2Matcher).permitAll()
////	        .anyRequest().authenticated()
////	    )
////	    .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("http://localhost:3000", true))
////	    .cors(); 
//		return http.build();
//	}
//
//	@RestController
//	@CrossOrigin(origins = "http://localhost:3000")
//	class LoginController {
//
//		@GetMapping("/login")
//		public String loginPage() {
//			return "You need to login to access this page.";
//		}
//
//		@GetMapping("/welcome")
//		public String welcome(Authentication authentication) {
//			OAuth2User user = (OAuth2User) authentication.getPrincipal();
//			return "Welcome, " + user.getAttributes().get("name") + "!";
//		}
//
//		@GetMapping("/api/user")
//		public HashMap getUser(Authentication authentication) {
//			HashMap<String,String> user=new HashMap<String,String>();
//			OAuth2User userDetails=(OAuth2User) authentication.getPrincipal();
//			user.put("name",userDetails.getAttributes().get("name").toString());
//			return user;
//			
//			
//			
//		}
//
//		@GetMapping("/api/logout")
//		public String logout() {
//			return "Logged out successfully.";
//		}
//	}
//}
