package frs.hotgammon.mytests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator124433;
import frs.hotgammon.variants.rules.AlphaMon;
import frs.hotgammon.variants.rules.BetaMon;
import frs.hotgammon.variants.rules.DeltaMon;
import frs.hotgammon.variants.rules.SemiMon;

public class RunTimeReconfigurationsTests {
	
GameImpl game;

	@Test
	public void shouldHaveRedPlayerInTurnAfterAlphaMonChangedToBetaMon() {
		
		game = new GameImpl(new AlphaMon());
		game.newGame();
		game.nextTurn();
		game.setUpRules(new BetaMon());
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.RED);	
	}
	
	
	
	@Test
	public void diceThrownShouldBe34AfterAlphaMonChangedToBetaMon() {
		
		game = new GameImpl(new AlphaMon());
		game.newGame();
		game.nextTurn();
		game.setUpRules(new BetaMon());
		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 3 && game.diceThrown()[1]==4 );	
	}
	
	
	
	@Test
	public void shouldUseCompleteMoveValidatorWhenAlphaMonChangedToBetaMon() {
		
		game = new GameImpl(new AlphaMon());
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R5));
		game.setUpRules(new BetaMon());
		assertFalse(game.move(Location.R1, Location.R5));
		assertTrue(game.move(Location.R1, Location.R2));	
	}
	
	@Test
	public void shouldHaveOneDiceValueLeftAfterOneMove() {
		
		game = new GameImpl(new AlphaMon());
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R5));
		game.setUpRules(new BetaMon());
		assertTrue(game.diceValuesLeft() .length==1);	
	}
	
	@Test 
	public void redShouldBeInTurnWhenAlphaMonChangedToDeltaMon() {
		
		game = new GameImpl(new AlphaMon());
		game.newGame();
		game.nextTurn();
		game.setUpRules(new DeltaMon());
		game.nextTurn();
		assertTrue(game.getPlayerInTurn()==Color.RED);	
	}
	
	
	@Test 
	public void dieValuesShouldBe34AfterSemiMonChangedToAlphaMon() {
		
		game = new GameImpl(new SemiMon());
		game.newGame();
		game.nextTurn();
		game.setUpRules(new AlphaMon());
		game.nextTurn();
		assertTrue(game.diceThrown()[0]==3 && game.diceThrown()[1]==4);	
	}

	
	@Test 
	public void thereShouldBeAWinnerAfter6MovesWhenSemiMonChangedToBetaMon() {
		
		game = new GameImpl(new SemiMon());
		game.getRollDeterminer().setRandomDiceGenerator(new DiceGenerator124433());
		game.newGame();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.setUpRules(new BetaMon());
		game.nextTurn();
		assertTrue(game.winner() == Color.RED);
	}

	
	@Test 
	public void thereShouldBeNoWinnerAfer6MovesWhenBetaMonChangedToSemiMon() {
		
		game = new GameImpl(new BetaMon());
		game.newGame();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.setUpRules(new SemiMon());
		game.nextTurn();
		assertTrue(game.winner() == Color.NONE);
	}
	
	
}
