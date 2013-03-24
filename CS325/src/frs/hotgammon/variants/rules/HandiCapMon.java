package frs.hotgammon.variants.rules;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.RulesFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;

public class HandiCapMon implements RulesFactory {

	private AlphaMon alphaMon;
	private BetaMon betaMon;
	private RulesFactory currentRules;
	private Game game;

	public HandiCapMon(AlphaMon alphaMon, BetaMon betaMon) {

		this.alphaMon = alphaMon;
		this.betaMon = betaMon;
		this.currentRules = alphaMon;
	}

	
	public void setGame(Game game) {
		
		this.game = game;
		
		currentRules = (game.getPlayerInTurn() == Color.BLACK || game.getPlayerInTurn() == Color.NONE) ? alphaMon : betaMon;
		((GameImpl)game).setMoveValidator(currentRules.createMoveValidator());
		currentRules.setGame(game);
		
		
	}

	public MoveValidator createMoveValidator() {

		return currentRules.createMoveValidator();
	}

	public WinnerDeterminer createWinnerDeterminer() {
		return currentRules.createWinnerDeterminer();

	}

	public TurnDeterminer createTurnDeterminer() {

		return currentRules.createTurnDeterminer();

	}

	public RollDeterminer createRollDeterminer() {

		return currentRules.createRollDeterminer();
	}

}
