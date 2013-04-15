package frs.hotgammon.tests.stub;

import java.util.ArrayList;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.DiceRollState;
import frs.hotgammon.states.GameState;
import frs.hotgammon.tests.stub.*;

public class MoveCheckerStateStub implements GameState {

	private StubGame1 game;

	public MoveCheckerStateStub(Game game) {
		this.game = (StubGame1) game;
	}

	@Override
	public boolean move(Location from, Location to) {

		if (to != Location.B3 && to != Location.R3 && to != null) {
			System.out.println("GAME: moving from " + from + " to " + to);
			if (from == game.loneRiderHere1) {
				game.loneRiderHere1 = to;
			} else if (from == game.loneRiderHere2) {
				game.loneRiderHere2 = to;
			}
		}

		else if (to == null) {

			System.out.println("You cannot move to empty location");
			return false;
		}

		else if (Location.distance(from, to) == 0) {

			System.out.println("You cannot move to itself");
			return false;
		} else {
			System.out
					.println("GAME: Moving to B3/R3 is illegal (testing purposes)");
			return false;
		}
		game.movesLeft--;

		if (noMovesLeft()) {

			System.out.println("NUMBER OF MOVES LEFT IS " + game.movesLeft);
			game.setState(new DiceRollStateStub(game));

		}

		for (int i = 0; i < game.gameObserversList.size(); i++) {
			game.gameObserversList.get(i).checkerMove(from, to);
		}

		return true;
	}

	private boolean noMovesLeft() {

		return (game.movesLeft == 0);
	}

	@Override
	public void nextTurn() {
		System.out.println("You have moves left, you cannot perform nextTurn");

	}

	public String toString() {

		return "MoveCheckerState";
	}

}