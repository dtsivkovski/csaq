package com.nighthawk.spring_portfolio.mvc.generics;

public class User extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { User.key = key; }
	public enum KeyType implements KeyTypes {title, name, age, color}

	// Instance data
    private final String uid;  // user / person id
    private final String password;
    private final String name;
    private final int dob;

	/* constructor
	 *
	 */
	public User(String uid, String password, String name, int dob)
	{
		super.setType("User");
		this.uid = uid;
		this.password = password;
		this.name = name;
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
		} else if (KeyType.password.equals(this.getKey())) {
			output += this.password;
		} else if (KeyType.name.equals(this.getKey())) {
			output += this.name;
        } else if (KeyType.dob.equals(this.getKey())) {
			output += this.dob;
		} else {
			output += super.getType() + ": " + this.uid + ", " + this.password + ", " + this.name + ", " + this.dob;
		}
		return output;
		
	}

	// Test data initializer
	public static User[] Users() {
		return new User[]{
				new User("100", "Password1234", "Krish", 101906),
		};
	}
	
	/* main to test User class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		User[] objs = Users();

		// print with title
		User.setOrder(KeyType.title);
		User.print(objs);

		// print name only
		User.setOrder(KeyType.name);
		User.print(objs);
	}

}
