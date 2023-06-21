package com.example.techconnect.controllers;

import com.example.techconnect.models.Event;

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


    // I want to login to the website

    // The page should be displayed to the user


//    @GetMapping("/event/create")
//    public String showEventForm(Model model) {
//
//        model.addAttribute("event", new Event());
//
//        return "/event/create";
//    }
//
//    @PostMapping("/event/create")
//
//    public String createEvent(@ModelAttribute Event event) {
//
//        eventRepository.save(event);
//
//        return "/event/create";
//
//
//    }

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

        // This will display the list of all the interest in our database


        model.addAttribute("interests", interestRepository.findAll());


        model.addAttribute("event", new Event());

        return "/event/create";
    }

    @PostMapping("/event/create")

    public String createEvent(@ModelAttribute Event event) {

        // This method must also save the hostid and the interest id once they have logged in;

        // This piece of code allows us to access the authenticated User;

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // We are setting the whole User Object to the Event table and JPA will only save the Id in the whole database

        event.setHost(loggedIn);


        eventRepository.save(event);

        return "redirect:/profile";
        // The user should be redirected to their profile page so that the they can view their newly created event


    }

    // Create a method that allows the user to edit the events that they created

//    @PostMapping("/edit")
//    public String editEvents(@ModelAttribute Event event) {
//
//
//
//
//
//        // Update the event with the form data
//        event.setTitle(eventForm.getTitle());
//        event.setDescription(eventForm.getDescription());
//        event.setDate(eventForm.getDate());
//
//        eventService.saveEvent(event);
//
//        return "redirect:/events/" + event.getId();
//    }

    // Create a method that will edit events

    // ALl new mappings within Controllers need to be added to the Security CConfiguration Class

    @GetMapping("/event/edit/{id}")
    public String showEditEventPage(@PathVariable long id, Model model) {

        Event event  = eventRepository.findById(id).get();
        System.out.println(event.getEventId());
        model.addAttribute("event",event);

        return "event/edit";

    }





}
