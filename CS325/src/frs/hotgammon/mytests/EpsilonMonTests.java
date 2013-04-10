package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.mytests.dicegenerators.DiceGenerator124433;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator2134;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator44;
import frs.hotgammon.variants.rules.EpsilonMon;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;


public class EpsilonMonTests {

	Game game;

	@Before
	public void setUp() {
		game = new GameImpl(new EpsilonMon());
		game.newGame();
		game.getRollDeterminer()
				.setRandomDiceGenerator(new DiceGenerator2134());
	}

	@Test
	public void diceThrownShouldBe21() {

		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 2 && game.diceThrown()[1] == 1);
	}

	@Test
	public void diceValuesLeftShouldBe16() {

		game.nextTurn();
		assertTrue(game.diceValuesLeft()[0] == 2
				&& game.diceValuesLeft()[1] == 1);
	}

	@Test
	public void diceThrownDoNotChangeOnMethodInvocation() {

		game.nextTurn();

		game.diceThrown();
		game.diceThrown();
		game.diceThrown();
		assertTrue(game.diceThrown()[0] == 2 && game.diceThrown()[1] == 1);
	}

	@Test
	public void redPlayerShouldStartTheGameWhen21Thrown() {

		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void shouldSwitchTurnsCorrectlyWhenRedPlayerStarts() {

		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.BLACK);
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);
	}

	@Test
	public void shouldHave2MovesWhenThrown21() {

		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B3));
		assertTrue(game.move(Location.B1, Location.B3));
		assertFalse(game.move(Location.B3, Location.B5));
		assertFalse(game.move(Location.B3, Location.B5));

	}

	@Test
	public void redShouldHave4MovesWhenThrown44() {

		game.getRollDeterminer().setRandomDiceGenerator(
				new DiceGenerator124433());
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B3));
		assertTrue(game.move(Location.B1, Location.B3));
		assertTrue(game.move(Location.B3, Location.B5));
		assertTrue(game.move(Location.B3, Location.B5));

	}

	@Test
	public void blackShouldHave4MovesWhenThrown33() {

		game.getRollDeterminer().setRandomDiceGenerator(
				new DiceGenerator124433());
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();

		System.out.println("0 is " + game.diceThrown()[0]);
		System.out.println("1 is " + game.diceThrown()[1]);

		assertTrue(game.move(Location.R1, Location.R3));
		assertTrue(game.move(Location.R1, Location.R3));
		assertTrue(game.move(Location.R3, Location.R5));
		assertTrue(game.move(Location.R3, Location.R5));

	}

	@Test
	public void shouldHaveNoPlayerInTurnWhenThrown44InFirstTurn() {

		game.getRollDeterminer().setRandomDiceGenerator(new DiceGenerator44());
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.NONE);
	}

}
