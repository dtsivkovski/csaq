package com.nighthawk.spring_portfolio.mvc.generics;

public class UserPoll extends Generics {
    // Class data
    public static KeyTypes key = KeyType.title; // static initializer
    public static void setOrder(KeyTypes key) { UserPoll.key = key; }
    public enum KeyType implements KeyTypes { title, question, answer, date }

    // Instance data
    private final String question; // poll question
    private final String answer; // user's answer to poll question
    private final String date; // date when poll was created

    /* constructor
     *
     */
    public UserPoll(String question, String answer, String date) {
        super.setType("UserPoll");
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    /* 'Generics' requires getKey to help enforce KeyTypes usage */
    @Override
    protected KeyTypes getKey() { return UserPoll.key; }

    /* 'Generics' requires toString override
     * toString provides data based off of Static Key setting
     */
    @Override
    public String toString() {
        String output = "";
        if (KeyType.question.equals(this.getKey())) {
            output += this.question;
        } else if (KeyType.answer.equals(this.getKey())) {
            output += this.answer;
        } else if (KeyType.date.equals(this.getKey())) {
            output += this.date;
        } else {
            output += super.getType() + ": " + this.question + ", " + this.answer + ", " + this.date;
        }
        return output;
    }

    // Test data initializer
    public static UserPoll[] UserPolls() {
        return new UserPoll[] {
                new UserPoll("What is a variable in coding?", "A variable is a container for storing data values.", "2022-03-20"),
                new UserPoll("What is an array?", "An array is a collection of data items, all of the same type, in which each item's position is uniquely identified by an index.", "2022-03-21")
        };
    }

    /* main to test UserPoll class
     *
     */
    public static void main(String[] args) {
        // Inheritance Hierarchy
        UserPoll[] objs = UserPolls();

        // print with title
        UserPoll.setOrder(KeyType.title);
        UserPoll.print(objs);

        // print question only
        UserPoll.setOrder(KeyType.question);
        UserPoll.print(objs);
    }
}
