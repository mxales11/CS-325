package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.Board;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.WinnerDeterminer;

public class BearOffWinnerDeterminer implements WinnerDeterminer {

	Game game;

	@Override
	public boolean isWinner(int i) {
		return allCheckersOnTheBar();
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	private boolean allCheckersOnTheBar() {

		Board board = game.getBoard();

		for (int i = 0; i < board.size(); i++) {
			if (!(board.get(i).occupants == 0 || board.get(i).color != game.getPlayerInTurn() || i == Location.R_BEAR_OFF.ordinal() || i == Location.B_BEAR_OFF
					.ordinal())) {
				return false;
			}
		}
		return true;

	}

}
