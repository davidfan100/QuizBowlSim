import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	private HashMap<String,String> question_Ans;
	/**
	 * 
	 */
	private List<String> questions;
	/**
	 * 
	 */
	private boolean gameEnded = false;
	
	public QuizBowl(Player[] p, int numQ, )
	/**
	 * @throws IOException 
	 * 
	 */
	public void startGame() throws IOException {
		Scanner input = new Scanner(System.in);
		String tempS = "";
		String line = null;
		FileReader filereader = new FileReader("quizbowlquestions.txt");
		BufferedReader br = new BufferedReader(filereader);
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
	public void displayQuestion() {
		Thread t = new Thread();
		Scanner input = new Scanner(System.in);
		int index = (int)(Math.random() * 50);
		String question = questions.get(index);
		for(int i = 0; i < question.length(); i++) {
			System.out.print(question.charAt(i));
			t.sleep(75);
		}
		String answer = input.nextLine().toLowerCase();
		if (question_Ans.get(questions.get(index)).indexOf(answer) >= 0) {
			System.out.println("correct!");
		} else {
			System.out.println("Incorrect, the correct answer is " + question_Ans.get(questions.get(index)));
		}
	}
	public void endGame() {
		for (int i = 0; i < numOfPlayers.length; i++) {
			System.out.println(numOfPlayers[i]);
		}
	}
}
