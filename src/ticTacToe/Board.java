/*
 * Created by Daniel Sanchez
 * May 23, 2020
 * Creates the board and handles most of the game's functionality
 */
package ticTacToe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board {
	//Variables to be used throughout program
	private ArrayList<Player> players = new ArrayList<Player>();
	private BoardCell[][] board;
	private int currentPlayer = 0;
	private static Board theInstance = new Board();
	private int currentTurn = 0;

	//Methods to be utilized throughout program

	//Initializes everything
	public void initialize() {
		createBoard();
		createPlayers();
		playGame();
	}
	//Plays the game
	private void playGame() {
		while (!checkVictory()) {
			currentTurn++;
			if (currentTurn == 10) {
				catsGame();
				return;
			}
			displayBoard();
			System.out.println("Player " + (theInstance.currentPlayer + 1) + "'s Turn!");
			updateBoard(this.players.get(currentPlayer).move());
			if (currentPlayer == 1) {
				currentPlayer = 0;
			} else {
				currentPlayer++;
			}
		}
		endGame();
	}
	public int getCurrentTurn() {
		return currentTurn;
	}
	//Updates the board
	private void updateBoard(int value) {
		int counter = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (counter == value) {
					theInstance.board[i][j].setIcon(theInstance.players.get(currentPlayer).getIcon());
				}
				counter++;
			}
		}
	}
	
	//Handles a cat's game
	private void catsGame() {
		displayBoard();
		System.out.println("Cat's Game!!");
	}

	//Handles a victory
	private void endGame() {
		displayBoard();
		if (currentPlayer == 0) {
			System.out.println("Player 2 Wins!");
		} else {
			System.out.println("Player 1 Wins!");
		}
	}

	//Checks if a player won the game
	private boolean checkVictory() {	
		for (int i = 0; i < board.length; i++) {
			if (board[i][0].getIcon() == 'X' && board[i][1].getIcon() == 'X' && board[i][2].getIcon() == 'X') {
				return true;
			}
			if (board[i][0].getIcon() == 'O' && board[i][1].getIcon() == 'O' && board[i][2].getIcon() == 'O') {
				return true;
			}
		}
		for (int i = 0; i < board.length; i++) {
			if (board[0][i].getIcon() == 'X' && board[1][i].getIcon() == 'X' && board[2][i].getIcon() == 'X') {
				return true;
			}
			if (board[0][i].getIcon() == 'O' && board[1][i].getIcon() == 'O' && board[2][i].getIcon() == 'O') {
				return true;
			}
		}
		if (board[0][0].getIcon() == 'X' && board[1][1].getIcon() == 'X' && board[2][2].getIcon() == 'X') {
			return true;
		}
		if (board[0][0].getIcon() == 'O' && board[1][1].getIcon() == 'O' && board[2][2].getIcon() == 'O') {
			return true;
		}
		if (board[0][2].getIcon() == 'X' && board[1][1].getIcon() == 'X' && board[2][0].getIcon() == 'X') {
			return true;
		}
		if (board[0][2].getIcon() == 'O' && board[1][1].getIcon() == 'O' && board[2][0].getIcon() == 'O') {
			return true;
		}
		return false;
	}

	//Displays Board
	private void displayBoard() {
		//Clears terminal
		clearTerminal();

		//Displays the Board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].getIcon());
				if (j < board[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
		}
	}

	//Creates Board
	private void createBoard() {
		theInstance.board = new BoardCell[3][3];
		theInstance.board[0][0] = new BoardCell('1');
		theInstance.board[0][1] = new BoardCell('2');
		theInstance.board[0][2] = new BoardCell('3');
		theInstance.board[1][0] = new BoardCell('4');
		theInstance.board[1][1] = new BoardCell('5');
		theInstance.board[1][2] = new BoardCell('6');
		theInstance.board[2][0] = new BoardCell('7');
		theInstance.board[2][1] = new BoardCell('8');
		theInstance.board[2][2] = new BoardCell('9');
	}

	public BoardCell[][] getBoard() {
		return board;
	}
	//Creates Players
	private void createPlayers() {
		int uInput;
		System.out.println("Welcome to Tic-Tac-Toe!");
		System.out.println("Enter 1 for player vs player");
		System.out.println("Enter 2 for player vs computer");
		while (true) {
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();

			try {
				uInput = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
				continue;
			}
			if (uInput <= 0 || uInput > 3) {
				System.out.println("Invalid Input");
				continue;
			} else {
				break;
			}
		}
		switch (uInput) {
		case 1:
			theInstance.players.add(new HumanPlayer(1));
			theInstance.players.add(new HumanPlayer(2));
			break;
		case 2:
			System.out.println("Choose Difficulty");
			System.out.println("1 for Easy");
			System.out.println("2 for Difficult");
			int input;
			while (true) {
				Scanner in = new Scanner(System.in);
				String s = in.nextLine();

				try {
					uInput = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					System.out.println("Invalid Input");
					continue;
				}
				if (uInput < 1 || uInput > 2) {
					System.out.println("Invalid Input");
					continue;
				} else {
					input = uInput;
					break;
				}
			}

			theInstance.players.add(new HumanPlayer(1));
			theInstance.players.add(new ComputerPlayer(2, input));
			break;
		case 3:
			theInstance.players.add(new ComputerPlayer(1, 2));
			theInstance.players.add(new ComputerPlayer(2, 2));
			break;
		default:
			break;
		}
		Random rand = new Random();
		theInstance.currentPlayer = rand.nextInt(2);
	}

	//Clears terminal
	private void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void main(String[] args) {
		Board board = Board.getTheInstance();
		board.clearTerminal();
		board.initialize();
	}

	public static Board getTheInstance() {
		return theInstance;
	}

}
