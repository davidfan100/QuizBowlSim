import java.io.IOException;

public class QuizBowlGame {

	public static void main(String[] args) throws IOException, InterruptedException {
		QuizBowl game = new QuizBowl();
		game.startGame();
		for (int i = 0; i < game.getNumQ(); i++) {
			game.displayQuestion();
		}
	}

}
