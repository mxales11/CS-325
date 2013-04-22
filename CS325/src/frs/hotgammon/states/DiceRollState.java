package frs.hotgammon.states;

import java.util.ArrayList;

import assignments.chap5.breakthrough.Breakthrough.Player;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.GameState;
import frs.hotgammon.states.MoveCheckerState;
import frs.hotgammon.tests.stub.*;

public class DiceRollState implements GameState {

	private GameImpl game;

	public DiceRollState(Game game) {
		this.game = (GameImpl) game;
	}

	@Override
	public boolean move(Location from, Location to) {
		System.out.println("You cannot move now. You have 0 moves left");
		return false;
	}

	@Override
	public void nextTurn() {
		
		
		if(game.turnNumber!= 0 && game.getPlayerInTurn()==Color.NONE){
			
			game.nextTurn();
			
		}
		else{
			game.numberOfMovesMade = 0;
			game.getRollDeterminer().rollDice(game.turnNumber);
			game.diceValuesLeft = game.diceThrown().clone();
	
			if (game.turnNumber == 0) {
				game.playerInTurn = game.getStartingPlayer();
	
			}
	
			game.getRulesFactory().createTurnDeterminer().nextTurn(game.changePlayer);
			game.turnNumber++;
	
			for (int i = 0; i < game.getGameObserversList().size(); i++) {
				game.getGameObserversList().get(i).diceRolled(game.diceThrown());
			}
			game.setState(new MoveCheckerState(game));
		}
	}
	

	public String toString() {

		return "DiceRollState";
	}

}
