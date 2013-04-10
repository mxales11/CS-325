package frs.hotgammon.variants.rules;

import frs.hotgammon.RulesFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.framework.Game;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.randomdicegenerators.ReallyRandomDiceGenerator;
import frs.hotgammon.variants.rolldeterminers.RandomRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class EpsilonMon implements RulesFactory {

	private Game game;

	public void setGame(Game game) {
		this.game = game;

	}

	public MoveValidator createMoveValidator() {

		MoveValidator moveValidator = new SimpleMoveValidator();
		moveValidator.setGame(game);
		return moveValidator;
	}

	public WinnerDeterminer createWinnerDeterminer() {

		WinnerDeterminer winnerDeterminer = new SixMoveWinnerDeterminer();
		winnerDeterminer.setGame(game);
		return winnerDeterminer;
	}

	public TurnDeterminer createTurnDeterminer() {

		TurnDeterminer turnDeterminer = new AlternatingTurnDeterminer();
		turnDeterminer.setGame(game);
		return turnDeterminer;
	}
	

	public RollDeterminer createRollDeterminer() {

		RollDeterminer rollDeterminer = new RandomRollDeterminer(new ReallyRandomDiceGenerator());
		rollDeterminer.setGame(game);
		return rollDeterminer;
	}

	

}
