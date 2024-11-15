package com.vaibhav.data.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document(collection = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responses {
	
	 @Id
	private String id;
	private Question question;
	private int time;
	private boolean response;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isResponse() {
		return response;
	}
	public void setResponse(boolean response) {
		this.response = response;
	}
	
	
}
	
	 