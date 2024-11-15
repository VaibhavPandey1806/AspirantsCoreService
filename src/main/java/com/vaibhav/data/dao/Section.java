package com.vaibhav.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    private String id; // MongoDB ID for the section
    private String name; // Name of the section
    private String details; // Additional details about the section

    // Getters and Setters (optional if using Lombok @Data)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
