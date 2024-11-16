package com.vaibhav.data.controler;


import com.vaibhav.data.dao.User;
import com.vaibhav.repos.UserRepository;
import com.vaibhav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    UserDetailsService userDetailsService;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    public publicController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User login (@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername())!=null)
        {
            UserDetails a =userDetailsService.loadUserByUsername(user.getUsername());
            return userRepository.findByUsername(a.getUsername());
        }
        else
            return null;
    }

    @GetMapping("/isLogin")
    public boolean isLogin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isLoggedIn = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal().equals("anonymousUser"));
        return isLoggedIn;
    }


//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login.jsp";  // returns the name of the login view
//    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  userRepository.save(user);
    }


}