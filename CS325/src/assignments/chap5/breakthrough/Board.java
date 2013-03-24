package assignments.chap5.breakthrough;

import assignments.chap5.breakthrough.Breakthrough.Piece;

public class Board {

	private static final int NUMBER_OF_ROWS = 8;
	private static final int NUMBER_OF_COLUMNS = 8;
	private static final int NUMBER_OF_ROWS_FILLED_WITH_PIECES_OF_EACH_TYPE = 2;
	private Piece[][] board;

	public Board() {
		board = new Piece[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		fillBoard();
	}

	public Piece[][] getBoard() {
		return board;
	}

	private void fillBoard() {

		fillWithWhites();
		fillWithBlacks();
		leaveEmpty();

	}

	private void fillWithBlacks() {
		for (int i = 0; i < NUMBER_OF_ROWS_FILLED_WITH_PIECES_OF_EACH_TYPE; i++) {
			for (int k = 0; k < NUMBER_OF_COLUMNS; k++) {
				board[i][k] = Piece.BLACK;
			}
		}

	}

	private void fillWithWhites() {

		for (int i = NUMBER_OF_ROWS
				- NUMBER_OF_ROWS_FILLED_WITH_PIECES_OF_EACH_TYPE; i < NUMBER_OF_ROWS; i++) {
			for (int k = 0; k < NUMBER_OF_COLUMNS; k++) {
				board[i][k] = Piece.WHITE;
			}
		}

	}

	private void leaveEmpty() {

		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			for (int k = 0; k < NUMBER_OF_COLUMNS; k++) {
				if (board[i][k] == null) {
					board[i][k] = Piece.NONE;
				}
			}
		}

	}
}
