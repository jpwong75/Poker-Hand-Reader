import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner; 
public class Poker {
	private String name;
	private String fileName;
	private Scanner scan;
	private int hands;
	private int wins;
	public Poker (String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}
	public ArrayList <String> setUp () throws FileNotFoundException{
		ArrayList outputs = new ArrayList <String> ();
		scan = new Scanner(new FileReader(fileName));
		while (scan.hasNext()){
			ArrayList tempArray = runHand (name,scan);
			for (int i = 0; i < tempArray.size();i++) {
				outputs.add(tempArray.get(i));
			}
			hands++;
		}
		return outputs;
	}
	public int getHands() {
		return hands;
	}
	
	//runs through hand given name n and scanner sc
	public ArrayList <String> runHand (String n, Scanner sc) {
		ArrayList outputs = new ArrayList <String> ();
		//get to the start of the hand "big blind"
		
			String temp = sc.next();
			
		while (!temp.equals("big")) {

				temp = sc.next();
				if (temp.equals("entry")) {
					return outputs;
				}
		}
		String state = "p";
		//create a temp arraylist to stash the hand in
		
		String peek = sc.next();
		while (!peek.equals("ending")){
			
			if (peek.equals ("Flop:")) {
				state = "f";
			} else if (peek.equals ("River:")) {
				state = "r";
			} else if (peek.equals("Turn:")) {
				state = "t";
			} else if (peek.equals("entry")) {
				return outputs;
			} else if (peek.equals(n)) {
				sc.next();
				sc.next();
				String temp1 = sc.next();
				//checking what the action is
				if (temp1.equals("calls")) {
					outputs.add (state + "c");
				} else if (temp1.equals("raises")||temp1.equals("bets")) {
					outputs.add (state + "r");
				} else if (temp1.equals("checks")) {
					outputs.add (state + "k");
				} else if (temp1.equals("collected")){				
					for (int i = 0; i < outputs.size(); i++ ) {
						outputs.set(i, outputs.get(i)+"w");
					}
					wins++;
					return outputs;
				} 
			}
			if (sc.hasNext()){
				peek = sc.next();
			}
		}
		return outputs;
	}
	public int getWins() {
		return wins;
	}
	
	public int[][] getResults (ArrayList <String> list){
		int [][] array = new int[2][12];
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).charAt(0)== 'p') {
				if (list.get(i).charAt(1)== 'c') {
					array [0][0] += 1;
					if (list.get(i).length() == 3) {
						array [1][0] += 1;
					}
				} else if (list.get(i).charAt(1)== 'k') {
					array [0][1] += 1;
					if (list.get(i).length() == 3) {
						array [1][1] += 1;
					}
				} else if (list.get(i).charAt(1)== 'r') {
					array [0][2] += 1;
					if (list.get(i).length() == 3) {
						array [1][2] += 1;
					}
				} 
			} else if (list.get(i).charAt(0)== 'f') {
				if (list.get(i).charAt(1)== 'c') {
					array [0][3] += 1;
					if (list.get(i).length() == 3) {
						array [1][3] += 1;
					}
				} else if (list.get(i).charAt(1)== 'k') {
					array [0][4] += 1;
					if (list.get(i).length() == 3) {
						array [1][4] += 1;
					}
				} else if (list.get(i).charAt(1)== 'r') {
					array [0][5] += 1;
					if (list.get(i).length() == 3) {
						array [1][5] += 1;
					}
				} 
			} else if (list.get(i).charAt(0)== 't') {
				if (list.get(i).charAt(1)== 'c') {
					array [0][6] += 1;
					if (list.get(i).length() == 3) {
						array [1][6] += 1;
					}
				} else if (list.get(i).charAt(1)== 'k') {
					array [0][7] += 1;
					if (list.get(i).length() == 3) {
						array [1][7] += 1;
					}
				} else if (list.get(i).charAt(1)== 'r') {
					array [0][8] += 1;
					if (list.get(i).length() == 3) {
						array [1][8] += 1;
					}
				} 
			} else if (list.get(i).charAt(0)== 'r') {
				if (list.get(i).charAt(1)== 'c') {
					array [0][9] += 1;
					if (list.get(i).length() == 3) {
						array [1][9] += 1;
					}
				} else if (list.get(i).charAt(1)== 'k') {
					array [0][10] += 1;
					if (list.get(i).length() == 3) {
						array [1][10] += 1;
					}
				} else if (list.get(i).charAt(1)== 'r') {
					array [0][11] += 1;
					if (list.get(i).length() == 3) {
						array [1][11] += 1;
					}
				} 
			} else {
		
			}
		return array;
				
	}


}
