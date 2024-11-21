package com.vaibhav.data.controler;


import com.vaibhav.data.dao.User;
import com.vaibhav.data.dto.LoginRequest;
import com.vaibhav.repos.UserRepository;
import com.vaibhav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class publicController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    public publicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//
//            if (authentication.isAuthenticated()) {
//                return "Login successful!";
//            } else {
//                return "Invalid username or password.";
//            }
//        } catch (AuthenticationException e) {
//            return "Invalid username or password.";
//        }
//    }

    @GetMapping("/isLogin")
    public boolean isLogin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal().equals("anonymousUser"));
        return isLoggedIn;
    }

@GetMapping("/getUsers")
public List<User> getUsers(){
        return userRepository.findAll();

}
//    @GetMapping("/login")
//    public ModelAndView login(Model model) {
//        return new ModelAndView("login");  // returns the name of the login view
//    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername())!=null) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        // Check if the email ID already exists
        if (userRepository.findByEmailId(user.getEmailId())!=null&& !userRepository.findByEmailId(user.getEmailId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Email ID is already registered.");
        }

        // Check if the mobile number already exists
        if (userRepository.findbyMobile(user.getMobile())!=null&& !userRepository.findbyMobile(user.getMobile()).isEmpty()) {
            return ResponseEntity.badRequest().body("Mobile number is already registered.");
        }

        // Encode the password and save the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }


    @PostMapping("/checkUsername")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        boolean available;
                if(userRepository.findByUsername(username)!=null||username.equals("anonymousUser"))
                    available=false;
                else {
                    available = true;
                }

        Map<String, Boolean> response = new HashMap<>();
        response.put("available", available);

        return ResponseEntity.ok(response);
    }


}