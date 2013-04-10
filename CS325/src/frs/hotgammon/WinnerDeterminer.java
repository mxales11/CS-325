package frs.hotgammon;

import frs.hotgammon.framework.Game;


public interface WinnerDeterminer {

	public boolean isWinner(int numberOfTurns);
	public void setGame(Game game);
		
}
