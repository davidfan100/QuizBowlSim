import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, IOException{
		int x = 1; // wait 2 seconds at most
		String s = "JDKFJKBNSNEKFNKDSNFKDSNF";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < s.length(); i++) {
			System.out.print(s.charAt(i));
			long startTime = System.currentTimeMillis();
			while ((System.currentTimeMillis() - startTime) < x * 1000
			        && !in.ready()) {
			}

			if (in.ready()) {
			    break;
			} 
		}

	}
}
