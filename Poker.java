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
	private String[][] hand;
	private String pfBet;
	private boolean didShow;
	public Poker (String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}
	public double [] setUp () throws FileNotFoundException{
		ArrayList outputs = new ArrayList <String> ();
		scan = new Scanner(new FileReader(fileName));
		while (scan.hasNext()){
			pfBet = "nothing";
			String adder = runHand (name,scan);
			if (adder != null) {
				outputs.add(0,adder.substring(0,3));
				outputs.add(adder.substring(4));
			}
		}
		//return outputs;
		return getPercents(outputs);
		
	}
	public String [] givePF () throws FileNotFoundException{
		String[] outputs = {"fold:", "call 20:", "call 20+:", "raise 40:","raise 60:","rasie 80:","raise 100:","raise 100+"};
		scan = new Scanner(new FileReader(fileName));
		while (scan.hasNext()){
			didShow = false;
			pfBet = "nothing";
			runHand (name,scan);
			if (didShow) {
				for  (int i = 0; i <2; i ++) {
					if (hand[0][i].equals ("1")) {
						hand [0][i] = "10";
					}
				}
				String realHand = ""+hand[0][0]+hand[0][1];
				if (hand[1][0].equals(hand[1][1])) {
					realHand += "s, ";
				} else {
					realHand += "o, ";
				}
				if (pfBet.equals("f")) {
					outputs[0] += ""+realHand;
				} else if (pfBet.equals("c20")) {
					outputs[1] += ""+realHand;
				}else if (pfBet.equals("c+")) {
					outputs[2] += ""+realHand;
				}else if (pfBet.equals("r40")) {
					outputs[3] += ""+realHand;
				}else if (pfBet.equals("r60")) {
					outputs[4] += ""+realHand;
				}else if (pfBet.equals("r80")) {
					outputs[5] += ""+realHand;
				}else if (pfBet.equals("r100")) {
					outputs[6] += ""+realHand;
				}else if (pfBet.equals("r100+")) {
					outputs[7] += ""+realHand;
				}
			}
			
		}
		return outputs;	
	}
	public int getHands() {
		return hands;
	}
	public String getPFBet() {
		return pfBet;
	}
	
	public double[] getPercents(ArrayList <String> list){
		int poc = 0;
		int mid= 0;
		int top= 0;
		int low= 0;
		int hig= 0;
		int two= 0;
		int tri= 0;
		int oes= 0;
		int str= 0;
		int flu= 0;
		double pocP = 0;
		double midP= 0;
		double topP= 0;
		double lowP= 0;
		double higP= 0;
		double twoP= 0;
		double triP= 0;
		double oesP= 0;
		double strP= 0;
		double fluP= 0;
		for (int i = 0; i < (list.size()/2); i ++) {
			if (list.get(i).equals("poc")) {
				poc++;
				pocP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("mid")) {
				mid++;
				midP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("top")) {
				top++;
				topP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("low")) {
				low++;
				lowP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("hig")) {
				hig++;
				higP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("two")) {
				two++;
				twoP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("tri")) {
				tri++;
				triP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("oes")) {
				oes++;
				oesP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("str")) {
				str++;
				strP+= Double.parseDouble(list.get(list.size()-i-1));
			} else if (list.get(i).equals("flu")) {
				flu++;
				fluP+= Double.parseDouble(list.get(list.size()-i-1));
			} 
		}
		double[] result = new double[10];
		result [0] = higP/hig;
		result [1] = lowP/low;
		result [2] = midP/mid;
		result [3] = topP/top;
		result [4] = oesP/oes;
		result [5] = pocP/poc;
		result [6] = twoP/two;
		result [7] = triP/tri;
		result [8] = strP/str;
		result [9] = fluP/flu;
		return result;
	}
	
	//runs through hand given name n and scanner sc
	public String runHand (String n, Scanner sc) {
		//get to the start of the hand "big blind"
		int pot = 30;
		int bet = 0;
			String temp = sc.next();
			
		while (!temp.equals("big")) {

				temp = sc.next();
				if (temp.equals("entry")) {
					return null;
				}
		}
		String state = "p";
		//create a temp arraylist to stash the hand in
		String [][] flop = new String [2][3];
		String peek = sc.next();
		while (!peek.equals("starting")){
			if (state.equals("p")) {
				if (peek.equals("raises")) {
					sc.next();
					pot += Integer.parseInt(sc.next());
				} else if (peek.equals("calls")) {
					pot += Integer.parseInt(sc.next());
				}
			}
			if (peek.equals ("Flop:")) {
				state = "f";
				flop = checkFlop(sc);
			} else if (peek.equals ("River:")) {
				state = "r";
			} else if (peek.equals("Turn:")) {
				state = "t";
			} else if (peek.equals("entry")) {
				return null;
			
			} else if (peek.equals(n)) {
				sc.next();
				sc.next();
				String temp1 = sc.next();
				if (pfBet.equals("nothing")) {
					if (temp1.equals("calls")) {
						if (sc.next().equals("20")) {
							pfBet = "c20";
						} else {
							pfBet = "c+";
						}
						
					} else if (temp1.equals("folds")) {
						pfBet = "f";
						
					} else if (temp1.equals("raises")) {
						sc.next();
						int temp2 = Integer.parseInt(sc.next());
						if (temp2<=40) {
							pfBet = "r40";
						} else if (temp2>40&&temp2<60) {
							pfBet = "r46";
						} else if (temp2==60) {
							pfBet = "r60";
						} else if (temp2>60&&temp2<80) {
							pfBet = "r68";
						} else if (temp2==80) {
							pfBet = "r80";
						} else if (temp2>80&&temp2<100) {
							pfBet = "r81";
						} else if (temp2==100) {
							pfBet = "r100";
						} else if (temp2>100) {
							pfBet = "r100+";
						} 
						
					}
					
				}
				if (temp1.equals("shows")&& !state.equals("p")) {
					didShow = true;
					hand = checkHand(sc);
					String board = checkBoard (hand,flop);
					return ""+board+" " + ((double)bet/pot);
				
				}
				if (state.equals( "f")) {
					//checking what the action is
					if (temp1.equals("bets")) {
						bet = Integer.parseInt(sc.next());
					} 
				}
			}
			if (sc.hasNext()){
				peek = sc.next();
			}
		}
		return null;
	}
	public int getWins() {
		return wins;
	}
	
	public String checkBoard (String [][]hand, String [][]flop) {
		String [] nums = new String [5];
		String [] suits = new String [5];
		
		boolean pockets = false;
		boolean topPair = false;
		boolean midPair = false;
		boolean lowPair = false;
		boolean twoPair = false;
		boolean trips = false;
		boolean flushDraw = false;
		boolean gutShot = false;
		boolean openEnd = false;
		boolean flush = false;
		boolean straight = false;
		nums[0] = hand [0][0];
		nums[1] = hand [0][1];
		nums[2] = flop [0][0];
		nums[3] = flop [0][1];
		nums[4] = flop [0][2];
		suits[0] = hand [1][0];
		suits[1] = hand [1][1];
		suits[2] = flop [1][0];
		suits[3] = flop [1][1];
		suits[4] = flop [1][2];
		// pockets
		if (nums[0].equals(nums[1])) {
			pockets = true;
		}
		//pair
		if (nums[0].equals(nums[2])||(nums[0].equals(nums[3]))||(nums[0].equals(nums[4]))){
			if (nums[0].equals("A")||nums[0].equals("K")||nums[0].equals("Q")||nums[0].equals("J")) {
				topPair = true;
			} else if (nums[0].equals("1")||nums[0].equals("9")||nums[0].equals("8")||nums[0].equals("7")) {
				midPair = true;
			} else {
				lowPair = true;
			}
		} else if ((nums[1].equals(nums[2]))||(nums[1].equals(nums[3]))||(nums[0].equals(nums[4]))){
			if (nums[1].equals("A")||nums[1].equals("K")||nums[1].equals("Q")||nums[1].equals("J")) {
				topPair = true;
			} else if (nums[1].equals("1")||nums[1].equals("9")||nums[1].equals("8")||nums[1].equals("7")) {
				midPair = true;
			} else {
				lowPair = true;
			}
		}
		//trips
		if (pockets) {
			if (nums[0] .equals(nums[2])||nums[0] .equals(nums[3])||nums[0] .equals(nums[4])){
				trips = true;
			}
		} else if (nums[0].equals(nums[2])&&nums[0].equals(nums[3])) {
			trips = true;
		}else if (nums[0].equals(nums[2])&&nums[0].equals(nums[4])) {
			trips = true;
		}else if (nums[0].equals(nums[3])&&nums[0].equals(nums[4])) {
			trips = true;
		}else if (nums[1].equals(nums[2])&&nums[0].equals(nums[3])) {
			trips = true;
		}else if (nums[1].equals(nums[2])&&nums[0].equals(nums[4])) {
			trips = true;
		}else if (nums[1].equals(nums[3])&&nums[0].equals(nums[4])) {
			trips = true;
		}
		//two pair
		if (pockets) {
			if (nums[2].equals(nums[3])||nums[2].equals(nums[4])||nums[3].equals(nums[4])) {
				twoPair = true;
			}
		} else if (nums[0].equals(nums[2])||nums[0].equals(nums[3])||nums[0].equals(nums[4])) {
			if (nums[1].equals(nums[2])||nums[1].equals(nums[3])||nums[1].equals(nums[4])) {
				twoPair = true;
			}
		}
		//flush 
		if (suits[0].equals(suits[1])&&suits[0].equals(suits[2])&&suits[0].equals(suits[3])&&suits[0].equals(suits[4])) {
			flush = true;
		} 
		//flush draw
		if (suits[0].equals(suits[1])&&suits[0].equals(suits[2])&&suits[0].equals(suits[3])) {
			flushDraw = true;
		} else if (suits[0].equals(suits[1])&&suits[0].equals(suits[2])&&suits[0].equals(suits[4])) {
			flushDraw = true;
		} else if (suits[0].equals(suits[1])&&suits[0].equals(suits[3])&&suits[0].equals(suits[4])) {
			flushDraw = true;
		} else if (suits[0].equals(suits[4])&&suits[0].equals(suits[2])&&suits[0].equals(suits[3])) {
			flushDraw = true;
		} else if (suits[1].equals(suits[2])&&suits[1].equals(suits[3])&&suits[1].equals(suits[4])) {
			flushDraw = true;
		} 
		//straight
		String [] rank = {"A","2","3","4","5","6","7","8","9","1","J","Q","K","A"};
		if (!nums[0] .equals(null)) {
		for (int i = 0; i < 5; i ++) {
			for (int j = 0;j<10;j++) {
				if (nums [i].equals(rank[j])) {
					for (int k = 0; k < 5; k++) {
						if (nums[k].equals(rank[j+1])) {
							for (int l = 0; l < 5; l++) {
								if (nums[l].equals(rank[j+2])) {
									for (int m = 0; m < 5; m++) {
										if (nums[m].equals(rank[j+3])) {
											for (int n = 0; n < 5; n++) {
												if (nums[n].equals(rank[j+4])) {
													straight = true;
												}
											}
										}
									}
								}
		
							}
						} 
					}
				}
			}
		}
		}
		//straightDraw
		if (!nums[0] .equals(null)) {
		for (int i = 0; i < 5; i ++) {
			for (int j = 0;j<10;j++) {
				if (nums [i].equals(rank[j])) {
					for (int k = 0; k < 5; k++) {
						if (nums[k].equals(rank[j+1])) {
							for (int l = 0; l < 5; l++) {
								if (nums[l].equals(rank[j+2])) {
									for (int m = 0; m < 5; m++) {
										if (nums[m].equals(rank[j+3])) {
											openEnd = true;
										}
									}
								}
		
							}
						} 
					}
				}
			}
		}
		}
		
		if (flush) {
			return "flu";
		} else if (straight) {
			return "str";
		} else if (trips) {
			return "tri";
		} else if (twoPair) {
			return "two";
		} else if (openEnd) {
			return "oes";
		} else if (midPair) {
			return "mid";
		} else if (lowPair) {
			return "low";
		} else if (topPair) {
			return "top";
		} else if (pockets) {
			return "poc";
		} else {
			return "hig";
		}
	}	
	
	public String[][] checkHand(Scanner sc){
		sc.next();
		String []suitArray = new String [2];
		String []numArray = new String [2];
		String temp = sc.next();
		numArray[0] = temp.charAt(0)+"";
		if (!(temp.charAt(0)+"").equals("1")) {
			suitArray[0] = temp.charAt(1)+"";
		} else {
			suitArray[0] = temp.charAt(2)+"";
		}
		String temp1 = sc.next();
		numArray[1] = temp1.charAt(0)+"";
		if (!(temp1.charAt(0)+"").equals("1")) {
			suitArray[1] = temp1.charAt(1)+"";
		} else {
			suitArray[1] = temp1.charAt(2)+"";
		}
		
		String [][] combo = new String [2][2];
		combo [0][0] = numArray[0];
		combo [0][1] = numArray[1];
		combo [1][0] = suitArray[0];
		combo [1][1] = suitArray[1];
		return combo;
	}

	public String [][] checkFlop(Scanner sc) {
		String []suitArray = new String [3];
		String []numArray = new String [3];
		String temp = sc.next();
		numArray[0] = temp.charAt(1)+"";
		if (!(temp.charAt(1)+"").equals("1")) {
			suitArray[0] = temp.charAt(2)+"";
		} else {
			suitArray[0] = temp.charAt(3)+"";
		}
		String temp1 = sc.next();
		numArray[1] = temp1.charAt(0)+"";
		if (!(temp1.charAt(0)+"").equals("1")) {
			suitArray[1] = temp1.charAt(1)+"";
		} else {
			suitArray[1] = temp1.charAt(2)+"";
		}
		String temp2 = sc.next();
		numArray[2] = temp2.charAt(0)+"";
		if (!(temp2.charAt(0)+"").equals("1")) {
			suitArray[2] = temp2.charAt(1)+"";
		} else {
			suitArray[2] = temp2.charAt(2)+"";
		}
		String [][] combo = new String [2][3];
		combo [0][0] = numArray[0];
		combo [0][1] = numArray[1];
		combo [0][2] = numArray[2];
		combo [1][0] = suitArray[0];
		combo [1][1] = suitArray[1];
		combo [1][2] = suitArray[2];
		return combo;
	}
	
