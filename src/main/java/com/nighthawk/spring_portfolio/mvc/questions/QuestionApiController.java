package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/mcq")
public class QuestionApiController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private QuestionJpa repository;

    private String getUserName() {
        // gets username from authentication, used to attribute objects to users
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }
    
    @GetMapping("/get/")
    public ResponseEntity<List<QuestionObject>> getQuestionObjects() {
        // Get user's objects by userid
        return new ResponseEntity<>(repository.findByowner(getUserName()), HttpStatus.OK);
    }

    // POST method to create object
    @PostMapping("/create") 
    public ResponseEntity<List<QuestionObject>> createQuestionObject(@RequestParam("question") String question, 
        @RequestParam("optionA") String optionA, 
        @RequestParam("optionB") String optionB, 
        @RequestParam("optionC") String optionC, 
        @RequestParam("optionD") String optionD, 
        @RequestParam("answer") char answer) {
        // Create new object and save to repo
        String username = getUserName();
        QuestionObject a = new QuestionObject(question, optionA, optionB, optionC, optionD, answer);
        repository.save(a);
        return new ResponseEntity<>(repository.findByowner(username), HttpStatus.OK);
    }
    

    // @GetMapping("/scrub/{objectID}")
    // public ResponseEntity<QuestionObject> scrub(@PathVariable int objectID) {
    //     QuestionObject a = repository.findById(objectID).get();
    //     // Check if owner matches
    //     if (!(a.getOwner().equals(getUserName()))) {
    //         return new ResponseEntity<>(a, HttpStatus.BAD_REQUEST);
    //     }
    //     // Scrub object history and save to repo
    //     a.clearHistory();
    //     repository.save(a);
    //     return new ResponseEntity<>(a, HttpStatus.OK);
    // }

    // @GetMapping("/delete/{objectID}")
    // public ResponseEntity<List<QuestionObject>> delete(@PathVariable int objectID) {
    //     QuestionObject a = repository.findById(objectID).get();
    //     // Check if owner matches
    //     if (!(a.getOwner().equals(getUserName()))) {
    //         return new ResponseEntity<>(repository.findByowner(getUserName()), HttpStatus.BAD_REQUEST);
    //     }
    //     // Delete object from repo
    //     repository.delete(a);
    //     return new ResponseEntity<>(repository.findByowner(getUserName()), HttpStatus.OK);
    // }
}
