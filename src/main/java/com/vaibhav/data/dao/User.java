package com.vaibhav.data.dao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  	@Id
    private String id;
	private String Name;
	private String Role;
	private String EmailId;
	private String Mobile;
    private String username;
    private String password; // Ideally, this should be hashed

    
}
