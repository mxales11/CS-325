package frs.hotgammon.states;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.tests.stub.DiceRollStateStub;
import frs.hotgammon.tests.stub.MoveCheckerStateStub;

public class GameStateImpl implements GameState {
	
	private Game game;
	private GameState diceRollState;
	private GameState moveCheckerState;
	private GameState currentState;

	public GameStateImpl(Game game) {
		this.game = game;
		this.moveCheckerState = new MoveCheckerState(game);
		this.diceRollState = new DiceRollState(game);
		this.currentState = diceRollState;	
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
