package ticTacToe;

public class ComputerPlayer extends Player{

	public ComputerPlayer(int playerNumber) {
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
		// TODO Auto-generated method stub
		return 0;
	}

}
