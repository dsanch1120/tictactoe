/*
 * Created by Daniel Sanchez
 * May 23, 2020
 * Abstract class for the players
 */
package ticTacToe;

public abstract class Player {
	//Variables to be used in the class
	protected char icon;
	protected Board board;
	//Methods to be implemented by human and computer player classes
	public abstract int move();
	public char getIcon() {
		return this.icon;
	}
}
