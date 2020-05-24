/*
 * Created by Daniel Sanchez
 * May 23, 2020
 * Human player extends the 
 */
package ticTacToe;

import java.util.Scanner;

public class HumanPlayer extends Player {
	private int iIndex;
	private int jIndex;
	
	public HumanPlayer(int playerNumber) {
		super();
		board = Board.getTheInstance();
		switch (playerNumber) {
		case 1:
			this.icon = 'X';
			break;
		case 2:
			this.icon = 'O';
			break;
		default:
			this.icon = '#';
		}
	}

	@Override
	public int move() {
		Scanner in = new Scanner(System.in);
		int uInput;
		while (true) {
			String s = in.nextLine();

			try {
				uInput = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input");
				continue;
			}
			getIndex(uInput);
			if (uInput < 1 || uInput > 9 || board.getBoard()[iIndex][jIndex].getIcon() == 'X' || board.getBoard()[iIndex][jIndex].getIcon() == 'O') {
				System.out.println("Invalid Input");
				continue;
			} else {
				break;
			}
		}
		return uInput;
	}
	
	private void getIndex(int input) {
		int count = 1;
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (count == input) {
					iIndex = i; 
					jIndex = j;
				}
				count++;
			}
		}
	}
	
	public char getIcon() {
		return this.icon;
	}

}
