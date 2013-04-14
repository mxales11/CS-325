package frs.hotgammon.states;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;

public class GameStateImpl implements GameState {
	
	private Game game;
	private GameState diceRollState;
	private GameState moveCheckerState;
	private GameState currentState;

	public GameStateImpl(Game game) {
		this.game = game;
		this.moveCheckerState = new MoveCheckerState(game);
		this.diceRollState = new DiceRollState(game);
		
		//change current State to roll dice first
		this.currentState = moveCheckerState;
	}

	
	@Override
	public boolean move(Location from, Location to) {
		return currentState.move(from, to);
	}

	@Override
	public void nextTurn() {
		currentState.nextTurn();
	}
	
	public String toString() {
		
		return currentState.toString();
	}

}
