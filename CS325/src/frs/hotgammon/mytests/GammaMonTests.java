package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.rules.GammaMon;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;


public class GammaMonTests {

	GameImpl game;

	@Before
	public void setUp() {
		game = new GameImpl(new GammaMon());
		game.newGame();
	}

	@Test
	public void shouldHaveNoWinnerAfter6Turns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.winner() == Color.NONE);
	}

	@Test
	public void blackShouldBeTheWinner() {

		game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.BLACK, Location.B_BEAR_OFF),
				new Placement(Color.RED, Location.B1) });
		game.nextTurn();
		assertTrue(game.winner() == Color.BLACK);
	}

	@Test
	public void redShouldNotBeTheWinnerWhenNotOnCheckersOnTheBar() {

		game.configure(new Placement[] {
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.R_BEAR_OFF),
				new Placement(Color.RED, Location.B1) });
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.winner() == Color.BLACK);
	}

}