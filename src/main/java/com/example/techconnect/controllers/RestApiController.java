package com.example.techconnect.controllers;
import com.example.techconnect.models.User;
import com.example.techconnect.models.Event;
import com.example.techconnect.repositories.EventRepository;
import com.example.techconnect.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/events",headers = "Accept=application/json")
public class RestApiController {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RestApiController(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/userEvents")
    public List <Event> userEvents(){
        return eventRepository.findAll();
    }




    @GetMapping("/profEvents")
    public List <Event> findUserEvents(){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return eventRepository.findAllByHostId(loggedInUser.getId());
    }


//    @GetMapping("/eventsSearch")
//    public String findEventsBySearch(){
//        return "../apitester";
//    }

//    @PostMapping("/eventsSearch")
//    public List <Event> findEventByLocation(@RequestParam(name = "location")String location){
//        return eventRepository.findEventByLocation(location);
//    }




//    @PostMapping("/eventsSearchKeyword")
//    public List <Event> findEventsByKeyWord(@RequestParam(name = "keyword") String keyword){
//        return eventRepository.findEventByTitleContainingIgnoreCase(keyword);
//    }


    @GetMapping("/events.json")
    public @ResponseBody List<Event> viewEventsInJson() {
        return eventRepository.findAll();
    }

    @GetMapping("/events/ajax")
    public String viewAllEventsWithAjax() {
        return "/apitester";
    }


    @GetMapping("events/eventsSearch")
    public String findEventsBySearch(){
        return "/apitester";
    }

    @PostMapping("events/eventsSearch")
    public List <Event> findEventByLocation(@RequestParam(name = "location")String location){
        return eventRepository.findEventByLocation(location);
    }








}
