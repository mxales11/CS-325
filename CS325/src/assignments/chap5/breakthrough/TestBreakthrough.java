package assignments.chap5.breakthrough;

import org.junit.*;

import assignments.chap5.breakthrough.Breakthrough.Piece;
import assignments.chap5.breakthrough.Breakthrough.Player;
import static org.junit.Assert.*;

/**
 * Initial test case class for Breakthrough
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestBreakthrough {
	Breakthrough game;

	/** Fixture */
	@Before
	public void setUp() {
		game = new BreakthroughImpl();
	}

	@Test
	public void shouldHaveBlackPieceOn00() {
		assertEquals("Black has piece on (0,0)",
				BreakthroughImpl.Piece.BLACK, game.getPieceAt(0, 0));
	}

	@Test
	public void shouldHaveWhitePieceOn70() {
		assertEquals("White has piece on (7,0)",
				BreakthroughImpl.Piece.WHITE, game.getPieceAt(7, 0));
	}

	@Test
	public void shouldHaveAllPiecesCorrectlyPlaced() {
		assertEquals("Should have NONE on (3,0)",
				BreakthroughImpl.Piece.NONE, game.getPieceAt(3, 0));
		assertEquals("Should have NONE on (5,1)",
				BreakthroughImpl.Piece.NONE, game.getPieceAt(5, 1));
		assertEquals("Should have WHITE on (6,5)",
				BreakthroughImpl.Piece.WHITE, game.getPieceAt(6, 5));
		assertEquals("Should have BLACK on (1,4)",
				BreakthroughImpl.Piece.BLACK, game.getPieceAt(1, 4));
	}

	@Test
	public void shouldStartWithWhitePlayer() {
		assertTrue(game.getCurrentPlayer() == Player.WHITE);
	}

	@Test
	public void shouldSwitchFromWhiteToBlackPlayer() {
		assertTrue(game.getCurrentPlayer() == Player.WHITE);
		game.move(6, 7, 5, 7);
		assertTrue(game.getCurrentPlayer() == Player.BLACK);
	}

	@Test
	public void shouldSwitchPlayerAfterEachMove() {

		game.move(6, 7, 5, 7);
		game.move(1, 0, 2, 0);
		assertTrue(game.getCurrentPlayer() == Player.WHITE);
		game.move(5, 7, 4, 7);
		assertTrue(game.getCurrentPlayer() == Player.BLACK);
	}

	@Test(expected = IllegalMoveException.class)
	public void whitePlayerShouldNotMoveBlackPiece() {
		game.move(0, 0, 1, 0);
	}

	@Test(expected = IllegalMoveException.class)
	public void whitePlayerShouldNotMoveEmptyPiece() {
		game.move(5, 0, 4, 0);
	}

	@Test
	public void eachPlayerCanMoveOnlyPieceOfHisColor() {
		game.move(6, 4, 5, 4);
		game.move(1, 2, 2, 2);
	}

	public void shouldBeALegalMoveToNullField() {
		game.move(6, 4, 5, 4);
	}

	@Test(expected = IllegalMoveException.class)
	public void whitePlayerShouldNotMoveToFieldWithWhitePiece() {
		game.move(7, 7, 6, 7);
	}

	@Test(expected = IllegalMoveException.class)
	public void blackPlayerShouldNotMoveToFieldWithBlackPiece() {
		game.move(6, 1, 5, 1);
		game.move(0, 6, 1, 6);
	}

	@Test
	public void whiteShouldMoveFrom60to50() {
		assertTrue(game.getPieceAt(6, 0) == Piece.WHITE);
		assertTrue(game.getPieceAt(5, 0) == Piece.NONE);
		game.move(6, 0, 5, 0);
		assertTrue(game.getPieceAt(5, 0) == Piece.WHITE);
		assertTrue(game.getPieceAt(6, 0) == Piece.NONE);
	}

	@Test
	public void blackShouldMoveFrom14to24() {

		assertTrue(game.getPieceAt(1, 4) == Piece.BLACK);
		assertTrue(game.getPieceAt(2, 4) == Piece.NONE);
		game.move(6, 0, 5, 0);
		game.move(1, 4, 2, 4);
		assertTrue(game.getPieceAt(2, 4) == Piece.BLACK);
		assertTrue(game.getPieceAt(1, 4) == Piece.NONE);
	}

	@Test(expected = IllegalMoveException.class)
	public void whitePlayerShouldNotGoBackwards() {
		game.move(5, 0, 6, 0);
	}

	@Test(expected = IllegalMoveException.class)
	public void blackPlayerShouldNotGoBackwards() {
		game.move(6, 0, 5, 0);
		game.move(1, 0, 0, 0);
	}

	@Test(expected = IllegalMoveException.class)
	public void whitePlayerCannotMakeMoreThanOneStepMove() {
		game.move(6, 0, 4, 0);

	}

	@Test(expected = IllegalMoveException.class)
	public void blackPlayerShouldNotMakeMoreThanOneStepMove() {
		game.move(6, 0, 5, 0);
		game.move(1, 1, 3, 1);
		
	}
	
	

	@Test
	public void whiteShouldBeTheWinner() {
		
		assertEquals(game.getWinner(), null);
		game.move(6, 0, 5, 0);
		assertEquals(game.getWinner(), null);
		game.move(1, 1, 2, 2);
		assertEquals(game.getWinner(), null);
		game.move(5, 0, 4, 0);
		assertEquals(game.getWinner(), null);
		game.move(0, 0, 1, 1);
		assertEquals(game.getWinner(), null);
		game.move(4, 0, 3, 0);
		assertEquals(game.getWinner(), null);
		game.move(1, 0, 2, 1);
		assertEquals(game.getWinner(), null);
		game.move(3, 0, 2, 0);
		assertEquals(game.getWinner(), null);
		game.move(1, 7, 2, 7);
		assertEquals(game.getWinner(), null);
		game.move(2, 0, 1, 0);
		assertEquals(game.getWinner(), null);
		game.move(2, 7, 3, 7);
		assertEquals(game.getWinner(), null);
		game.move(1, 0, 0, 0);
		assertEquals(game.getWinner(), Player.WHITE);	
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void shouldNotCrossBoard() {
		
		assertEquals(game.getWinner(), null);
		game.move(6, 0, 5, 0);
		game.move(0, 0, -1, 0);
		
	}
	@Test
	public void whitePieceShouldCaptuereBlackPieceDiagonally() {
		
		
		game.move(6, 0, 5, 0);
		game.move(1, 0, 2, 1);
		game.move(5, 0, 4, 0);
		game.move(2, 1, 3, 1);;
		assertTrue(game.getPieceAt(4, 0) == Piece.WHITE);
		assertTrue(game.getPieceAt(3, 1) == Piece.BLACK);
		game.move(4, 0, 3, 1);
		assertTrue(game.getPieceAt(3, 1) == Piece.WHITE);
		assertEquals(game.getPieceAt(4, 0), Piece.NONE);
		
	}
	
	@Test(expected = IllegalMoveException.class)
	public void blackPieceShouldNotCaptuereWhitePieceVertically() {
		
		
		game.move(6, 0, 5, 0);
		game.move(1, 0, 2, 1);
		game.move(5, 0, 4, 0);
		game.move(2, 1, 3, 1);;
		game.move(4, 0, 3, 0);
		game.move(3, 1, 3, 0);
		
	}
}
