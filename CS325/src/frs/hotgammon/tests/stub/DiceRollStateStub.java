package frs.hotgammon.tests.stub;

import java.util.ArrayList;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.GameState;
import frs.hotgammon.states.MoveCheckerState;
import frs.hotgammon.tests.stub.*;

public class DiceRollStateStub implements GameState {

	private StubGame1 game;

	public DiceRollStateStub(Game game) {
		this.game = (StubGame1) game;
	}

	@Override
	public boolean move(Location from, Location to) {
		return false;
	}

	@Override
	public void nextTurn() {
		
		game = ((StubGame1) game);
		game.turn++;
		((StubGame1) game).maxNumberOfMoves();
		((StubGame1) game).tictac = !((StubGame1) game).tictac;
		System.out.println("nextTurn: " + ((StubGame1) game).getTurn());
		ArrayList<GameObserver> observers = game.gameObserversList;

		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).diceRolled(((StubGame1) game).diceThrown());
		}
		game.setState(new MoveCheckerStateStub(game));
		

	}

	public String toString() {

		return "DiceRollState";
	}

}