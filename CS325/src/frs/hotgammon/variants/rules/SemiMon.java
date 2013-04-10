package frs.hotgammon.variants.rules;

import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.RulesFactory;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.framework.Game;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.randomdicegenerators.ReallyRandomDiceGenerator;
import frs.hotgammon.variants.rolldeterminers.RandomRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;

public class SemiMon implements RulesFactory {
	
	private Game game;

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame(){
		
		return game;
	}
	
	public MoveValidator createMoveValidator() {

		MoveValidator moveValidator = new CompleteMoveValidator();
		moveValidator.setGame(game);
		return moveValidator;
	}

	public WinnerDeterminer createWinnerDeterminer() {

		WinnerDeterminer winnerDeterminer = new BearOffWinnerDeterminer();
		winnerDeterminer.setGame(game);
		return winnerDeterminer;
	}

	public TurnDeterminer createTurnDeterminer() {

		AlternatingTurnDeterminer alternatingTurnDeterminer = new AlternatingTurnDeterminer();
		alternatingTurnDeterminer.setGame(game);
		return alternatingTurnDeterminer;

	}


	public RollDeterminer createRollDeterminer() {

		RollDeterminer rollDeterminer = new RandomRollDeterminer(new ReallyRandomDiceGenerator());
		rollDeterminer.setGame(game);
		return rollDeterminer;
	}
	
	


}
