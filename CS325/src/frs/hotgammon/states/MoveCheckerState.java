package frs.hotgammon.states;

import java.util.ArrayList;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.DiceRollState;
import frs.hotgammon.states.GameState;
import frs.hotgammon.tests.stub.*;
import frs.hotgammon.common.*;

public class MoveCheckerState implements GameState {

	private GameImpl game;

	public MoveCheckerState(Game game) {
		this.game = ((GameImpl) game);
	}

	@Override
	public boolean move(Location from, Location to) {

		if (game.diceThrownEqual()) {
			game.movesDoubled = true;

		}

		if (game.getRulesFactory().createMoveValidator().isValid(from, to)) {

			game.currentDistanceTravelled = Math.abs(Location
					.distance(from, to));

			if (game.numberOfMovesMade == 3) {
				game.movesDoubled = false;
			}

			if (game.getCount(to) > 0
					&& game.getColor(to) != game.getPlayerInTurn()) {
				game.sendToTheBar(from, to);
			}

			game.getBoard().move(from, to);
			game.updateDiceValuesLeft();
			game.numberOfMovesMade++;

			if (noMovesLeft()) {

				for (int i = 0; i < game.getGameObserversList().size(); i++) {
					game.getGameObserversList().get(i)
							.changeStatusField("Roll dice to move!");
				}

				game.setState(new DiceRollState(game));
			}

			for (int i = 0; i < game.getGameObserversList().size(); i++) {
				game.getGameObserversList().get(i).checkerMove(from, to);
			}

			if (game.numberOfMovesMade == 2 && game.movesDoubled) {

				game.diceValuesLeft = new int[2];
				game.diceValuesLeft[0] = game.diceThrown()[0];
				game.diceValuesLeft[1] = game.diceThrown()[1];

				

			}

			for (int i = 0; i < game.getGameObserversList().size(); i++) {
				game.getGameObserversList().get(i).checkerMove(from, to);
			}

			return true;
		}
		return false;

	}

	private boolean noMovesLeft() {

		return (game.getNumberOfMovesLeft() <= 0);
	}

	@Override
	public void nextTurn() {
		System.out.println("You have moves left, you cannot perform nextTurn");

		for (int i = 0; i < game.getGameObserversList().size(); i++) {
			game.getGameObserversList()
					.get(i)
					.changeStatusField(
							"You cannot throw dice now. "
									+ game.getPlayerInTurn() + " has "
									+ game.getNumberOfMovesLeft());
		}

	}

	public String toString() {

		return "MoveCheckerState";
	}

}