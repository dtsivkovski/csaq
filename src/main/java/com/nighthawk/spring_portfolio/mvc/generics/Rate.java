package com.nighthawk.spring_portfolio.mvc.generics;

public class Rate extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { Rate.key = key; }
	public enum KeyType implements KeyTypes {title, uid, year, type, rating}

	// Instance data
    private final String uid;  // user / person id
    private final String year;
    private final String type;
    private final int rating;

	/* constructor
	 *
	 */
	public Rate(String uid, String year, String type, int rating)
	{
		super.setType("Rate");
		this.uid = uid;
		this.year = year;
		this.type = type;
        this.rating = rating;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Rate.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.uid.equals(this.getKey())) {
			output += this.uid;
		} else if (KeyType.year.equals(this.getKey())) {
			output += this.year;
		} else if (KeyType.type.equals(this.getKey())) {
			output += this.type;
        } else if (KeyType.rating.equals(this.getKey())) {
			output += this.rating;
		} else {
			output += super.getType() + ": " + this.uid + ", " + this.year + ", " + this.type + ", " + this.rating;
		}
		return output;
		
	}

	// Test data initializer
	public static Rate[] Ratings() {
		return new Rate[]{
                new Rate("9", "2021", "Array List", 9),
                new Rate("12", "2022", "Methods and Control Structures", 8),
                new Rate("3", "2018", "Classes", 4),
                new Rate("7", "2014", "2D Arrays", 6)
		};
	}
	
	/* main to test User class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Rate[] objs = Ratings();

		// print with title
		Rate.setOrder(KeyType.title);
		Rate.print(objs);

		// print name only
		Rate.setOrder(KeyType.type);
		Rate.print(objs);
	}

}
