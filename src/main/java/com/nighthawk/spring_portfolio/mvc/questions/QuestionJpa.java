package com.nighthawk.spring_portfolio.mvc.questions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface QuestionJpa extends JpaRepository<QuestionObject, Integer> {
    // JPA has many built in methods, these few have been prototyped for this application
    // void save(StatsObject pobj);
    // List<PhysObject> findAllByOrderByuserIDAsc();
    List<QuestionObject> findByowner(String username);
}
