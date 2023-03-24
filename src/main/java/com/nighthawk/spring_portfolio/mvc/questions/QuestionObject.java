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
    private String answer;

    // Hashmap with type and result for history of calculations on the object
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "type")
    @Column(name = "result")
    private Map<String, String> history = new HashMap<String, String>();

    QuestionObject(String q, String ans) {
        question = q;
        answer = ans;
    }

    // Puts calculations in
    public void addQuestion(String q, String ans) {
        history.put(q, ans);
    }

    public Map<String, String> getHistory() {
        return history;
    }

    public String getQ() {
        return question;
    }

    public String getAns() {
        return answer;
    }

    public void clearHistory() {
        history.clear();
    }

    public static void main(String[] args) {
        QuestionObject a = new QuestionObject("What is 2 + 2? \nA: 4\nB: 3\nC: 21\nD: 1", "A");
        System.out.println("\nQuestion: " + a.getQ());
        System.out.println("Answer: " + a.getAns());
        System.out.println("History: " + a.getHistory());
    }
}