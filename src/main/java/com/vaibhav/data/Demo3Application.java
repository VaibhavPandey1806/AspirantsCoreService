package com.vaibhav.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.vaibhav.repos")
@ComponentScan(basePackages = "com.vaibhav") 
@ComponentScan(basePackages = "com.vaibhav.data") // Adjust the package path if necessary
// Adjust the package path if necessary

public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}
	 @Bean
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder.build();
	    }



//@Bean
//public CommandLineRunner runner(ChatClient.Builder builder) {
//	return args -> {
//		ChatClient chatClient = builder.build();
//		String response = chatClient.prompt("Tell me a joke").call().content();							
//		System.out.println(response);
//	};
//}
}