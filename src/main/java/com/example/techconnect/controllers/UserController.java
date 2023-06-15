package com.example.techconnect.controllers;

import com.example.techconnect.models.User;
import com.example.techconnect.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registerUser(@ModelAttribute User user){
        // hash the password
        String hash = encoder.encode(user.getPassword());
        // set the hashed password BEFORE saving to the database
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}
