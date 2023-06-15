package com.example.techconnect.controllers;

import com.example.techconnect.models.User;
import com.example.techconnect.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder encoder;

    public UserController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam("profilePicture") MultipartFile profilePicture, Model model){
        // Save the profile picture if provided
        if (!profilePicture.isEmpty()) {
            // Save the profile picture file and set the path in the user object
            String profilePicturePath = "/Users/coleusher/IdeaProjects/TechConnect/src/main/resources/static/img"; // Replace with your logic to save the file
            user.setProfilePicture(profilePicturePath);
        }

        // Hash the password
        String hash = encoder.encode(user.getPassword());
        // Set the hashed password BEFORE saving to the database
        user.setPassword(hash);
        userDao.save(user);

        model.addAttribute("user", user); // Add the user object to the model
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        // Retrieve the user object from the session
        User user = (User) session.getAttribute("user");

        // Check if the user object is null
        if (user == null) {
            // Handle the case where the user is not logged in
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "profile";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request){
        // Retrieve the user object from the database based on the provided username
        User userFromDb = userDao.findByUsername(user.getUsername());

        // Check if the user exists and compare the passwords
        if (userFromDb != null && encoder.matches(user.getPassword(), userFromDb.getPassword())) {
            // If the passwords match, set the user object in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", userFromDb);
            return "redirect:/profile";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
