package com.nighthawk.spring_portfolio.mvc.generics;

public class FRQ extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { FRQ.key = key; }
	public enum KeyType implements KeyTypes {title, year, problem, subject, difficulty}

	// Instance data
	private final int year;
	private final int problem;
	private final String subject;
	private final String difficulty;

	/* constructor
	 *
	 */
	public FRQ(int year, int problem, String subject, String difficulty)
	{
		super.setType("FRQ");
		this.year = year;
		this.problem = problem;
		this.subject = subject;
		this.difficulty = difficulty;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return FRQ.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.year.equals(this.getKey())) {
			output += this.year;
		} else if (KeyType.problem.equals(this.getKey())) {
			output += this.problem;
		} else if (KeyType.subject.equals(this.getKey())) {
			output += this.subject;
		} else if (KeyType.difficulty.equals(this.getKey())) {
			output += this.difficulty;
		} else {
			output += super.getType() + ": " + this.year + ", " + this.problem + ", " + this.subject + ", " + this.difficulty;
		}
		return output;
		
	}

	// Test data initializer
	public static FRQ[] FRQs() {
		return new FRQ[]{
				new FRQ(2010, 1, "Methods and Control Structures", "Medium"),
				new FRQ(2017, 4,  "2D Arrays", "Easy"), 
				new FRQ(2021, 3, "Classes", "Hard"),
				new FRQ(2015, 1, "Arrays", "Easy"),
		};
	}
	
	/* main to test FRQ class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		FRQ[] objs = FRQs();

		// print with title
		FRQ.setOrder(KeyType.title);
		FRQ.print(objs);

		// print subject only
		FRQ.setOrder(KeyType.subject);
		FRQ.print(objs);
	}

}