package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.rules.AlphaMon;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;
import frs.hotgammon.common.GameImpl.Placement;

public class AlphaMonTests {

	GameImpl game;

	
	@Before
	public void setUp() {
		game = new GameImpl(new AlphaMon());
		game.newGame();
	}

	@Test
	public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
		game.nextTurn();
		game.move(Location.B6, Location.B2);
		game.nextTurn();
		assertFalse(game.move(Location.B1, Location.B2));
	}

	@Test
	public void shouldBeAbleToPlaceTheSameCheckersInOneContainer() {
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R8, Location.R6));
	}

	@Test
	public void shouldBeAbleToRemovePlayerOfRightColor() {
		game.nextTurn();
		game.nextTurn();
		assertTrue("Should be able to remove Red pieces.",
				game.move(Location.B1, Location.B2));
	}

	@Test
	public void shouldBeAbleToPlaceTwoSameColorPiecesOnSameSquare() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldNotBeAbleToMakeThreeMoves() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R2, Location.R4));
		assertFalse(game.move(Location.R3, Location.R4));
	}

	@Test
	public void shouldBeZeroMovesAfterTwoConsecutive() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		assertEquals(0, game.getNumberOfMovesLeft());
	}

	@Test
	public void shouldBeNoMovesLeftAfterMovingTwoBlackCheckersFromR1toR2() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);

		assertEquals(0, game.getNumberOfMovesLeft());
	}

	@Test
	public void shouldEndGameAfterSixTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}

	@Test
	public void shouldEndGameAfter6Rolls() {

		game.nextTurn();
		game.nextTurn();
		assertEquals("should not be a winner ", Color.NONE, game.winner());
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("should not be a winner ", Color.RED, game.winner());
	}

	@Test
	public void redWinsAfter6Turns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.winner() == Color.NONE);
		game.nextTurn();
		assertTrue(game.winner() == Color.RED);
	}

	@Test
	public void shouldChangeThePlayerAfterEachTurn() {

		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void redShouldBeInTurn() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void noMovesLegalAfterThereIsAWinner() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.winner() == Color.RED);
		assertFalse(game.move(Location.R6, Location.R11));
	}

	@Test
	public void redPlayerIsInTurnAfterNextTurnIsInvokedTheSecondTime() {

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);

	}

	@Test
	public void shouldBeRedTurnAfter2NextTurns() {
		game.nextTurn();
		game.nextTurn();
		assertEquals(game.getPlayerInTurn(), Color.RED);
		assertEquals(game.diceThrown()[0], 3);
		assertEquals(game.diceThrown()[1], 4);
	}

	@Test
	public void shouldHaveRedIsWinner() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}

	@Test
	public void shouldNotSendToTheBar() {

		game.configure(new Placement[] { new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();

		assertFalse(game.move(Location.R1, Location.R2));

	}

	@Test
	public void shouldBeRedWinnerAfterSixTurns() {
		for (int i = 0; i < 6; i++) {
			game.nextTurn();
		}
		assertTrue(game.winner() == Color.RED);
	}

}
