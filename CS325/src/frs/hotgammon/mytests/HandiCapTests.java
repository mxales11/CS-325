package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Location;
import frs.hotgammon.variants.rules.AlphaMon;
import frs.hotgammon.variants.rules.BetaMon;
import frs.hotgammon.variants.rules.HandiCapMon;

public class HandiCapTests {

	GameImpl game;

	@Before
	public void setUp() {
		game = new GameImpl(new HandiCapMon(new AlphaMon(), new BetaMon()));
		game.newGame();
	}

	@Test
	public void blackCanMoveInEitherDirection() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R11));
		assertTrue(game.move(Location.R12, Location.R1));

	}

	@Test
	public void whiteCanMoveOnlyInOneDirection() {

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R9));
		assertTrue(game.move(Location.R8, Location.R5));
	}

	@Test
	public void blackCanMoveAnyDiceValue() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R11));
		assertTrue(game.move(Location.R1, Location.B2));

	}

	@Test
	public void whiteCanMoveOnlyDiceValue() {

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.B12, Location.R11));
		assertFalse(game.move(Location.B12, Location.R8));
		assertFalse(game.move(Location.B12, Location.R2));
		assertTrue(game.move(Location.B12, Location.R10));
		assertTrue(game.move(Location.B12, Location.R9));
	}

	@Test
	public void eachPlayerPlayesAccordingToHisRules() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R11));
		assertTrue(game.move(Location.R1, Location.R10));
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
		assertFalse(game.move(Location.R6, Location.R9));
		assertTrue(game.move(Location.R8, Location.R5));
		assertFalse(game.move(Location.R6, Location.R5));
		assertTrue(game.move(Location.B1, Location.B5));

	}
	
	@Test
	public void afterResettingGameEachPlayerPlayesAccordingToHisRules(){

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R11));
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R11));
		assertTrue(game.move(Location.R1, Location.R10));
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
		assertFalse(game.move(Location.R6, Location.R9));
		assertTrue(game.move(Location.R8, Location.R5));
		assertFalse(game.move(Location.R6, Location.R5));
		assertTrue(game.move(Location.B1, Location.B5));
	}

}