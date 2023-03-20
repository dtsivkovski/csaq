package com.nighthawk.spring_portfolio.mvc.generics;

public class UserProgress extends Generics {
	// Class data
	public static KeyTypes key = KeyType.name;  // static initializer
	public static void setOrder(KeyTypes key) { User.key = key; }
	public enum KeyType implements KeyTypes {name, email, password, progress}

	// Instance data
	private final String name;
	private final String email;
	private final String password;
	private final int progress;

	/* constructor
	 *
	 */
	public UserProgress(String name, String email, String password, int progress)
	{
		super.setType("User");
		this.name = name;
		this.email = email;
		this.password = password;
		this.progress = progress;
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
		if (KeyType.email.equals(this.getKey())) {
			output += this.email;
		} else if (KeyType.password.equals(this.getKey())) {
			output += "******"; // hide the password for security reasons
		} else if (KeyType.progress.equals(this.getKey())) {
			output += this.progress + "%";
		} else {
			output += super.getType() + ": " + this.name + ", " + this.email + ", " + this.progress + "%";
		}
		return output;
		
	}

	// Test data initializer
	public static UserProgress[] users() {
		return new UserProgress[]{
				new UserProgress("John Doe", "johndoe@example.com", "password123", 50),
				new UserProgress("Jane Smith", "janesmith@example.com", "password456", 75),
				new UserProgress("Bob Johnson", "bobjohnson@example.com", "password789", 25),
				new UserProgress("Alice Lee", "alicelee@example.com", "passwordabc", 90),
		};
	}
	
	/* main to test User class
	 * 
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		UserProgress[] objs = users();

		// print with name
		UserProgress.setOrder(KeyType.name);
		UserProgress.print(objs);

		// print email only
		UserProgress.setOrder(KeyType.email);
		UserProgress.print(objs);
		
		// print progress only
		UserProgress.setOrder(KeyType.progress);
		UserProgress.print(objs);
	}
}
