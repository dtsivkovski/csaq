package com.nighthawk.spring_portfolio.mvc.generics;

/*
 * Animal class extends Generics and defines abstract methods
 */
public class Review extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { Review.key = key; }
	public enum KeyType implements KeyTypes {title, year, problem, rating, interactivity, questions}

	// Instance data
	private final int year;
	private final int problem;
	private final int rating;
	private final String interactivity;
    private final String questions;


	/* constructor
	 *
	 */
	public Review(int year, int problem, int rating, String interactivity, String questions)
	{
		super.setType("Review");
		this.year = year;
		this.problem = problem;
		this.rating = rating;
		this.interactivity = interactivity;
        this.questions = questions;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Review.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.year.equals(this.getKey())) {
			output += this.year;
			// output = output.substring(output.length() - 2);
		} else if (KeyType.problem.equals(this.getKey())) {
			output += this.problem;
		} else if (KeyType.rating.equals(this.getKey())) {
			output += this.rating;
		} else if (KeyType.interactivity.equals(this.getKey())) {
			output += this.interactivity;
        } else if (KeyType.questions.equals(this.getKey())) {
			output += this.questions;
		} else {
			output += super.getType() + ": " + this.year + ", " + this.problem + ", " + this.rating + ", " + this.interactivity + ", " + this.questions;
		}
		return output;
		
	}

	// Test data initializer
	public static Review[] reviews() {
		return new Review[]{
				new Review(2010, 1, 4, "Good", "Why is this considered a control structures question?"),
				new Review(2015, 1, 2, "Poor", "How does this array work?"),
				new Review(2017, 4, 5, "Excellent", "Why would a normal array not work here?"),
				new Review(2017, 3, 3, "Good", "Would this not be categorized as methods and control structures?"),

		};
	}
	
	/* main to test Animal class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Review[] objs = reviews();

		// print with title
		Review.setOrder(KeyType.title);
		Review.print(objs);

		// print name only
		Review.setOrder(KeyType.interactivity);
		Review.print(objs);
	}

}