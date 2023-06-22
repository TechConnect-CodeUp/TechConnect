package com.example.techconnect.controllers;

import com.example.techconnect.models.Event;

import com.example.techconnect.models.Interest;
import com.example.techconnect.models.User;
import com.example.techconnect.repositories.EventRepository;
import com.example.techconnect.repositories.InterestRepository;
import com.example.techconnect.repositories.UserRepository;
import com.example.techconnect.utilities.AddressUtility;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Controller
public class EventController {

    private final EventRepository eventRepository;

//    private final AddressUtility addressUtility;

    private final UserRepository userRepository;

    private final InterestRepository interestRepository;


    public EventController(EventRepository eventRepository, UserRepository userRepository, InterestRepository interestRepository) {

        this.eventRepository = eventRepository;
//      this.addressUtility = addressUtility;
        this.userRepository = userRepository;
        this.interestRepository = interestRepository;


    }


    @GetMapping("/events.json")
    public @ResponseBody List<Event> viewEventsInJson() {
        return eventRepository.findAll();
    }

    @GetMapping("/events/ajax")
    public String viewAllEventsWithAjax() {
        return "/apitester";
    }


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


        model.addAttribute("interests", interestRepository.findAll());


        model.addAttribute("event", new Event());

        return "/event/create";
    }

    @PostMapping("/event/create")

    public String createEvent(@ModelAttribute Event event) {


        // This piece of code allows us to access the authenticated User;

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // We are setting the whole User Object to the Event table and JPA will only save the Id in the whole database

        event.setHost(loggedIn);


        eventRepository.save(event);


        return "redirect:/profile";


    }


    // Create a method that will edit events

    // ALl new mappings within Controllers need to be added to the Security Configuration Class

    @GetMapping("/event/edit/{id}")
    public String showEditEventPage(@PathVariable long id, Model model) {

        Event event = eventRepository.findById(id).get();
        model.addAttribute("event", event);

        return "event/edit";

    }


    @PostMapping("/event/edit/{id}")
    public String editEvents(@ModelAttribute Event event, @PathVariable long id) {


        // Update the event with the form data

        event.setHost(event.getHost());
        event.setInterest(event.getInterest());
        event.setEventId(id);
        event.setTitle(event.getTitle());
        event.setDateTime(event.getDateTime());
        event.setDescription(event.getDescription());
        event.setLocation(event.getLocation());


        eventRepository.save(event);


        return "redirect:/profile";
    }


    @PostMapping("/event/delete/{id}")

    public String deleteEvent(@ModelAttribute Event event, @PathVariable long id) {

        event.setHost(new User());
        event.setInterest(new Interest());
        eventRepository.deleteById(id);
        return "redirect:/profile";


    }


}
