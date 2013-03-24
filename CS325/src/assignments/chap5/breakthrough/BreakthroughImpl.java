package assignments.chap5.breakthrough;

public class BreakthroughImpl implements Breakthrough {

	private Board board;
	private Player currentPlayer;
	private Player winner = null;

	public BreakthroughImpl() {

		board = new Board();
		currentPlayer = Player.WHITE;

	}

	public Board getBoard() {
		return board;
	}

	public Piece getPieceAt(int row, int column) {
		return board.getBoard()[row][column];

	}

	public Player getCurrentPlayer() {

		return currentPlayer;
	}

	private void switchPlayers() {

		if (currentPlayer == Player.WHITE) {
			currentPlayer = Player.BLACK;
		} else {
			currentPlayer = Player.WHITE;
		}
	}

	public boolean whitePlayerWon(int toRow) {
		return (currentPlayer == Player.WHITE && toRow == 0);
	}

	public boolean blackPlayerWon(int toRow) {
		return (currentPlayer == Player.BLACK && toRow == 7);
	}

	public void checkForWinner(int toRow) {

		if (whitePlayerWon(toRow) || blackPlayerWon(toRow)) {
			winner = currentPlayer;
		}
	}

	public boolean playerMovesEqualTypePiece(int fromRow, int fromColumn) {

		if (playerEqualsPiece(fromRow, fromColumn)) {
			return true;
		} else {
			throw new IllegalMoveException(currentPlayer.toString()
					+ " player cannot move "
					+ getPieceAt(fromRow, fromColumn).toString() + "piece");
		}
	}

	public boolean playerEqualsPiece(int row, int column) {

		return (getCurrentPlayer().toString().equals(getPieceAt(row, column)
				.toString()));
	}

	public boolean playerDoesNotMoveIntoFieldOccupiedByEqualPiece(int toRow,
			int toColumn) {
		if (!playerEqualsPiece(toRow, toColumn)) {
			return true;
		} else {
			throw new IllegalMoveException(getCurrentPlayer().toString()
					+ " player cannot move into "
					+ getPieceAt(toRow, toColumn).toString() + "piece");
		}

	}

	private boolean verticalMove(int fromColumn, int toColumn) {
		return (fromColumn == toColumn);

	}

	private boolean diagonalMove(int fromColumn, int toColumn) {
		return (fromColumn == toColumn - 1 || fromColumn == toColumn + 1);

	}

	private boolean oneForwardMove(int fromRow, int toRow) {
		return ((getCurrentPlayer() == Player.WHITE && fromRow == toRow + 1) || (getCurrentPlayer() == Player.BLACK && fromRow == toRow - 1));

	}

	private boolean verticalAndOneForwardMove(int fromRow, int fromColumn,
			int toRow, int toColumn) {
		return verticalMove(fromColumn, toColumn)
				&& oneForwardMove(fromRow, toRow);

	}

	private boolean diagonalAndOneForwardMove(int fromRow, int fromColumn,
			int toRow, int toColumn) {
		return (diagonalMove(fromColumn, toColumn) && oneForwardMove(fromRow,
				toRow));

	}

	public boolean verticalOrDiagonalMove(int fromRow, int fromColumn,
			int toRow, int toColumn) throws IllegalMoveException {

		if (verticalAndOneForwardMove(fromRow, fromColumn, toRow, toColumn)
				|| diagonalAndOneForwardMove(fromRow, fromColumn, toRow,
						toColumn)) {
			return true;
		} else {
			throw new IllegalMoveException(
					"This is neither vertical nor diagonal one-step move forward");
		}
	}

	public boolean isMoveValid(int fromRow, int fromColumn, int toRow,
			int toColumn) {

		return playerMovesEqualTypePiece(fromRow, fromColumn)
				&& playerDoesNotMoveIntoFieldOccupiedByEqualPiece(toRow,
						toColumn)
				&& verticalOrDiagonalMove(fromRow, fromColumn, toRow, toColumn)
				&& doesNotCrossTheBoard(toRow, toColumn);
	};

	private boolean doesNotCrossTheBoardVertically(int toRow) {
		return (toRow < board.getBoard()[0].length && toRow> -1 ) ;
			
	}

	private boolean doesNotCrossTheBoardHorizontally(int toColumn) {
		return (toColumn < board.getBoard().length && toColumn> -1 ) ;
	}

	private boolean doesNotCrossTheBoard(int toRow, int toColumn) throws ArrayIndexOutOfBoundsException{
		if (doesNotCrossTheBoardVertically(toRow) && doesNotCrossTheBoardHorizontally(toColumn)) {
			return true;
		}
		throw new ArrayIndexOutOfBoundsException("You crossed the board");
	}

	public void move(int fromRow, int fromColumn, int toRow, int toColumn)
			throws IllegalMoveException {

		if (isMoveValid(fromRow, fromColumn, toRow, toColumn)) {
			checkForWinner(toRow);
			switchPlayers();
			switchPieceTypes(fromRow, fromColumn, toRow, toColumn);

		}

	}

	public void switchPieceTypes(int fromRow, int fromColumn, int toRow,
			int toColumn) {

		Piece temp = board.getBoard()[fromRow][fromColumn];
		board.getBoard()[fromRow][fromColumn] = Piece.NONE;
		board.getBoard()[toRow][toColumn] = temp;

	}

	@Override
	public Player getWinner() {

		return winner;
	}
}
