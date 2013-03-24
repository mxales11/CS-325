package frs.hotgammon.mytests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.rules.DeltaMon;
import frs.hotgammon.variants.turndeterminers.AceyDeuceyTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class DeltaMonTests {

	Game game;

	@Before
	public void setUp() {
		game = new GameImpl(new DeltaMon());
		game.newGame();
	}

	@Test
	public void blackPlayerShouldReceiveAdditionalTurnAfterThrowing12or21() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R1, Location.R3));
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
	}

	@Test
	public void blackShouldReceiveAdditionalTurnAfterThrowing12or21SecondTime() {

		game.nextTurn();
		game.nextTurn();

		game.nextTurn();

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);

	}

	@Test
	public void shouldHaveDieValuesLeft34AfterThrowing12() {

		game.nextTurn();
		game.nextTurn();

		assertTrue(game.diceThrown()[0] == 3 && game.diceThrown()[1] == 4);

	}

	@Test
	public void shouldHaveRedInTurnAfterTwoTurnsOfBlack() {

		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

}