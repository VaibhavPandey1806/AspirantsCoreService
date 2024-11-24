package com.vaibhav.data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${frontend.url}")
	private String frontendUrl;
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Allow all paths
				.allowedOrigins("https://aspirantsclubfe-production-3426.up.railway.app","https://aspirantsclub.in",frontendUrl) // Specify your frontend origin
				.allowedMethods("*") // Allowed methods
				.allowedHeaders("*")  // Allow all headers
				.allowCredentials(true); // Allow cookies and auth headers
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:/static/css/");
	}
}


