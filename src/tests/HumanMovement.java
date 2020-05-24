package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ticTacToe.*;

class HumanMovement {
	//Tests that the icons generate properly or throw an exception
	@Test
	void testIcon() {
		HumanPlayer player1 = new HumanPlayer(1);
		assert(player1.getIcon() == 'X');
		HumanPlayer player2 = new HumanPlayer(2);
		assert(player2.getIcon() == 'O');
		HumanPlayer player3 = new HumanPlayer(3);
		assert(player3.getIcon() == '#');
	}

	//Tests that movement method generates an integer
	@Test
	void testMovement() {
		HumanPlayer player1 = new HumanPlayer(1);
		assert(0 < player1.move() && player1.move() < 10);
		
	}
}
