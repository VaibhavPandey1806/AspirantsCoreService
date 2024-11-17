package com.vaibhav.data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Allow all paths
				.allowedOrigins("http://192.168.31.38:3000","https://aspirantsclub.netlify.app","https://aspirantsclub.netlify.app","http://192.168.1.2:3000","http://localhost:3000") // Specify your frontend origin
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


