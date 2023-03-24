package com.nighthawk.spring_portfolio.mvc.questions;

import com.nighthawk.spring_portfolio.mvc.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
public class QuestionViewController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private QuestionJpa repository;

    @GetMapping("/database/question")
    public String person(Model model) {
        List<QuestionObject> list = repository.findAll();
        model.addAttribute("list", list);
        return "mvc/database/question";
    }

    /*  The HTML template Forms and PersonForm attributes are bound
        @return - template for person form
        @param - Person Class
    */
    @GetMapping("/database/questioncreate")
    public String questionAdd(QuestionObject question) {
        return "mvc/database/questioncreate";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Person object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/database/questioncreate")
    public String questionSave(@Valid QuestionObject question, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/questioncreate";
        }
        repository.save(question);
        // Redirect to next step
        return "redirect:/database/question";
    }

    @GetMapping("/database/questionupdate/{id}")
    public String personUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("question", repository.getOne(id));
        return "mvc/database/questionupdate";
    }

    @PostMapping("/database/questionupdate")
    public String personUpdateSave(@Valid QuestionObject question, BindingResult bindingResult) {
        // Validation of Decorated PersonForm attributes
        if (bindingResult.hasErrors()) {
            return "mvc/database/questionupdate";
        }
        repository.save(question);

        // Redirect to next step
        return "redirect:/database/question";
    }

    @GetMapping("/database/questiondelete/{id}")
    public String questionDelete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "redirect:/database/question";
    }

    @GetMapping("/database/question/search")
    public String question() {
        return "mvc/database/question_search";
    }

}