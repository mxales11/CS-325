package frs.hotgammon.states;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;

public class MoveCheckerState implements GameState {
	
	private Game game;
	
	public MoveCheckerState(Game game) {
		this.game = game;
	}

	@Override
	public boolean move(Location from, Location to) {
		// TODO Auto-generated method stub
		
		//put from game method
		return false;
	}

	@Override
	public void nextTurn() {
		System.out.println("You have moves left, you cannot perform nextTurn");
		
	}
	
	public String toString() {
		
		return "MoveCheckerState";
	}
	
	

}
