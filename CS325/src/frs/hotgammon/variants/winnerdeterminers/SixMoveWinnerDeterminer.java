package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.framework.Game;

public class SixMoveWinnerDeterminer implements WinnerDeterminer {

	private Game game;
	public static final int NUMBER_OF_ROLLS_IN_GAME =6;
	
	@Override
	public boolean isWinner(int turnNumber) {
		if (turnNumber == NUMBER_OF_ROLLS_IN_GAME) {
			return true;
		}
		return false;
	}
	@Override
	public void setGame(Game game) {
		this.game = game;
	}
	

}
