package com.nighthawk.spring_portfolio.mvc.generics;

public class Comments extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) {Comments.key = key;}
	public enum KeyType implements KeyTypes {title, comment, frq, score}

	// Instance data
	private final String frq;
	private final int score;
	private final String comment;

	// Constructor
	Comments(String frq, int score, String comment)
	{
		this.setType("Grade");
		this.frq = frq;
		this.score = score;
		this.comment = comment;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Comments.key; }

	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString() {		
		String output="";
		if (KeyType.comment.equals(this.getKey())) {
			output += this.comment;
		} else if (KeyType.frq.equals(this.getKey())) {
			output += this.frq;
		} else if (KeyType.score.equals(this.getKey())) {
			output += "00" + this.score;
			output = output.substring(output.length() - 2);
		} else {
			output = super.getType() + ": " + this.comment + ", " + this.frq + ", " + this.score;
		}
		return output;
	}

	// Test data initializer
	public static Comments[] grade() {
		return new Comments[]{
				new Comments("FRQ1", 40, "Didn't use loop"),
			    new Comments("FRQ2", 50, "result was non-boolean"),
			    new Comments("FRQ3", 60, "didn't initialize counter"),
			    new Comments("FRQ4", 70, "Bad Syntax"),
			    
		};
	}
	
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Comments[] objs = grade();

		// print with title
		Comments.setOrder(KeyType.title);
		Comments.print(objs);

		// print flavor only
		Comments.setOrder(KeyType.comment);
		Comments.print(objs);
	}
	
}
