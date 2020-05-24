/*
 * Created by Daniel Sanchez
 * May 23, 2020
 * Handles the individual BoardCell objects
 */
package ticTacToe;

public class BoardCell {
	//Icon to be displayed alongside board
	private char icon;

	//Constructor that initializes the icon
	public BoardCell(char icon) {
		super();
		this.icon = icon;
	}
	
	//Getters and Setters
	public char getIcon() {
		return icon;
	}

	public void setIcon(char icon) {
		this.icon = icon;
	}
	
}
