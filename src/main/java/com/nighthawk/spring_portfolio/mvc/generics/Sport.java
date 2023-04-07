package com.nighthawk.spring_portfolio.mvc.generics;

public class Sport extends Generics {
	// Class data
	public static KeyTypes key = KeyType.name;  // static initializer
	public static void setOrder(KeyTypes key) { Sport.key = key; }
	public enum KeyType implements KeyTypes {name, bp, team, age, dob}

	// Instance data
    private final String bp;  // sport / person id
    private final String team;
    private final int age;
    private final int dob;

	/* constructor
	 *
	 */
	public Sport(String bp, String team, int age, int dob)
	{
		super.setType("Sport");
		this.bp = bp;
		this.team = team;
		this.age = age;
        this.dob = dob;
	}

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Sport.key; }
	
	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.bp.equals(this.getKey())) {
			output += this.bp;
		} else if (KeyType.team.equals(this.getKey())) {
			output += this.team;
		} else if (KeyType.age.equals(this.getKey())) {
			output += this.age;
        } else if (KeyType.dob.equals(this.getKey())) {
			output += this.dob;
		} else {
			output += super.getType() + ": " + this.bp + ", " + this.team + ", " + this.age + ", " + this.dob;
		}
		return output;
		
	}

	// Test data initializer
	public static Sport[] sports() {
		return new Sport[]{
				new Sport( "Cristian Ronald ", "Al Nassr FC", 38,  1985),
                new Sport( "Tom Brady", "Buccaneers", 45, 1977),   
                new Sport( "Lebron James", "Lakers", 38,  1984)    
		};
	}
	
	/* main to test sport class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Sport[] objs = sports();

		// print with name
		Sport.setOrder(KeyType.bp);
		Sport.print(objs);

		// print name only
		Sport.setOrder(KeyType.team);
		Sport.print(objs);
	}

}
