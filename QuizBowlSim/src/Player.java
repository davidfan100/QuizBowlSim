
public class Player {
	/**
	 * Name of Player
	 */
	private String name;
	/**
	 * Score of Player
	 */
	private int score;
	/**
	 * Default Constructor
	 */
	public Player() {
		name = "";
		score = 0;
	}
	/**
	 * Constructor with String
	 * @param n name
	 */
	public Player(String n) {
		name = n;
		score = 0;
	}
	/**
	 * Change Player's score
	 * @param amount amount to change score by
	 */
	public void changeScore(int amount) {
		score += amount;
	}
	/**
	 * get Player's score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Display Player's name and score
	 */
	public String toString() {
		return name + "'s score: " + score; 
	}
}
