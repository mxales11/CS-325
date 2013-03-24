package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.rules.ZetaMon;

public class ZetaMonTests {
	GameImpl game;

	@Before
	public void setUp() {
		game = new GameImpl(new ZetaMon());
		game.newGame();
	}

	@Test
	public void blackHasCheckersOnR1R2R3() {

		assertTrue(game.getBoard().get(Location.R1.ordinal()).color == Color.BLACK);
		assertTrue(game.getBoard().get(Location.R2.ordinal()).color == Color.BLACK);
		assertTrue(game.getBoard().get(Location.R3.ordinal()).color == Color.BLACK);
	}

	@Test
	public void whiteHasOnly3CheckersOnB1B2B3() {
		assertTrue(game.getBoard().get(Location.B1.ordinal()).color == Color.RED);
		assertTrue(game.getBoard().get(Location.B2.ordinal()).color == Color.RED);
		assertTrue(game.getBoard().get(Location.B3.ordinal()).color == Color.RED);
	}

	@Test
	public void thereAreOnly6CheckersOnTheBoard() {

		assertTrue(this.countCheckersOnTheBoard() == 6);
	}

	@Test
	public void thereAreOnly6CheckersOnTheBoardAfterResetting() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		game.newGame();
		assertTrue(this.countCheckersOnTheBoard() == 6);

	}

	@Test
	public void checkersShouldBeOnCorrectLocationsAfterResetting() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		game.newGame();
		assertTrue(game.getBoard().get(Location.R1.ordinal()).color == Color.BLACK);
		assertTrue(game.getBoard().get(Location.R2.ordinal()).color == Color.BLACK);
		assertTrue(game.getBoard().get(Location.R3.ordinal()).color == Color.BLACK);
		assertTrue(game.getBoard().get(Location.B1.ordinal()).color == Color.RED);
		assertTrue(game.getBoard().get(Location.B2.ordinal()).color == Color.RED);
		assertTrue(game.getBoard().get(Location.B3.ordinal()).color == Color.RED);

	}

	private int countCheckersOnTheBoard() {

		int count = 0;

		for (int i = 0; i < game.getBoard().size(); i++) {

			if (game.getBoard().get(i).color != Color.NONE) {
				count++;
			}
		}
		return count;

	}

}
