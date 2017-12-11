import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QuizBowl {
	/**
	 * Number of Players in the round.
	 */
	private Player[] numOfPlayers;
	/**
	 * Number of Questions in the Round
	 */
	private int numOfQuestions;
	/**
	 * A Map of Questions and Answers
	 */
	private HashMap<String,String> question_Ans = new HashMap<String,String>();
	/**
	 * A List of the Keys of question_Ans, which are the questions, to randomly choose questions.
	 */
	private List<String> questions;

	/**
	 * @throws IOException 
	 * Start the Quiz Bowl Game
	 */
	public void startGame() throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("Hi there and welcome to the Quiz Bowl Simulator. Please enter the number of people playing this round (1-4)");
		int numOfP = input.nextInt();
		
		Player[] hold = new Player[numOfP];
		for (int i = 0; i < numOfP; i++) {
			System.out.println("Please enter Player" + (i+1) + "'s name:");
			String s = input.next();
			hold[i] = new Player(s);
		}
		
		numOfPlayers = hold;
		int holdNums = 0;
		while (true) {
			if (holdNums >=3 && holdNums <= 20) {
				numOfQuestions = holdNums;
				break;
			}
			System.out.println("How many questions for this round (3-20)?");
			holdNums = input.nextInt();
		}
		initializeQuestions();
	}
	/**
	 * @throws IOException 
	 * Initialize the questions and answers and puts them in our Hashmap.
	 */
	public void initializeQuestions() throws IOException {
		String tempS = "";
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(
		                      new FileInputStream("quizbowlquestions.txt"), "UTF-8"));
		while ((line = br.readLine()) != null) {
			if (line.contains("*")) {
				question_Ans.put(tempS, line.substring(1));
				tempS = "";
			} else {
				tempS += line;
				tempS += "\n";
			}
		}
		questions = new ArrayList<String>(question_Ans.keySet());
	}
	/**
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * Used to display the question to the player
	 */
	public void displayQuestion() throws InterruptedException, IOException {
		int timeLimit = 40;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Scanner input_Answer = new Scanner(System.in);
		
		for (int i = 0; i < numOfPlayers.length; i++) {
			if (numOfPlayers.length > 1) {
				System.out.println(numOfPlayers[i].getName() +"'s Question");
			}
			int index = (int)(Math.random() * 50);
			String question = questions.get(index);
			for(int j = 0; j < question.length(); j++) {
				System.out.print(question.charAt(j));
				long startTime = System.currentTimeMillis();
				while ((System.currentTimeMillis() - startTime) < timeLimit
				        && !input.ready()) {
				}

				if (input.ready()) {
				    break;
				} 
			}
			
			System.out.println();
			String answer = "";
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) < 5000
			        && !input.ready()) {
			}
			if (input.ready()) {
			    answer = input.readLine().toLowerCase();
			} 
			if (!answer.equals("") && question_Ans.get(questions.get(index)).indexOf(answer) >= 0) {
				numOfPlayers[i].changeScore(10);
				System.out.println("correct!");
			} else {
				numOfPlayers[i].changeScore(-5);
				System.out.println("Incorrect, the correct answer is " + question_Ans.get(questions.get(index)));
			}
		}
	}
	/**
	 * Used to display the final results once the game has finished.
	 */
	public void endGame() {
		ArrayList<String> winners = new ArrayList<String>();
		int maxScore = numOfPlayers[0].getScore();
		for (int i = 0; i < numOfPlayers.length; i++) {
			System.out.println(numOfPlayers[i]);
			if (numOfPlayers[i].getScore() > maxScore){
				winners.remove(0);
				maxScore = numOfPlayers[i].getScore();
				winners.add(numOfPlayers[i].getName());
			}
			if (numOfPlayers[i].getScore() == maxScore) {
				winners.add(numOfPlayers[i].getName());
			}
		}
		if (winners.size() == 1) {
			System.out.println("The winner is " + winners.get(0) + "!");
		} else {
			System.out.print("We have a tie between ");
			for (int i = 0; i < winners.size()-1; i++) {
				System.out.print(winners.get(i) + " and ");
			}
			System.out.println(winners.get(winners.size()-1) + "!");
		}
	}
	/**
	 * Get the number of questions.
	 * @return numOfQuestions
	 */
	public int getNumQ() {
		return numOfQuestions;
	}
}
