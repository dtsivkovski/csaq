package com.nighthawk.spring_portfolio.mvc.generics;

public class Stats extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { Stats.key = key; }
	public enum KeyType implements KeyTypes {title, year, score, problem1, problem2, problem3, problem4}

	// Instance data
	private final int year;
	private final int score;
	private final int problem1;
	private final int problem2;
	private final int problem3;
	private final int problem4;

	/* constructor
	 *
	 */
	public Stats(int year, int score, int problem1, int problem2, int problem3, int problem4)
	{
		super.setType("FRQ");
		this.year = year;
		this.score = score;
		this.problem1 = problem1;
		this.problem2 = problem2;
		this.problem3 = problem3;
		this.problem4 = problem4;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Stats.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.year.equals(this.getKey())) {
			output += this.year;
		} else if (KeyType.score.equals(this.getKey())) {
			output += this.score;
		} else if (KeyType.problem1.equals(this.getKey())) {
			output += this.problem1;
		}  else if (KeyType.problem2.equals(this.getKey())) {
			output += this.problem2;
		} else if (KeyType.problem3.equals(this.getKey())) {
			output += this.problem3;
		} else if (KeyType.problem4.equals(this.getKey())) {
			output += this.problem4;
		} else {
			output += super.getType() + ": " + this.year + ", Overall Score: " + this.score + 
			", \n\t\tProblem 1 Points: " + this.problem1 + ", \n\t\tProblem 2 Points: " + this.problem2 + ", \n\t\tProblem 3 Points: " + this.problem3 + ", \n\t\tProblem 4 Points: " + this.problem4;
		}
		return output;
		
	}

	// Test data initializer
	public static Stats[] Stat() {
		return new Stats[]{
				new Stats(2010, 23, 3, 3, 9, 8),
				new Stats(2017, 33, 7, 9, 8, 9), 
				new Stats(2021, 20, 5, 9, 4, 2),
				new Stats(2015, 26, 7, 8, 6, 5),
		};
	}
	
	/* main to test Stats class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Stats[] objs = Stat();

		// print with title
		Stats.setOrder(KeyType.title);
		Stats.print(objs);
	}

}