package com.nighthawk.spring_portfolio.mvc.generics;

public class FRQTypes extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { FRQTypes.key = key; }
	public enum KeyType implements KeyTypes {title, name, year, qnum, description}

	// Instance data
	private final String name;
	private final int year;
	private final int qnum;
    private String description;

	/* constructor
	 *
	 */
	public FRQTypes(String name, int year, int qnum, String description)
	{
		super.setType("FRQTypes");
		this.name = name;
		this.year = year;
		this.qnum = qnum;
        if (description == null) {
            this.description = "No description";
        }
        else {
            this.description = description;
        }
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return FRQTypes.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.name.equals(this.getKey())) {
			output += this.name;
		} else if (KeyType.year.equals(this.getKey())) {
			output += this.year;
		} else if (KeyType.qnum.equals(this.getKey())) {
			output += this.qnum;
		} else if (KeyType.description.equals(this.getKey())) {
			output += this.description;
		} else {
			output += super.getType() + ": " + this.name + ", " + this.year + ", #" + this.qnum + ", Description: '" + this.description + "'";
		}
		return output;
		
	}

	// Test data initializer
	public static FRQTypes[] FRQTypess() {
		return new FRQTypes[]{
				new FRQTypes("Lightboard", 2019, 4, "Creating a lightboard with methods and classes."),
                new FRQTypes("Steptracker", 2019, 2, "Creating a fitness tracking system, writing a class with methods.")
		};
	}
	
	/* main to test FRQTypes class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		FRQTypes[] objs = FRQTypess();

		// print with title
		FRQTypes.setOrder(KeyType.title);
		FRQTypes.print(objs);

		// print name only
		FRQTypes.setOrder(KeyType.name);
		FRQTypes.print(objs);
	}

}
