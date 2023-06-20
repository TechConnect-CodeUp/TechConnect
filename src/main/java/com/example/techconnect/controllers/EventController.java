package com.example.techconnect.controllers;

import com.example.techconnect.models.Event;
import com.example.techconnect.models.Interest;
import com.example.techconnect.models.User;
import com.example.techconnect.repositories.EventRepository;
import com.example.techconnect.repositories.UserRepository;
import com.example.techconnect.utilities.AddressUtility;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventRepository eventRepository;

    private final AddressUtility addressUtility;

    private final UserRepository userRepository;


    public EventController(EventRepository eventRepository, AddressUtility addressUtility, UserRepository userRepository) {

        this.eventRepository = eventRepository;
        this.addressUtility = addressUtility;
        this.userRepository = userRepository;


    }


    // I want to login to the website

    // The page should be displayed to the user

    // We need the user's session key from when they login


    // I want to click a button that creates an event


    // We need a form that has a POST method
    // @Get will simply render the page

    // Post method grabs the registration information

    // Use the Address utility method to store Street, city, state information

    // Creates a new Event Object

    // Saves it to the DB

//    <!--The naming convention has been changed from /event to /event/create-->

    @GetMapping("/event/create")
    public String showEventForm(Model model) {


        model.addAttribute("event", new Event());

        return "event";
    }

    @PostMapping("/event/create")

    public String createEvent(@ModelAttribute Event event) {

        // This method must also save the hostid and the interest id once they have logged in;

        // This piece of code allows us to access the authenticated User;

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // We are setting the whole User Object to the Event table and JPA will only save the Id in the whole database

        event.setHost(loggedIn);



        event.setInterest();


        eventRepository.save(event);

        return "event";

        // The user should be redirected to their profile page so that the they can view their newly created event


    }


}
