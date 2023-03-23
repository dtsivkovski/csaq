package com.nighthawk.spring_portfolio.mvc.FRQData;

import com.nighthawk.spring_portfolio.mvc.generics.Generics;
import javax.persistence.*;

@Entity
public class FRQData extends Generics {
    // Class data

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public static KeyTypes key = KeyType.description;  // static initializer
    public static void setOrder(KeyTypes key) { FRQData.key = key; }
    public enum KeyType implements KeyTypes {description, input, output}

    // Instance data
    private final String description;
    private final String input;
    private final String output;

    /* constructor */
    public FRQData(String description, String input, String output) {
        super.setType("FRQ");
        this.description = description;
        this.input = input;
        this.output = output;
    }

    /* 'Generics' requires getKey to help enforce KeyTypes usage */
    @Override
    protected KeyTypes getKey() { return FRQData.key; }

    /* 'Generics' requires toString override
     * toString provides data based off of Static Key setting
     */
    @Override
    public String toString() {
        String output = "";
        if (KeyType.description.equals(this.getKey())) {
            output += this.description;
        } else if (KeyType.input.equals(this.getKey())) {
            output += "Sample Input: " + this.input;
        } else if (KeyType.output.equals(this.getKey())) {
            output += "Sample Output: " + this.output;
        } else {
            output += super.getType() + ": " + this.description + ", " + this.input + ", " + this.output;
        }
        return output;
    }

    // Test data initializer
    public static FRQData[] frqs() {
        return new FRQData[]{
                new FRQData("Write a Java program that prompts the user that takes in 3 parameters to return a concatenation", "1:1.5:H1", "11.5H1"),
                new FRQData("Write a Java program that prompts the user to enter a string and then prints that string", "Hello World", "Hello World"),
                new FRQData("Write a Java program that calculates the sum of the first 10 natural numbers and prints the result", "", "55")
        };
    }

    /* main to test FRQData class */
    public static void main(String[] args) {
        // Inheritance Hierarchy
        FRQData[] objs = frqs();

        // print with description
        FRQData.setOrder(KeyType.description);
        FRQData.print(objs);

        // print with sample input
        FRQData.setOrder(KeyType.input);
        FRQData.print(objs);

        // print with sample output
        FRQData.setOrder(KeyType.output);
        FRQData.print(objs);
    }
}
