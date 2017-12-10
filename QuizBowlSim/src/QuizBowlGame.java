import java.io.IOException;

public class QuizBowlGame {

	public static void main(String[] args) throws IOException, InterruptedException {
		QuizBowl game = new QuizBowl();
		game.startGame();
		System.out.println("Press the enter key whenever you think you know the answer!");
		for (int i = 0; i < game.getNumQ(); i++) {
			System.out.println("Question #" + (i+1));
			game.displayQuestion();
		}
		game.endGame();
	}

}
