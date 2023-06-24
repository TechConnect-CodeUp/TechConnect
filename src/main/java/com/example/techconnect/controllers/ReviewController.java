//package com.example.techconnect.controllers;
//
//import com.example.techconnect.models.Attendee;
//import com.example.techconnect.models.Event;
//import com.example.techconnect.repositories.EventRepository;
//import com.example.techconnect.repositories.ReviewRepository;
//import com.example.techconnect.repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.Collections;
//
//@Controller
//public class ReviewController {
//
//    private final EventRepository eventRepository;
//
//    private final ReviewRepository reviewRepository;
//
//    private final UserRepository userRepository;
//
//
//    public ReviewController(EventRepository eventRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
//        this.eventRepository = eventRepository;
//        this.userRepository = userRepository;
//        this.reviewRepository = reviewRepository;
//
//    }
//
//
//    @GetMapping("/reviews/view/{id}")
//
//    public String showEventReview(@PathVariable long id, Model model) {
//
//        model.addAttribute("event", eventRepository.findById(id));
//
//        model.addAttribute("reviews", reviewRepository.findAllByEventEventId(id));
//
//        return "event-reviews";
//
//
//    }
//
//
//}
//
