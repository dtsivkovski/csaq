package com.nighthawk.spring_portfolio.mvc.generics;

public class Homework extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { User.key = key; }
	public enum KeyType implements KeyTypes {title, uid, assignments, dob}

	// Instance data
    private final String uid;  // user / person id
    private final String assignments; // # of assignments for the week
    private final int dob; // due date

	/* constructor
	 *
	 */
	public Homework(String uid, String assignments, int dob)
	{
		super.setType("User");
		this.uid = uid;
		this.assignments = assignments;
        this.dob = dob;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return User.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.uid.equals(this.getKey())) {
			output += this.uid;
		} else if (KeyType.assignments.equals(this.getKey())) {
			output += this.assignments;
		} else if (KeyType.dob.equals(this.getKey())) {
			output += this.dob;
		} else {
			output += super.getType() + ": " + this.uid + ", " + this.assignments +  ", " + this.dob;
		}
		return output;
		
	}

	// Test data initializer
	public static Homework[] Homework() {
		return new Homework[]{
				new Homework("100", "Data Types : #2", 101906),
				new Homework("120", "Data Types : #4b", 031505)
		};
	}
	
	/* main to test User class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Homework[] objs = Homework();

		// print with title
		User.setOrder(KeyType.title);
		User.print(objs);

		// print name only
		User.setOrder(KeyType.title);
		User.print(objs);
	}

}
