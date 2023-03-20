package com.nighthawk.spring_portfolio.mvc.generics;

public class UserInterests extends Generics {
    // Class data
    public static KeyTypes key = KeyType.title; // static initializer
    public static void setOrder(KeyTypes key) {
        UserInterests.key = key;
    }

    public enum KeyType implements KeyTypes {
        title, interest1, interest2, interest3
    }

    // Instance data
    private final String uid; // user / person id
    private final String interest1;
    private final String interest2;
    private final String interest3;

    /* constructor
     *
     */
    public UserInterests(String uid, String interest1, String interest2, String interest3) {
        super.setType("UserInterests");
        this.uid = uid;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
    }

    /* 'Generics' requires getKey to help enforce KeyTypes usage */
    @Override
    protected KeyTypes getKey() {
        return UserInterests.key;
    }

    /* 'Generics' requires toString override
     * toString provides data based off of Static Key setting
     */
    @Override
    public String toString() {
        String output = "";
        if (KeyType.uid.equals(this.getKey())) {
            output += this.uid;
        } else if (KeyType.interest1.equals(this.getKey())) {
            output += this.interest1;
        } else if (KeyType.interest2.equals(this.getKey())) {
            output += this.interest2;
        } else if (KeyType.interest3.equals(this.getKey())) {
            output += this.interest3;
        } else {
            output += super.getType() + ": " + this.uid + ", " + this.interest1 + ", " + this.interest2 + ", " + this.interest3;
        }
        return output;
    }

    // Test data initializer
    public static UserInterests[] UserInterestsList() {
        return new UserInterests[]{
                new UserInterests("100", "Music", "Sports", "Reading"),
                new UserInterests("101", "Dancing", "Cooking", "Travelling")
        };
    }

    /* main to test UserInterests class
     *
     */
    public static void main(String[] args) {
        // Inheritance Hierarchy
        UserInterests[] objs = UserInterestsList();

        // print with title
        UserInterests.setOrder(KeyType.title);
        UserInterests.print(objs);

        // print name only
        UserInterests.setOrder(KeyType.interest1);
        UserInterests.print(objs);
    }
}
