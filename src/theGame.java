import java.util.*;

import java.io.File; // to deal with files
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter; // to print files
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.Math;   // to get random moves


public class theGame {
	public static String[][] board;
	public static int[] allMoves = new int[9];
	
	/* Colors */
	public static final String TEXT_RESET = "\u001B[0m";
	public static final String TEXT_RED = "\u001B[31m";
	public static final String TEXT_BLUE = "\u001B[34m";
	public static final String TEXT_GREEN = "\u001B[32m";
	public static final String TEXT_WHITE = "\u001B[37m";
    public static final String RED_BACKGROUND= "\u001B[41m";
    public static final String BLUE_BACKGROUND = "\033[44m";
    public static final String BLACK_BACKGROUND = "\033[40m";

    /* to print the scoreboard */
    public static void readScoreboard() {
		try {
	      File myObj = new File("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\leaderboard.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	    	  String data = myReader.nextLine();
	    	  System.out.println(data);
	      }
	      myReader.close();
		} catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
		}
    }
    
    /* to add points to the player who won */
    public static void addscoreboard(String winner) {
    	try {
	      StringBuffer buffer = new StringBuffer();
		  String score = " ";
		  boolean cont = true;
    	  File myObj = new File("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\leaderboard.txt");
  	      Scanner myReader = new Scanner(myObj);
  	      while (myReader.hasNextLine() && cont) {
  	    	  buffer.append(myReader.nextLine()+System.lineSeparator());
  	      }
  	      String fileContents = buffer.toString();
  	      	if (fileContents.contains(winner)) {
  	      		myReader.close();	 	
  	      		int indexOfWinner = fileContents.indexOf(winner);
  	      		int findScore = indexOfWinner + winner.length();
  	      		score = fileContents.substring(findScore+1, findScore+5);
  	      		int realScore = Integer.parseInt(score.replaceAll("\\s", ""));
  	      		String replaceThis = winner + " " + score;
  	    	  	String withThis = winner + " " + (++realScore) + "  ";
  	    	  	fileContents = fileContents.replace(replaceThis, withThis); 
  	    	  	FileWriter myWriter = new FileWriter("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\leaderboard.txt");
  	    	  	myWriter.append(fileContents);
  	    	  	myWriter.flush();
		    } else {
		    	FileWriter Writer = new FileWriter("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\leaderboard.txt");
		    	fileContents += winner + " 1    ";
		    	Writer.append(fileContents);
		    	Writer.flush();
		    }
  	    
    	} catch (IOException e) {
    		System.out.println("An error occurred.");
    	    e.printStackTrace();
    	}
    
    }
    
    /* to print statistics */
    public static void readStats() {
    	try {
  	      File myObj = new File("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\stats.txt");
  	      Scanner myReader = new Scanner(myObj);
  	      while (myReader.hasNextLine()) {
  	    	  String data = myReader.nextLine();
  	    	  System.out.println(data);
  	      }
  	      myReader.close();
  		} catch (FileNotFoundException e) {
  	      System.out.println("An error occurred.");
  	      e.printStackTrace();
  		}
    }
    
    /* to get who won and add it to statistics*/
    public static void addStats(String stat) {
    	try {
  	      StringBuffer buffer = new StringBuffer();
  		  boolean cont = true;
  		  
  		  String totalGames = "games played...";
  		  String computerW = "computer wins...";
  		  String userW = "player wins...";
  		  String totalD = "total draws...";
  		  
      	  File myObj = new File("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\stats.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine() && cont) {
	    	  buffer.append(myReader.nextLine()+System.lineSeparator());
	      }
	        String fileContents = buffer.toString();
      		myReader.close();	 	
      		int indexOfTG = fileContents.indexOf(totalGames);
      		int indexOfCW = fileContents.indexOf(computerW);
      		int indexOfUW = fileContents.indexOf(userW);
      		int indexOfTD = fileContents.indexOf(totalD);
      		int findTG = indexOfTG+totalGames.length();
      		int TG = Integer.parseInt(fileContents.substring(findTG, findTG+4).replaceAll("\\s", ""));
      		int findCW = indexOfCW+computerW.length();
      		int CW = Integer.parseInt(fileContents.substring(findCW, findCW+4).replaceAll("\\s", ""));
      		int findUW = indexOfUW+userW.length();
      		int UW = Integer.parseInt(fileContents.substring(findUW, findUW+4).replaceAll("\\s", ""));
      		int findTD = indexOfTD+totalD.length();
      		int TD = Integer.parseInt(fileContents.substring(findTD, findTD+4).replaceAll("\\s", ""));
      		fileContents = fileContents.replace(totalGames + TG, totalGames + (++TG));
      	    if(stat == "computer") {
    	  		fileContents = fileContents.replace(computerW + CW, computerW + (++CW) + "  "); 
    	  	} else if(stat == "user") {
    	  		fileContents = fileContents.replace(userW + UW, userW + (++UW) + "  ");
    	  	} else if(stat == "draw") {
    	  		fileContents = fileContents.replace(totalD + TD, totalD + (++TD) + "  "); 
    	  	}
			FileWriter myWriter = new FileWriter("D:\\Coding\\javva\\Final Project - Tic Tac Toe\\src\\stats.txt");
    	  	myWriter.append(fileContents);
    	  	myWriter.flush();
	} catch (IOException e) {
      		System.out.println("An error occurred.");
      	    e.printStackTrace();
      	}
    }
    
    /* to print the board */
	public static void printBoard() {
		System.out.printf(" %s | %s | %s \n", board[0][0], board[0][1], board[0][2]);
		System.out.println("---+---+---");
		System.out.printf(" %s | %s | %s \n", board[1][0], board[1][1], board[1][2]);
		System.out.println("---+---+---");
		System.out.printf(" %s | %s | %s \n", board[2][0], board[2][1], board[2][2]);
	}
	
	/* to clear the board every time before printing the board*/
	public static void clearBoard() {
		board = new String[][] {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
	}
	
	/* to add every move in a list*/
	public static void addMove(int move, int turn) {
		allMoves[turn] = move;
	}
	
	/* to check if a move has been made before */
	public static boolean checkMove(int move, int turn) {
		for(int i=0; i<turn; i++) {
			if(allMoves[i] == move) {
				return false;
			}
		}
		return true;
	}
	
	/* to place player's move with his symbol */
	public static void placeMove(int move, String symbol) {
		switch (move) {
		case 1:
			board[0][0] = symbol;
			break;
		case 2:
			board[0][1] = symbol;
			break;
		case 3:
			board[0][2] = symbol;
			break;
		case 4:
			board[1][0] = symbol;
			break;
		case 5:
			board[1][1] = symbol;
			break;
		case 6:
			board[1][2] = symbol;
			break;
		case 7:
			board[2][0] = symbol;
			break;
		case 8:
			board[2][1] = symbol;
			break;
		case 9:
			board[2][2] = symbol;
			break;
		}
	}
	
	/* To check who won and if ther's a draw it returns nothing*/
	public static String checkWinner(String symbol) {
		if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) {
			return symbol;
		} else if(board[1][0] == board[1][1] && board[1][1] == board[1][2]) {
			return symbol;
		} else if(board[2][0] == board[2][1] && board[2][1] == board[2][2]) {
			return symbol;
		} else if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return symbol;
		} else if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return symbol;
		} else if(board[0][0] == board[1][0] && board[1][0] == board[2][0]) {
			return symbol;
		} else if(board[0][1] == board[1][1] && board[1][1] == board[2][1]) {
			return symbol;
		} else if(board[0][2] == board[1][2] && board[1][2] == board[2][2]) {
			return symbol;
		}
		return " ";
	}
	
	/* Computer vs Player */
	public static int computerMove() {
		Random random = new Random();
		int rndMove = random.nextInt(8 + 1) + 1;
		
		if(board[0][1] == board[0][2] || board[1][0] == board[2][0] || board[1][1] == board[2][2]) {
			return 1;
		} else if(board[0][0] == board[0][2] || board[1][1] == board[2][1]) {
			return 2;
		} else if(board[0][0] == board[0][1] || board[1][2] == board[2][2] || board[1][1] == board[2][0]) {
			return 3;
		} else if(board[0][0] == board[2][0] || board[1][1] == board[1][2]) {
			return 4;
		} else if(board[0][0] == board[2][2] || board[0][2] == board[2][0] || board[0][1] == board[2][1] || board[1][0] == board[1][2]) {
			return 5;
		} else if(board[0][2] == board[2][2] || board[1][0] == board[1][1]) {
			return 6;
		} else if(board[0][0] == board[1][0] || board[2][1] == board[2][2] || board[1][1] == board[0][2]) {
			return 7;
		} else if(board[0][1] == board[1][1] || board[2][0] == board[2][2]) {
			return 8;
		} else if(board[0][2] == board[1][2] || board[2][0] == board[2][1] || board[0][0] == board[1][1]) {
			return 9;
		}
		return rndMove;
	}


	public static void main(String[] args) {	
		
		Scanner sc = new Scanner(System.in);
		theGame ret = new theGame();
				
		System.out.println(""
	+ TEXT_RED  + " ______  ____    __      ______   ____     __      ______   ___     ___ \r\n"
				+ "|      ||    |  /  ]    |      | /    |   /  ]    |      | /   \\   /  _]\r\n"
				+ "|      | |  |  /  /     |      ||  o  |  /  /     |      ||     | /  [_ \r\n" + TEXT_RESET
                + "|_|  |_| |  | /  /      |_|  |_||     | /  /      |_|  |_||  O  ||    _]\r\n"
	+ TEXT_BLUE + "  |  |   |  |/   \\_       |  |  |  _  |/   \\_       |  |  |     ||   [_ \r\n"
				+ "  |  |   |  |\\     |      |  |  |  |  |\\     |      |  |  |     ||     |\r\n"
				+ "  |__|  |____|\\____|      |__|  |__|__| \\____|      |__|   \\___/ |_____|"
				+ TEXT_RESET);

		System.out.println(TEXT_RED+"------------------------------------"+TEXT_RESET+TEXT_BLUE+"------------------------------------\n"+TEXT_RESET);
		
		boolean contGame = true;
		int opt = 1;
		
		while (contGame) {
			
			/* Menu */
			System.out.println(TEXT_BLUE + "1 - Player vs Player " + TEXT_RESET);
			System.out.println(TEXT_BLUE + "2 - Player vs Computer " + TEXT_RESET);
			System.out.println(TEXT_BLUE + "3 - Scoreboard " + TEXT_RESET);
			System.out.println(TEXT_BLUE + "4 - Statistics " + TEXT_RESET);
			System.out.println(TEXT_BLUE + "5 - Exit " + TEXT_RESET);
			System.out.print("Enter your choice: ");
			boolean iptClean = false;
			// while(!iptClean) {
				try {opt = sc.nextInt(); iptClean = true;}
				catch(InputMismatchException e) { System.out.println("try again");}
			// }
			sc.nextLine(); /* to clear Scanner */
			
			/* Clear board to start over */
			clearBoard();
			
			/* Player vs Player */
			if (opt == 1) {
				System.out.println("--------------");
				String player1 = "";
				String player2 = "";
				System.out.print("Enter first player's name: ");
				player1 = sc.nextLine();
				System.out.print("Enter second player's name: ");
				player2 = sc.nextLine();
				
				int turn = 0;
				String symbol;
				int move;
				boolean cont = true;
				boolean moveCont = true;
				
				while(cont) {

					move = 0;
					symbol = " ";
					moveCont = true;
				
					if(turn == 9) {
						moveCont = false;
					}
					
					System.out.println("------------------------");
					printBoard();
					
					if(turn % 2 == 0) {
						symbol = TEXT_RED + "X" + TEXT_RESET;
						while(moveCont) {
							System.out.print(TEXT_RED + player1 + ", " + TEXT_RESET + "Enter your move: ");
							move = sc.nextInt();
							if(move>=0 && move <= 9 && checkMove(move, turn)) {
								addMove(move, turn);
								moveCont = false;
							} else {
								System.out.println("try again!!!");
							}
						}
						
					} else {
						symbol = TEXT_BLUE + "O" + TEXT_RESET;
						while(moveCont) {
							System.out.print(TEXT_BLUE + player2 + ", " + TEXT_RESET + "Enter your move: ");
							move = sc.nextInt();
							if(move>=0 && move <=9 && checkMove(move, turn)) {
								addMove(move, turn);
								moveCont = false;
							} else {
								System.out.println(BLACK_BACKGROUND + TEXT_WHITE + "try again!!!" + TEXT_RESET);
							}
						}
					}
					
					
					
					placeMove(move, symbol);
					
					/* check winner */
					if (checkWinner(symbol) == (TEXT_RED + "X" + TEXT_RESET)) {
						placeMove(move, symbol);
						System.out.println("------------------------");
						printBoard();
						System.out.println("------------------------");
						System.out.println(RED_BACKGROUND + TEXT_WHITE + "  " + player1 + " Wins !!!!!  " + TEXT_RESET);
						cont = false;
						addscoreboard(player1);
						addStats("");
					} else if (checkWinner(symbol) == (TEXT_BLUE + "O" + TEXT_RESET)) {
						placeMove(move, symbol);
						System.out.println("------------------------");
						printBoard();
						System.out.println("------------------------");
						System.out.println(BLUE_BACKGROUND + TEXT_WHITE + "  " + player2 + " Wins !!!!!  " + TEXT_RESET);
						cont = false;
						addscoreboard(player2);
						addStats("");
					} else if(turn == 9 && checkWinner(symbol) == " ") {
						System.out.println("------------------------");
						System.out.println(BLACK_BACKGROUND + TEXT_WHITE + "  it's a draw!!!  " + TEXT_RESET);
						cont = false;
						addStats("draw");
					}
					
					turn++;
				}
			}
			
			/* Computer vs Player */
			if(opt == 2) {
				System.out.println("--------------");
				String player = "";
				System.out.print("Enter your name: ");
				player = sc.nextLine();
				
				int turn = 0;
				String symbol;
				int move;
				int compMove;
				boolean cont = true;
				boolean moveCont = true;
				
				while(cont) {
					
					move = 0;
					symbol = " ";
					moveCont = true;
				
					if(turn == 9) {
						moveCont = false;
					}
					
					System.out.println("------------------------");
					printBoard();
					
					if(turn % 2 == 0) {
						symbol = TEXT_RED + "X" + TEXT_RESET;
						while(moveCont) {
							System.out.print(TEXT_RED + player + ", " + TEXT_RESET + "Enter your move: ");
							move = sc.nextInt();
							if(move>=0 && move <= 9 && checkMove(move, turn)) {
								addMove(move, turn);
								placeMove(move, symbol);
								moveCont = false;
							} else {
								System.out.println("try again!!!");
							}
						}
						
					} else {
						symbol = TEXT_BLUE + "O" + TEXT_RESET;
						compMove = computerMove();
						while(moveCont) {
							if(compMove >= 0 && compMove <= 9 && checkMove(compMove, turn)) {
								addMove(compMove, turn);
								placeMove(compMove, symbol);
								System.out.print(TEXT_BLUE + "Comptuer's move: " + TEXT_RESET);
								System.out.print(compMove + "\n");
								moveCont = false;
							} else if(compMove >= 9) {
								compMove--;
							} else if(compMove <= 9) {
								compMove++;
							}
						}
					}
					
					/* check winner */ 
					if (checkWinner(symbol) == (TEXT_RED + "X" + TEXT_RESET)) {
						placeMove(move, symbol);
						System.out.println("------------------------");
						printBoard();
						System.out.println("------------------------");
						System.out.println(RED_BACKGROUND + TEXT_WHITE + "  You Win !!!!!  " + TEXT_RESET);
						cont = false;
						addscoreboard(player);
						addStats("user");
					} else if (checkWinner(symbol) == (TEXT_BLUE + "O" + TEXT_RESET)) {
						placeMove(move, symbol);
						System.out.println("------------------------");
						printBoard();
						System.out.println("------------------------");
						System.out.println(BLUE_BACKGROUND + TEXT_WHITE + "  Computer Wins !!!!!  " + TEXT_RESET);
						cont = false;
						addStats("computer");
					} else if(turn == 9 && checkWinner(symbol) == " ") {
						System.out.println("------------------------");
						System.out.println(BLACK_BACKGROUND + TEXT_WHITE + "  it's a draw!!!  " + TEXT_RESET);
						cont = false;
						addStats("draw");
					}
					
					turn++;
				}
			}
			
			/* LeaderBoard */
			if(opt == 3) {
				System.out.println("--------------");
				System.out.println(TEXT_RED + "name score" + TEXT_RESET + TEXT_GREEN);
				readScoreboard();
				System.out.print(TEXT_RESET);
				
			}
			
			/* Statistics */
			if(opt == 4) {
				System.out.println("--------------");
				System.out.println(TEXT_GREEN);
				readStats();
				System.out.println(TEXT_RESET);
			}
			
			/* Exit */
			if(opt == 5) {
				System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-");
				System.out.println("See you later !!!");
				contGame = false;
			}
			
			/* wrong input */
			if (opt > 5) {
				System.out.println("try again !!");
			}
			
	
			System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-");
		}	
	}
}