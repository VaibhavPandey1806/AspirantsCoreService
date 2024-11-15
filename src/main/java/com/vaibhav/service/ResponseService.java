package com.vaibhav.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.data.dao.Responses;
import com.vaibhav.data.dao.UserResponses;
import com.vaibhav.data.dto.UserResponseDto;
import com.vaibhav.repos.QuestionRepository;
import com.vaibhav.repos.UserResponsesRepository;


@Service
public class ResponseService {
	
	
	@Autowired
	UserResponsesRepository userResponsesRepository;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	QuestionRepository questionRepository;

	public String addResponse(UserResponseDto userResponseDto) {
       if((userResponsesRepository.findByUserid(userService.getUser().getId()))==null||(userResponsesRepository.findByUserid(userService.getUser().getId())).size()==0)
       {
    	   UserResponses temp=new UserResponses();
    	   temp.setUser(userService.getUser());
    	   temp.setUserid(userService.getUser().getId());
    	   Responses res=new Responses();
    	   res.setQuestion(questionRepository.findById(userResponseDto.getQuestionId()).get());
    	   res.setResponse(userResponseDto.isResponse());
    	   res.setTime(userResponseDto.getTimer());
    	   List<Responses> l=new ArrayList<Responses>();
    	   l.add(res);
    	   temp.setResponses(l);
    	   userResponsesRepository.save(temp);
       }
       else
       {
    	   UserResponses temp=userResponsesRepository.findByUserid(userService.getUser().getId()).get(0);
    	   Responses res=new Responses();
    	   res.setQuestion(questionRepository.findById(userResponseDto.getQuestionId()).get());
    	   res.setResponse(userResponseDto.isResponse());
    	   res.setTime(userResponseDto.getTimer());
    	   List<Responses> l=temp.getResponses();
    	   l.add(res);
    	   temp.setResponses(l);
    	   userResponsesRepository.save(temp);
    	   
       }
		
		
		
		return "Success";
	}

	public UserResponses getResponseforUser() 
	{
		
		
		String loggedinUserid=userService.getUser().getId();
		List<UserResponses> userResponses=userResponsesRepository.findByUserid(loggedinUserid);
		if(userResponses.size()==0)
			return new UserResponses();
		
		return userResponses.get(0);
		// TODO Auto-generated method stub
		
	}

}
