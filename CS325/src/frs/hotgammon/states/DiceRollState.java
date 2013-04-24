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
		
		for (int i = 0; i < game.getGameObserversList().size(); i++) {
			game.getGameObserversList().get(i).changeStatusField("You cannot move now. You have 0 moves left. It is " + game.getPlayerInTurn() + " turn");
		}
		return false;
	}

	@Override
	public void nextTurn() {
		

			game.numberOfMovesMade = 0;
			game.getRollDeterminer().rollDice(game.turnNumber);
			game.diceValuesLeft = game.diceThrown().clone();
	
			if (game.getPlayerInTurn()==Color.NONE) {
				game.playerInTurn = game.getStartingPlayer();
				game.changePlayer = false;
			}
			
			
			game.getRulesFactory().createTurnDeterminer().nextTurn(game.changePlayer);
			game.turnNumber++;
	
			for (int i = 0; i < game.getGameObserversList().size(); i++) {
				game.getGameObserversList().get(i).diceRolled(game.diceThrown());
			}
			
			if(game.playerInTurn!=Color.NONE){
				game.setState(new MoveCheckerState(game));
				for (int i = 0; i < game.getGameObserversList().size(); i++) {
					game.getGameObserversList().get(i).changeStatusField("Dice thrown are [" + game.diceThrown()[0] +"," + game.diceThrown()[1] + "]." + " It is " + game.getPlayerInTurn() + " turn.");
				}
			}
			
			else {
				for (int i = 0; i < game.getGameObserversList().size(); i++) {
					game.getGameObserversList().get(i).changeStatusField("Throw dice again to determine who starts the game.");
				}

			}
			
			
		
	}
	

	public String toString() {

		return "DiceRollState";
	}

}
