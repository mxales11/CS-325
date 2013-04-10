package frs.hotgammon.mytests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator123456;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator124433;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator2134;
import frs.hotgammon.mytests.dicegenerators.DiceGenerator44;
import frs.hotgammon.variants.rules.SemiMon;

public class SemiMonTests {

	GameImpl game;

	@Before
	public void setUp() {
		game = new GameImpl(new SemiMon());
		game.newGame();
		game.getRollDeterminer()
				.setRandomDiceGenerator(new DiceGenerator2134());
	}

	
	//RandomRollStrategy tests
	@Test
	public void diceThrownAndLeftShouldBe21() {

		game.nextTurn();
		assertTrue(game.diceThrown()[0] == 2 && game.diceThrown()[1] == 1);
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
		assertTrue(game.move(Location.B1, Location.B2));
		assertFalse(game.move(Location.B3, Location.B5));
		assertFalse(game.move(Location.B3, Location.B5));

	}

	@Test
	public void redShouldHave4MovesWhenThrown44() {

		game.getRollDeterminer().setRandomDiceGenerator(
				new DiceGenerator124433());
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B5));
		assertFalse(game.move(Location.B1, Location.B4));
		assertTrue(game.move(Location.B1, Location.B5));
		assertTrue(game.move(Location.B12, Location.R9));
		assertTrue(game.move(Location.B12, Location.R9));

	}

	@Test
	public void blackShouldHave4MovesWhenThrown33() {

		game.getRollDeterminer().setRandomDiceGenerator(
				new DiceGenerator124433());
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();

		assertFalse(game.move(Location.R1, Location.R3));
		
		assertTrue(game.move(Location.R1, Location.R4));
		assertTrue(game.move(Location.R1, Location.R4));
		
		assertFalse(game.move(Location.R4, Location.R10));
	
		assertTrue(game.move(Location.R4, Location.R7));
		assertTrue(game.move(Location.R4, Location.R7));

	}

	@Test
	public void shouldHaveNoPlayerInTurnWhenThrown44InFirstTurn() {

		game.getRollDeterminer().setRandomDiceGenerator(new DiceGenerator44());
		game.nextTurn();
		assertTrue(game.getPlayerInTurn() == Color.NONE);
	}

	
	//WinerDeterminer tests
	
	@Test
	public void shouldHaveNoWinnerAfter6Turns() {
		
		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
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
		
		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

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
		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.winner() == Color.BLACK);
	}
	
	
	
	//MoveDeterminer tests
	
	
	@Test
	public void shouldNotBeLegalToMoveBlackCheckerInWrongDirection() {

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.R11));

	}

	@Test
	public void shouldNotBeLegalToMoveRedCheckerInWrongDirection() {

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R8, Location.R10));
	}

	@Test
	public void shouldBeLegalToMove1InFirstTurn() {


		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R5));
		assertTrue(game.move(Location.R1, Location.R2));
		assertFalse(game.move(Location.R2, Location.R3));
	}

	@Test
	public void shouldBeLegalToMove2InFirstTurn() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R3));
	}

	@Test
	public void afterMoving2OnlyLegalMoveIs1() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R3));
		assertFalse(game.move(Location.R1, Location.R3));
		assertFalse(game.move(Location.R1, Location.R1));
		assertFalse(game.move(Location.R1, Location.R4));
		assertFalse(game.move(Location.R1, Location.R5));
		assertFalse(game.move(Location.R1, Location.R6));
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldResetLegalMovesTo12AfterNewGame() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		game.nextTurn();
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R1, Location.R3));
	}

	@Test
	public void dieValuesLeftShouldBeNullAfterReseting() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		game.nextTurn();
		game.newGame();
		assertTrue(game.diceValuesLeft() == null);

	}

	@Test
	public void allMovesShouldBeSameAsDieValues() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.nextTurn();

		assertTrue(game.move(Location.B6, Location.B5));
		assertTrue(game.move(Location.B6, Location.B4));
		assertFalse(game.move(Location.B6, Location.B4));
		game.nextTurn();
		assertTrue(game.move(Location.R6, Location.R3));
		assertFalse(game.move(Location.R6, Location.R4));
		assertTrue(game.move(Location.B12, Location.R9));
		game.nextTurn();
		assertFalse(game.move(Location.B8, Location.R11));
		assertFalse(game.move(Location.B12, Location.B7));
		assertTrue(game.move(Location.B8, Location.B3));
		assertTrue(game.move(Location.R12, Location.B7));
	}

	@Test
	public void dieValuesShouldBe21After1Turn() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.nextTurn();
		assertTrue(game.diceValuesLeft()[0] == 2);
		assertTrue(game.diceValuesLeft()[1] == 1);
	}

	@Test
	public void dieValuesShouldBe65After3Turns() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.diceValuesLeft()[0] == 6);
		assertTrue(game.diceValuesLeft()[1] == 5);

	}


	@Test
	public void shouldMoveBlackCheckerToTheBarWhenRedMoves() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.B4),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B1) });

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B4));
		assertTrue(game.getCount(Location.B4) == 1);
		assertTrue(game.getColor(Location.B4) == Color.RED);
		assertTrue(game.getCount(Location.B_BAR) == 1);
	}

	@Test
	public void shouldMoveRedCheckerToTheBarWhenBlackMoves() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.configure(new Placement[] { new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();

		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 1);
		assertTrue(game.getColor(Location.R2) == Color.BLACK);
		assertTrue(game.getCount(Location.B_BAR) == 0);
		assertTrue(game.getCount(Location.R_BAR) == 1);

	}

	@Test
	public void shouldNotMoveHerOwnCheckerToTheBar() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),

		});
		game.nextTurn();

		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.getCount(Location.R1) == 1);
		assertTrue(game.getCount(Location.R2) == 2);
		assertTrue(game.getColor(Location.R2) == Color.BLACK);
		assertTrue(game.getCount(Location.B_BAR) == 0);
		assertTrue(game.getCount(Location.R_BAR) == 0);

	}

	@Test
	public void movingToBarIncreasesNumberOfCheckersInThisBarBy1() {

		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		
		game.configure(new Placement[] { new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.RED, Location.R_BAR) });
		game.nextTurn();

		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.getCount(Location.R_BAR) == 2);

	}

	@Test
	public void shouldNotMoveToABlockedContainer() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		
		game.configure(new Placement[] { new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldBeAbleToMoveToAContainerWith2HisOwnCheckers() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldBeIllegalToManuallyGoToTheBar() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.configure(new Placement[] { new Placement(Color.BLACK, Location.B1), });
		game.nextTurn();

		assertFalse(game.move(Location.B1, Location.R_BAR));
	}

	@Test
	public void checkerInTheBarMustMoveToOpponentsInnerTable() {

		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {
				new Placement(Color.RED, Location.R_BAR),
				new Placement(Color.RED, Location.R8) });

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R8, Location.R5));
		assertFalse(game.move(Location.R_BAR, Location.B2));
		assertFalse(game.move(Location.R_BAR, Location.B9));
		assertTrue(game.move(Location.R_BAR, Location.B3));
	}

	@Test
	public void checkerInTheBarCannotMoveToInnerTableOccupiedByOpponent() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {
				new Placement(Color.RED, Location.R_BAR),
				new Placement(Color.BLACK, Location.B3),
				new Placement(Color.RED, Location.R8) });

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R_BAR, Location.B3));
	}

	@Test
	public void checkerInTheBarCanMoveToInnerTableOccupiedByHisOwnCheckers() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {
				new Placement(Color.RED, Location.R_BAR),
				new Placement(Color.RED, Location.B3),
				new Placement(Color.RED, Location.B3),
				new Placement(Color.RED, Location.R8) });

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R_BAR, Location.B3));
	}

	@Test
	public void shouldNotBearOfWhenNotAllCheckersAreInInnerTable() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

		new Placement(Color.RED, Location.R3),
				new Placement(Color.RED, Location.R8),
				new Placement(Color.BLACK, Location.B1), });

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R3, Location.R_BEAR_OFF));
	}

	@Test
	public void shouldBearOfTwiceInARowWhenNotAllCheckersAreInInnerTable() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

		new Placement(Color.RED, Location.R3),
				new Placement(Color.RED, Location.R4), });

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R3, Location.R_BEAR_OFF));
		assertTrue(game.move(Location.R4, Location.R_BEAR_OFF));

	}

	@Test
	public void blackShouldBearOfWhenAllCheckersAreInInnerTable() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

		new Placement(Color.BLACK, Location.B6),
				new Placement(Color.BLACK, Location.B6),
				new Placement(Color.BLACK, Location.B1), });

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B6, Location.B_BEAR_OFF));
	}

	@Test
	public void blackShouldBearOffWithLessThanDieIfNoOtherMoveAvailable() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());

		game.configure(new Placement[] {

		new Placement(Color.BLACK, Location.B2),
				new Placement(Color.BLACK, Location.B1),
				new Placement(Color.BLACK, Location.B3),
				new Placement(Color.BLACK, Location.B4),

		});

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
		assertTrue(game.diceValuesLeft().length==1);
	
	}

	@Test
	public void shouldNotBearOffWithLessThanDieIfOtherMoveAvailable() {


		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

		new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R6), });

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R2, Location.R_BEAR_OFF));
	}
	
	
	@Test
	public void afterBearingOffThereShouldBeOneDieValueLeft(){
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

				new Placement(Color.BLACK, Location.B2),
						new Placement(Color.BLACK, Location.B1),
						new Placement(Color.BLACK, Location.B3),
						new Placement(Color.BLACK, Location.B4),

				});
				game.nextTurn();
				game.nextTurn();
				game.nextTurn();
				assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
				assertTrue(game.diceValuesLeft().length==1);
				assertTrue(game.move(Location.B1, Location.B_BEAR_OFF));
				assertTrue(game.diceValuesLeft().length==0);
			
	}
	
	

	@Test
	public void redShouldBearOffWithLessThanDieIfNoOtherMoveAvailable() {


		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.configure(new Placement[] {

		new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R1), });

		game.nextTurn();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R_BEAR_OFF));
		assertTrue(game.diceValuesLeft().length==1);
	}

	@Test
	public void thereShouldOneDieLeftEquals2AfterOneMove(){
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.diceValuesLeft()[0]==2);
		assertTrue(game.diceValuesLeft().length==1);
		
	}
	
	@Test
	public void thereShouldOneDieLeftEquals1AfterOneMove(){
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R3));
		assertTrue(game.diceValuesLeft()[0]==1);
		assertTrue(game.diceValuesLeft().length==1);
		
	}
	
	@Test
	public void shouldNotMakeMoreThanTwoMoves() {
		

		game.getRollDeterminer()
		.setRandomDiceGenerator(new DiceGenerator123456());
			game.nextTurn();
			assertTrue(game.move(Location.R1, Location.R3));
			assertTrue(game.move(Location.R1, Location.R2));
			assertFalse(game.move(Location.R2, Location.R3));	
	}
		

}
