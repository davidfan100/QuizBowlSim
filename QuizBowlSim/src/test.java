import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class test {
	public static void main(String[] args) throws InterruptedException, IOException{
		//Scanner input = new Scanner(System.in);
		Thread t = new Thread();
		Scanner input = new Scanner(System.in);
		HashMap<String,String> qAns = new HashMap<String,String>();
		String s = "";
		String line = null;
		FileReader filereader = new FileReader("quizbowlquestions.txt");
		BufferedReader br = new BufferedReader(filereader);
		while ((line = br.readLine()) != null) {
			if (line.contains("*")) {
				qAns.put(s, line.substring(1));
				s = "";
			} else {
				s += line;
				s += "\n";
			}
		}
		List<String> q = new ArrayList<String>(qAns.keySet());
		int index = (int)(Math.random() * 50);
		String question = q.get(index);
		for(int i = 0; i < question.length(); i++) {
			System.out.print(question.charAt(i));
			t.sleep(75);
		}
		String answer = input.nextLine().toLowerCase();
		if (qAns.get(q.get(index)).indexOf(answer) >= 0) {
			System.out.println("correct!");
		} else {
			System.out.println("Incorrect, the correct answer is " + qAns.get(q.get(index)));
		}
	}
}
