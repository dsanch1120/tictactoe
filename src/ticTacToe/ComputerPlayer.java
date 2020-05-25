package ticTacToe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ComputerPlayer extends Player{
	private int difficulty;
	ArrayList<Integer> corners = new ArrayList<Integer>();
	ArrayList<Integer> edges = new ArrayList<Integer>();
	ArrayList<Integer> played = new ArrayList<Integer>();

	public ComputerPlayer(int playerNumber, int difficulty) {
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
		this.difficulty = difficulty;
	}

	@Override
	public int move() {
		Scanner sn = new Scanner(System.in);
		switch (this.difficulty) {
		case 1:
			sn.nextLine();
			return moveEasy();
		case 2:
			sn.nextLine();
			return moveDifficult();
		default:
			System.out.println("Error. Incorrect Difficulty Level");
			return -1;
		}
	}

	public int moveEasy() {
		ArrayList<Integer> options = new ArrayList<Integer>();
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (board.getBoard()[i][j].getIcon() != 'X' && board.getBoard()[i][j].getIcon() != 'O') {
					options.add(Character.getNumericValue(board.getBoard()[i][j].getIcon()));
				}
			}
		}

		Collections.shuffle(options);

		return options.get(0);
	}

	public int moveDifficult() {
		ArrayList<Integer> options = new ArrayList<Integer>();
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if (board.getBoard()[i][j].getIcon() != 'X' && board.getBoard()[i][j].getIcon() != 'O') {
					options.add(Character.getNumericValue(board.getBoard()[i][j].getIcon()));
				}
			}
		}
		checkCorners();
		checkEdges();
		if (canWin() == null) {
			switch (board.getCurrentTurn()) {
			case 1:		//Computer chooses a corner as their first turn
				Collections.shuffle(corners);
				played.add(corners.get(0));
				return corners.get(0);
			case 2:	//Computer goes second
				//Player chooses a corner or an edge
				if (corners.size() == 3 || edges.size() == 3) {
					played.add(5);
					return 5;
				}
				if (board.getBoard()[1][1].getIcon() == 'X' || board.getBoard()[1][1].getIcon() == 'O') {
					Collections.shuffle(corners);
					played.add(corners.get(0));
					return corners.get(0);
				}
			case 3:
				if (board.getBoard()[1][1].getIcon() == 'X' || board.getBoard()[1][1].getIcon() == 'O' || edges.size() == 3) {
					if (played.get(0) == 1) {
						return 9;
					} else if (played.get(0) == 3) {
						return 7;
					} else if (played.get(0) == 7) {
						return 3;
					} else {
						return 1;
					}
				}
				if (corners.size() == 2) {
					Collections.shuffle(corners);
					return corners.get(0);
				}
				System.out.println("Error, this code shouldn't be reached");
				Collections.shuffle(options);
				return options.get(0);
			case 4:
				if (canWin() == null) {
					if (corners.size() == 2) {
						Collections.shuffle(edges);
						return edges.get(0);
					}
					Collections.shuffle(options);
					return options.get(0);
				} else {
					return canWin();
				}
			case 5:
			case 6:
				if (canWin() == null) {
					Collections.shuffle(options);
					return options.get(0);
				} else {
					return canWin();
				}
			case 7:
			case 8:
				if (canWin() == null) {
					Collections.shuffle(options);
					return options.get(0);
				} else {
					return canWin();
				}
			case 9:
				return options.get(0);
			default:
				System.out.println("Error, incorrect turn!");
				break;
			}
		} else {
			return canWin();
		}
		return -1;
	}

	public Integer canWin() {
		Integer potentialWin = 0;
		for (int i = 0; i < board.getBoard().length; i++) {
			for (int j = 0; j < board.getBoard()[i].length; j++) {
				if ((board.getBoard()[i][j].getIcon() == board.getBoard()[1][1].getIcon())) {
					if (i == 0 && j == 0 && board.getBoard()[2][2].getIcon() == '9') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 9;
						} else {
							potentialWin = 9;
						}
					}
					if (i == 0 && j == 1 && board.getBoard()[2][1].getIcon() == '8') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 8;
						} else {
							potentialWin = 8;
						}
					}
					if (i == 0 && j == 2 && board.getBoard()[2][0].getIcon() == '7') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 7;
						} else {
							potentialWin = 7;
						}
					}
					if (i == 1 && j == 0 && board.getBoard()[1][2].getIcon() == '6') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 6;
						} else {
							potentialWin = 6;
						}
					}
					if (i == 1 && j == 2 && board.getBoard()[1][0].getIcon() == '4') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 4;
						} else {
							potentialWin = 4;
						}
					}
					if (i == 2 && j == 0 && board.getBoard()[0][2].getIcon() == '3') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 3;
						} else {
							potentialWin = 3;
						}
					}
					if (i == 2 && j == 1 && board.getBoard()[0][1].getIcon() == '2') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 2;
						} else {
							potentialWin = 2;
						}
					}
					if (i == 2 && j == 2 && board.getBoard()[0][0].getIcon() == '1') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 1;
						} else {
							potentialWin = 1;
						}
					}
				}
				if (i == 0 && j == 0) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[0][2].getIcon() && board.getBoard()[0][1].getIcon() == '2') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 2;
						} else {
							potentialWin = 2;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[0][1].getIcon() && board.getBoard()[0][2].getIcon() == '3') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 3;
						} else {
							potentialWin = 3;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][0].getIcon() && board.getBoard()[1][0].getIcon() == '4') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 4;
						} else {
							potentialWin = 4;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[1][0].getIcon() && board.getBoard()[2][0].getIcon() == '7') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 7;
						} else {
							potentialWin = 7;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][2].getIcon() && board.getBoard()[1][1].getIcon() == '5') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 5;
						} else {
							potentialWin = 5;
						}
					}
				}
				if (i == 0 && j == 1) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][1].getIcon() && board.getBoard()[1][1].getIcon() == '5') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 5;
						} else {
							potentialWin = 5;
						}
					}
				}
				if (i == 0 && j == 2) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[0][1].getIcon() && board.getBoard()[0][0].getIcon() == '1') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 1;
						} else {
							potentialWin = 1;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[1][2].getIcon() && board.getBoard()[2][2].getIcon() == '9') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 9;
						} else {
							potentialWin = 9;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][2].getIcon() && board.getBoard()[1][2].getIcon() == '6'){
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 6;
						} else {
							potentialWin = 6;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][0].getIcon() && board.getBoard()[1][1].getIcon() == '5') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 5;
						} else {
							potentialWin = 5;
						}
					}
				}
				if (i == 1 && j == 0) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[1][2].getIcon() && board.getBoard()[1][1].getIcon() == '5') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 5;
						} else {
							potentialWin = 5;
						}
					}
				}
				if (i == 2 && j == 0) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[1][0].getIcon() && board.getBoard()[0][0].getIcon() == '1') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 1;
						} else {
							potentialWin = 1;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][1].getIcon() && board.getBoard()[2][2].getIcon() == '9') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 9;
						} else {
							potentialWin = 9;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][2].getIcon() && board.getBoard()[2][1].getIcon() == '8'){
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 8;
						} else {
							potentialWin = 8;
						}
					}
				}
				if (i == 2 && j == 2) {
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[2][1].getIcon() && board.getBoard()[2][0].getIcon() == '7') {
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 7;
						} else {
							potentialWin = 7;
						}
					}
					if (board.getBoard()[i][j].getIcon() == board.getBoard()[1][2].getIcon() && board.getBoard()[0][2].getIcon() == '3'){
						if (board.getBoard()[i][j].getIcon() == this.icon) {
							return 3;
						} else {
							potentialWin = 3;
						}
					}
				}
			}
		}

		if (potentialWin != 0) {
			return potentialWin;
		} else {
			return null;
		}
	}
	public void checkCorners() {
		corners = new ArrayList<Integer>();
		if (board.getBoard()[0][0].getIcon() != 'X' && board.getBoard()[0][0].getIcon() != 'O') {
			corners.add(Character.getNumericValue(board.getBoard()[0][0].getIcon()));
		}
		if (board.getBoard()[0][2].getIcon() != 'X' && board.getBoard()[0][2].getIcon() != 'O') {
			corners.add(Character.getNumericValue(board.getBoard()[0][2].getIcon()));
		}
		if (board.getBoard()[2][0].getIcon() != 'X' && board.getBoard()[2][0].getIcon() != 'O') {
			corners.add(Character.getNumericValue(board.getBoard()[2][0].getIcon()));
		}
		if (board.getBoard()[2][2].getIcon() != 'X' && board.getBoard()[2][2].getIcon() != 'O') {
			corners.add(Character.getNumericValue(board.getBoard()[2][2].getIcon()));
		}
	}

	public void checkEdges() {
		edges = new ArrayList<Integer>();
		if (board.getBoard()[0][1].getIcon() != 'X' && board.getBoard()[0][1].getIcon() != 'O') {
			edges.add(Character.getNumericValue(board.getBoard()[0][1].getIcon()));
		}
		if (board.getBoard()[1][0].getIcon() != 'X' && board.getBoard()[1][0].getIcon() != 'O') {
			edges.add(Character.getNumericValue(board.getBoard()[1][0].getIcon()));
		}
		if (board.getBoard()[1][2].getIcon() != 'X' && board.getBoard()[1][2].getIcon() != 'O') {
			edges.add(Character.getNumericValue(board.getBoard()[1][2].getIcon()));
		}
		if (board.getBoard()[2][1].getIcon() != 'X' && board.getBoard()[2][1].getIcon() != 'O') {
			edges.add(Character.getNumericValue(board.getBoard()[2][1].getIcon()));
		}
	}

}
