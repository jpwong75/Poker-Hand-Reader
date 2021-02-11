import java.io.*;
public class PokerMain {

	public static void main (String[] args) throws FileNotFoundException {
		Poker poker = new Poker ("JP", "poker3.txt");
		System.out.print(poker.setUp());
		System.out.println();
		int [][] array = poker.getResults(poker.setUp());
		for (int i = 0; i < 12;  i++) {
			System.out.println((double)array [1][i]/array[0][i]);
		}
		int wins = 0;
		int hands = 0;
		for (int j = 0; j < 12;  j++) {
			wins+= array [1][j];
		}
		for (int h = 0; h < 12;  h++) {
			hands += array[0][h];
		}
		hands+= wins;
		System.out.println((double)wins/hands);
	}
}
