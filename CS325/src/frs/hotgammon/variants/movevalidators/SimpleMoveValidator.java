package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
public class SimpleMoveValidator implements MoveValidator {

	private Game game;

	public SimpleMoveValidator() {

	}

	public boolean coreValidMove(Location from) {
		return thereIsNoWinner() && playerMovesHisChecker(from) && dieValuesare12345or6();
	}
	
	public boolean movesDoubled() {
		return ((GameImpl)game).getMovesDoubled();
	}

	private boolean playerMovesHisChecker(Location from) {
		return game.getPlayerInTurn() == game.getColor(from);
	}
	
	private boolean thereIsNoWinner(){
		return game.winner()==Color.NONE;
	}

	
	private boolean checkerIsMovedToEmptyLocation(Location to) {
		return (game.getColor(to) == Color.NONE);
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

	protected boolean checkerIsPlacedOnTheSameColor(Location to) {
		return (game.getColor(to) == game.getPlayerInTurn());
	}

	protected boolean notMoreThanTwoMoves() {
		return (game.getNumberOfMovesLeft() > 0 && game.getNumberOfMovesMade() < 2);

	}
	
	public boolean dieValuesare12345or6(){
		
		return (game.diceThrown()[0]>0) && (game.diceThrown()[1]<7) ;
	}

	@Override
	public boolean isValid(Location from, Location to) {
		return coreValidMove(from)
				&& (notMoreThanTwoMoves() || (movesDoubled()))
				&& (checkerIsMovedToEmptyLocation(to) || checkerIsPlacedOnTheSameColor(to));
	}

}
