import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class QuizBowl {
	/**
	 * 
	 */
	private Player[] numOfPlayers;
	/**
	 * 
	 */
	private int numOfQuestions;
	/**
	 * 
	 */
	private HashMap<String,String> question_Ans = new HashMap<String,String>();
	/**
	 * 
	 */
	private List<String> questions;
	/**
	 * 
	 */
	private boolean gameEnded = false;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void startGame() throws IOException {
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
			if (holdNums >=5 && holdNums <= 20) {
				numOfQuestions = holdNums;
				break;
			}
			System.out.println("How many questions for this round (5-20)?");
			holdNums = input.nextInt();
		}
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
		System.out.println(question_Ans);
	}
	public void displayQuestion() throws InterruptedException {
		Thread t = new Thread();
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < numOfPlayers.length; i++) {
			int index = (int)(Math.random() * 50);
			String question = questions.get(index);
			for(int j = 0; j < question.length(); j++) {
				System.out.print(question.charAt(j));
				t.sleep(75);
			}
			String answer = input.nextLine().toLowerCase();
			if (question_Ans.get(questions.get(index)).indexOf(answer) >= 0) {
				numOfPlayers[i].changeScore(10);
				System.out.println("correct!");
			} else {
				numOfPlayers[i].changeScore(-5);
				System.out.println("Incorrect, the correct answer is " + question_Ans.get(questions.get(index)));
			}
		}
	}
	public void endGame() {
		for (int i = 0; i < numOfPlayers.length; i++) {
			System.out.println(numOfPlayers[i]);
		}
	}
	public int getNumQ() {
		return numOfQuestions;
	}
}
