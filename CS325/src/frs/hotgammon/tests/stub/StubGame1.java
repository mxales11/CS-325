package frs.hotgammon.tests.stub;

import java.util.ArrayList;

import frs.hotgammon.Board;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.*;
import frs.hotgammon.states.DiceRollState;
import frs.hotgammon.states.GameState;
import frs.hotgammon.states.GameStateImpl;
import frs.hotgammon.states.MoveCheckerState;

/**
 * A testing stub for visual testing of the hotgammon graphical user interface.
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class StubGame1 implements Game {

	private GameState currentState;
	public ArrayList<GameObserver> gameObserversList = new ArrayList<GameObserver>();

	public StubGame1() {
		
	}
	
	public void setState(GameState state) {
		
		currentState = state;
		
	}
	
	

	// Here I only have one red and one
	// black checker on the board!
	public Location loneRiderHere1;
	public Location loneRiderHere2;

	// toggle between player in turn
	public boolean tictac = true;
	// moves left to make for a player
	public int movesLeft;

	public void newGame() {
		
		System.out.println("New game was started");
		currentState = new GameStateImpl(this);
		
		System.out.println("Current state is: " + currentState);
		
		movesLeft = 2;
		turn = 0;

		loneRiderHere1 = Location.B12;
		loneRiderHere2 = Location.R1;
	}

	// count turns, used to simulate dice rolling
	public int turn;
	
	public int getTurn() {
		
		return turn;
	}

	
	
	
	public void  maxNumberOfMoves(){
		
		movesLeft = 2;
	}
	
	
	
	public void nextTurn() {
		
		currentState.nextTurn();
		/**
		turn++;
		movesLeft = 2;
		tictac = !tictac;
		System.out.println("nextTurn: " + turn);
		
		for (int i = 0; i < gameObserversList.size(); i++) {
			gameObserversList.get(i).diceRolled(diceThrown());
		}
		
		**/
	}

	/**
	 * for testing purposes location B3 and R3 are invalid to move to.
	 */
	public boolean move(Location from, Location to) {
		
		
		return currentState.move(from, to);
		
		/**
		if (to != Location.B3 && to != Location.R3 && to != null) {
			System.out.println("GAME: moving from " + from + " to " + to);
			if (from == loneRiderHere1) {
				loneRiderHere1 = to;
			} else if (from == loneRiderHere2) {
				loneRiderHere2 = to;
			}
		} 
		else if (to == null) {
			
			//it never equals null cause Convert returns the closest location
			System.out.println("You cannot move to empty location");
			return false;
		}
		
		
		else if(Location.distance(from, to)==0) {
			
			System.out.println("You cannot move to itself");
			return false;
		}
		else {
			System.out
					.println("GAME: Moving to B3/R3 is illegal (testing purposes)");
			return false;
		}
		movesLeft--;
		
		

		for (int i = 0; i < gameObserversList.size(); i++) {
			gameObserversList.get(i).checkerMove(from, to);
		}
		return true;
		
		**/
	}
	

	// accessor methods
	public Color getPlayerInTurn() {
		if (movesLeft == 0)
			return Color.NONE;
		if (tictac)
			return Color.RED;
		return Color.BLACK;
	}

	public int getNumberOfMovesLeft() {
		return movesLeft;
	}

	public int[] diceThrown() {
		switch (turn % 5) {
		case 1:
			return new int[] { 2, 3 };
		case 2:
			return new int[] { 4, 4 };
		case 3:
			return new int[] { 3, 6 };
		case 4:
			return new int[] { 6, 1 };
		case 0:
			return new int[] { 5, 3 };
		default:
			return new int[] { 2, 2 };
		}
	}

	public int[] diceValuesLeft() {
		int[] v = { 1 };
		return v;
	}

	public Color winner() {
		return Color.NONE;
	}

	// Board
	public Color getColor(Location location) {
		if (location == loneRiderHere1)
			return Color.BLACK;
		if (location == loneRiderHere2)
			return Color.RED;
		return Color.NONE;
	}

	public int getCount(Location location) {
		int sum = 0;
		if (location == loneRiderHere1)
			sum++;
		if (location == loneRiderHere2)
			sum++;
		return sum;
	}

	

	@Override
	public GameState getGameState() {
		return currentState;
	}
	
	
	@Override
	public void addObserver(GameObserver observer) {

		gameObserversList.add(observer);

	}
	
	
	
	//those methods don't do anything yet
	@Override
	public int getNumberOfMovesMade() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RollDeterminer getRollDeterminer() {
		// TODO Auto-generated method stub
		return null;
	}

}