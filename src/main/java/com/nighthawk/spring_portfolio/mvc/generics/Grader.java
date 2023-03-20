package com.nighthawk.spring_portfolio.mvc.generics;

public class Grader extends Generics {
	// Class data
	public static KeyTypes key = KeyType.title;  // static initializer
	public static void setOrder(KeyTypes key) { Grader.key = key; }
	public enum KeyType implements KeyTypes {title, id, email, frq_name, status, score}

	// Instance data
	private final long id;
    private final String email;
    private final String frq_name;
    private final Boolean status;
    private final int score;

	/* constructor
	 *
	 */
	public Grader(long id, String email, String frq_name, Boolean status, int score)
    {
        super.setType("Grader");
        this.id = id;
        this.email = email;
        this.frq_name = frq_name;
        this.status = status;
        this.score = score;
    }

	/* 'Generics' requires getKey to help enforce KeyTypes usage */
	@Override
	protected KeyTypes getKey() { return Grader.key; }

	/* 'Generics' requires toString override
	 * toString provides data based off of Static Key setting
	 */
	@Override
	public String toString()
	{
		String output="";
		if (KeyType.id.equals(this.getKey())) {
			output += this.id;
		} else if (KeyType.email.equals(this.getKey())) {
			output += this.email;
		} else if (KeyType.frq_name.equals(this.getKey())) {
			output += this.frq_name;
		} else if (KeyType.status.equals(this.getKey())) {
			output += this.status;
		} else if (KeyType.score.equals(this.getKey())) {
            output += this.score;
        } else {
			output += super.getType() + ": " + this.id + ", " + this.email + ", " + this.frq_name + ", " + this.status + ", " + this.score;
		}
		return output;

	}

	// Test data initializer
	public static Grader[] Graders() {
		return new Grader[]{
				new Grader(1, "aadit@aadit.com", "Steptracker", true, 100),
                new Grader(2, "bruh@bruh.com", "LightBoard", false, 0),
		};
	}

	/* main to test Grader class
	 *
	 */
	public static void main(String[] args)
	{
		// Inheritance Hierarchy
		Grader[] objs = Graders();

		// print with title
		Grader.setOrder(KeyType.title);
		Grader.print(objs);

		// print name only
		Grader.setOrder(KeyType.frq_name);
		Grader.print(objs);
	}

}
