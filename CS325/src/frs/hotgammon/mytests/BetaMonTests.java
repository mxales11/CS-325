package frs.hotgammon.mytests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.rules.BetaMon;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;
import frs.hotgammon.common.GameImpl.Placement;


public class BetaMonTests {

	GameImpl game;

	@Before
	public void setUp() {

		game = new GameImpl(new BetaMon());
		game.newGame();

	}

	@Test
	public void shouldNotBeLegalToMoveBlackCheckerInWrongDirection() {
		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.R11));

	}

	@Test
	public void shouldNotBeLegalToMoveRedCheckerInWrongDirection() {
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R8, Location.R10));
	}

	@Test
	public void shouldBeLegalToMove1InFirstTurn() {

		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R5));
		assertTrue(game.move(Location.R1, Location.R2));
		assertFalse(game.move(Location.R2, Location.R3));
	}

	@Test
	public void shouldBeLegalToMove2InFirstTurn() {

		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R3));
	}

	@Test
	public void afterMoving2OnlyLegalMoveIs1() {

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
		game.nextTurn();
		game.nextTurn();
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.move(Location.R1, Location.R3));
	}

	@Test
	public void dieValuesLeftShouldBeNullAfterReseting() {
		game.nextTurn();
		game.nextTurn();
		game.newGame();
		assertTrue(game.diceValuesLeft() == null);

	}

	@Test
	public void allMovesShouldBeSameAsDieValues() {

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

		game.nextTurn();
		assertTrue(game.diceValuesLeft()[0] == 2);
		assertTrue(game.diceValuesLeft()[1] == 1);
	}

	@Test
	public void dieValuesShouldBe65After3Turns() {

		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.diceValuesLeft()[0] == 6);
		assertTrue(game.diceValuesLeft()[1] == 5);

	}

	@Test
	public void shouldMoveBlackCheckerToTheBarWhenRedMoves() {

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
		game.configure(new Placement[] { new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R2),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();
		assertFalse(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldBeAbleToMoveToAContainerWith2HisOwnCheckers() {
		game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R1), });
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void shouldBeIllegalToManuallyGoToTheBar() {

		game.configure(new Placement[] { new Placement(Color.BLACK, Location.B1), });
		game.nextTurn();

		assertFalse(game.move(Location.B1, Location.R_BAR));
	}

	@Test
	public void checkerInTheBarMustMoveToOpponentsInnerTable() {

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

		game.configure(new Placement[] {

		new Placement(Color.RED, Location.R2),
				new Placement(Color.RED, Location.R6), });

		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R2, Location.R_BEAR_OFF));
	}
	
	
	@Test
	public void afterBearingOffThereShouldBeOneDieValueLeft(){
		
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
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertTrue(game.diceValuesLeft()[0]==2);
		assertTrue(game.diceValuesLeft().length==1);
		
	}
	
	@Test
	public void thereShouldOneDieLeftEquals1AfterOneMove(){
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R3));
		assertTrue(game.diceValuesLeft()[0]==1);
		assertTrue(game.diceValuesLeft().length==1);
		
	}
	
	@Test
	public void shouldNotMakeMoreThanTwoMoves() {
			game.nextTurn();
			assertTrue(game.move(Location.R1, Location.R3));
			assertTrue(game.move(Location.R1, Location.R2));
			assertFalse(game.move(Location.R2, Location.R3));	
	}
		
	
	
}