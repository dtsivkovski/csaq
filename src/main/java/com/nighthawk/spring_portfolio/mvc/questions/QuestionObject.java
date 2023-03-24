package com.nighthawk.spring_portfolio.mvc.questions;

import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuestionObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String question;
    private char answer;

    private String opA;
    private String opB;
    private String opC;
    private String opD;

    // Hashmap with type and result for history of calculations on the object
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "type")
    @Column(name = "result")
    private Map<String, String> history = new HashMap<String, String>();

    QuestionObject(String q, String optionA, String optionB, String optionC, String optionD, char ans) {
        question = q;
        opA = optionA;
        opB = optionB;
        opC = optionC;
        opD = optionD;
        answer = ans;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAllAnswers() {
        String[] answers = {opA, opB, opC, opD};
        return answers;
    }

    public char getAnswerChar() {
        return answer;
    }

    public static void main(String[] args) {
        QuestionObject a = new QuestionObject("What is 2 + 2?", "6", "8", "1", "4", 'D');
        System.out.println("Question: " + a.getQuestion());
        System.out.println("Answer: " + a.getAnswer());
        System.out.println("History: " + a.getHistory());
    }
}