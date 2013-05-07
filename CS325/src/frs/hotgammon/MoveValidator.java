package frs.hotgammon;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;

public interface MoveValidator {

	
	public boolean isValid(Location from, Location to);
	public void setGame(Game game);
	public int getNumberOfMovesLeft();
	public boolean noMovePossible();
	
}
