package frs.hotgammon.states;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;

public class DiceRollState implements GameState {

	private Game game;

	public DiceRollState(Game game) {
		this.game = game;
	}

	@Override
	public boolean move(Location from, Location to) {
		System.out.println("You cannot move now. You have 0 moves left");
		return false;
	}

	@Override
	public void nextTurn() {
		//move code from game
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
	
		return "DiceRollState";
	}

}
