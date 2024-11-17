package com.vaibhav.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.vaibhav.data.dao.User;
import com.vaibhav.repos.UserRepository;


@Service


public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getUser() {
//		HashMap<String,String> user=new HashMap<String,String>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails=(UserDetails) authentication.getPrincipal();
		String user= userDetails.getUsername();

		return userRepository.findByUsername(user);
//		OAuth2User userDetails=(OAuth2User) authentication.getPrincipal();
//		user.put("name",userDetails.getAttributes().get("name").toString());

   }
}
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public User registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
//        return userRepository.save(user);
//    }
//
//    public User findUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//}
