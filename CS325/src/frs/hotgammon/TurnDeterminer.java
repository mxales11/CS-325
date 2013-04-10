package frs.hotgammon;

import frs.hotgammon.framework.Game;

public interface TurnDeterminer {
	
	public void nextTurn(boolean changePlayer);
	public void setGame(Game game);

}
