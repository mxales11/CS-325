package frs.hotgammon.states;

import frs.hotgammon.framework.Location;

public interface GameState {

	public boolean move(Location from, Location to);

	public void nextTurn();
	
}