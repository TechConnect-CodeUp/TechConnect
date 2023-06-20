package com.example.techconnect.controllers;

import com.example.techconnect.models.User;
import com.example.techconnect.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
    public String registerUser(@ModelAttribute User user, Model model){
        // Hash the password
        String hash = encoder.encode(user.getPassword());
        // Set the hashed password BEFORE saving to the database
        user.setPassword(hash);
        userDao.save(user);

        model.addAttribute("user", user); // Add the user object to the model
        return "redirect:/profile";
    }



    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {

        // Retrieve the user object from the database based on the provided username
        User authenticatedUser = userDao.findByUsername(user.getUsername());
        System.out.println("Username:" + authenticatedUser);
        // Check if the user exists and the password matches
        if (authenticatedUser != null && encoder.matches(user.getPassword(), authenticatedUser.getPassword())) {
            // Authentication successful, set the user attribute in the session
            request.getSession().setAttribute("user", authenticatedUser);
            return "redirect:/profile";
        }

        // if Authentication failed, redirect back to the login page with an error message
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }


    @GetMapping("/profile")
    public String showProfile(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", loggedInUser);
       return "profile";
    }


    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, Model model) {
        // Retrieve the user object from the database based on the provided username
        User authenticatedUser = userDao.findByUsername(user.getUsername());
        // Check if the user exists and the password matches
        if (authenticatedUser!= null && encoder.matches(user.getPassword(), authenticatedUser.getPassword())) {
            // Authentication successful, set the user attribute in the session
            model.addAttribute("user", authenticatedUser);
            return "profile";
        }

        // if Authentication failed, redirect back to the login page with an error message
        return "redirect:/login";

    }

    @GetMapping("/editProfile")
    public String showEditProfileForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", loggedInUser);
        return "editProfile"; // Return the name of the template
    }
    @PostMapping("/editProfile")
    public String editProfile(@ModelAttribute User user, Model model) {
        // Retrieve the currently logged-in user
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User editedUser = userDao.findById(loggedInUser.getId()).get();

        // Update the relevant fields of the logged-in user with the new information

        editedUser.setEmail(user.getEmail());
        editedUser.setFirstName(user.getFirstName());
        editedUser.setLastName(user.getLastName());
        editedUser.setUsername(user.getUsername());


        // Check if the provided password matches the user's current password
        if (encoder.matches(user.getPassword(), editedUser.getPassword())) {
            // Save the updated user to the database
            userDao.save(editedUser);
            model.addAttribute("user", editedUser);
            return "redirect:/profile";
        }

        // If the provided password doesn't match, redirect back to the profile page with an error message
        return "redirect:/profile?error";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfile() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User deletedUser = userDao.findById(loggedInUser.getId()).get();

        // Perform the deletion operation on the user's profile using the userRepository
        userDao.delete(deletedUser);

        // Redirect to a different page after the deletion, e.g., the homepage
        return "redirect:/login";
    }


}