//	public int[][] getResults (ArrayList <String> list){
//		int [][] array = new int[2][12];
//		for (int i = 0; i < list.size(); i++)
//			if (list.get(i).charAt(0)== 'p') {
//				if (list.get(i).charAt(1)== 'c') {
//					array [0][0] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][0] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'k') {
//					array [0][1] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][1] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'r') {
//					array [0][2] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][2] += 1;
//					}
//				} 
//			} else if (list.get(i).charAt(0)== 'f') {
//				if (list.get(i).charAt(1)== 'c') {
//					array [0][3] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][3] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'k') {
//					array [0][4] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][4] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'r') {
//					array [0][5] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][5] += 1;
//					}
//				} 
//			} else if (list.get(i).charAt(0)== 't') {
//				if (list.get(i).charAt(1)== 'c') {
//					array [0][6] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][6] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'k') {
//					array [0][7] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][7] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'r') {
//					array [0][8] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][8] += 1;
//					}
//				} 
//			} else if (list.get(i).charAt(0)== 'r') {
//				if (list.get(i).charAt(1)== 'c') {
//					array [0][9] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][9] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'k') {
//					array [0][10] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][10] += 1;
//					}
//				} else if (list.get(i).charAt(1)== 'r') {
//					array [0][11] += 1;
//					if (list.get(i).length() == 3) {
//						array [1][11] += 1;
//					}
//				} 
//			} else {
//		
//			}
//		return array;
//				
//	}


}
