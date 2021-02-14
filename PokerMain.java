import java.io.*;
import java.util.ArrayList;
public class PokerMain {

	public static void main (String[] args) throws FileNotFoundException {
		Poker poker = new Poker ("boobs", "poker1.txt");
		//System.out.print(poker.setUp());
		double [] results = poker.setUp();
		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
		String [] pf = poker.givePF();
		for (int i = 0; i < pf.length; i++) {
			System.out.println(pf[i]);
		}

	}
}
