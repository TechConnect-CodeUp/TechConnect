package com.example.techconnect.controllers;

import com.example.techconnect.models.Event;
import com.example.techconnect.repositories.EventRepository;
import com.example.techconnect.utilities.AddressUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {

    private final EventRepository eventRepository;

    private final AddressUtility addressUtility;



    public EventController(EventRepository eventRepository,AddressUtility addressUtility) {

        this.eventRepository = eventRepository;
        this.addressUtility = addressUtility;



    }


    // I want to login to the website

    // The page should be displayed to the user

    @GetMapping("/event")
    public String showEventForm(Model model) {

        model.addAttribute("event", new Event());

        return "event";
    }

    @PostMapping("/event")

    public String createEvent(@ModelAttribute Event event){

        eventRepository.save(event);

        return "event";




    }








    // We need the user's session key from when they login


    // I want to click a button that creates an event


    // We need a form that has a POST method
    // @Get will simply render the page

    // Post method grabs the registration information

    // Use the Address utility method to store Street, city, state information

    // Creates a new Event Object

    // Saves it to the DB
}