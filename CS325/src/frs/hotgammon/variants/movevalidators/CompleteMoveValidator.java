package frs.hotgammon.variants.movevalidators;

import java.util.ArrayList;

import frs.hotgammon.Board;
import frs.hotgammon.Color;
import frs.hotgammon.Square;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;

public class CompleteMoveValidator implements MoveValidator {

	private Game game;

	public CompleteMoveValidator() {

	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	

	public boolean coreValidMove(Location from) {
		return thereIsNoWinner() && playerMovesHisChecker(from);
	}

	private boolean playerMovesHisChecker(Location from) {
		return game.getPlayerInTurn() == game.getColor(from);
	}
	
	private boolean thereIsNoWinner(){
		return game.winner()==Color.NONE;
	}

	
	private boolean checkerIsMovedToEmptyLocation(Location to) {
		return (game.getColor(to) == Color.NONE);
	}
	
	
	
	
	private boolean checkerIsPlacedOnTheSameColor(Location to) {
		return (game.getColor(to) == game.getPlayerInTurn());
	}

	private boolean redCheckerMovedInLegalDirection(Location from, Location to) {
		return game.getPlayerInTurn() == Color.RED
				&& Location.distance(from, to) < 0 ? true : false;
	}

	private boolean blackCheckerMovedInLegalDirection(Location from, Location to) {
		return game.getPlayerInTurn() == Color.BLACK
				&& Location.distance(from, to) > 0 ? true : false;
	}

	private boolean playerMovesCheckerInLegalDirection(Location from,
			Location to) {

		return (redCheckerMovedInLegalDirection(from, to) || blackCheckerMovedInLegalDirection(
				from, to)) ? true : false;

	}

	public boolean distanceTravelledEqualsTheValueOfDieRolled(Location from,
			Location to) {

		int distance = Location.distance(from, to);

		for (int i = 0; i < game.diceValuesLeft().length; i++) {
			if (game.diceValuesLeft()[i] == Math.abs(distance)) {
				return true;
			}
		}
		return false;

	}
	
	public boolean movesDoubled() {
		return ((GameImpl)game).getMovesDoubled();
	}

	@Override
	public boolean isValid(Location from, Location to) {
		return coreValidMove(from)
				&& (checkerIsMovedToEmptyLocation(to)
						|| checkerIsPlacedOnTheSameColor(to) || checkerIsMovedToLocationWithOneOpponent(to))
				&& playerMovesCheckerInLegalDirection(from, to)
				&& ((distanceTravelledEqualsTheValueOfDieRolled(from, to)) || (attemptsToBearOff(to)
						&& currentPlayerHasAllCheckersOnInnerTable() && noCheckerOnInnerTableFartherAwayThanDieValue(from)))
				&& (!attemptsToMoveToTheBar(to))
				&& (!currentPlayerIsInTheBar() || (movesToOpponentsInnerTable(to))
						&& !(checkerIsMovedToLocationWithOneOpponent(to)))
				&& ((!attemptsToBearOff(to)) || (currentPlayerHasAllCheckersOnInnerTable()))

		;
	}

	public boolean checkerIsMovedToLocationWithOneOpponent(Location to) {
		return (game.getCount(to) == 1 && ((BoardImpl) game.getBoard()).get(to
				.ordinal()).color != game.getPlayerInTurn());
	}

	private boolean attemptsToMoveToTheBar(Location to) {

		return (to == Location.B_BAR) || (to == Location.R_BAR);
	}

	private boolean blackCheckerIsInTheBar() {
		return game.getBoard().get(Location.B_BAR.ordinal()).occupants != 0
				&& game.getPlayerInTurn() == Color.BLACK;
	}

	private boolean redCheckerIsInTheBar() {
		return game.getBoard().get(Location.R_BAR.ordinal()).occupants != 0
				&& game.getPlayerInTurn() == Color.RED;
	}

	private boolean currentPlayerIsInTheBar() {
		return blackCheckerIsInTheBar() || redCheckerIsInTheBar();
	}

	private boolean blackMovesToOpponentsInnerTable(Location to) {

		return game.getPlayerInTurn() == Color.BLACK
				&& BoardImpl.redInnerTable.contains(to);
	}

	private boolean redMovesToOpponetsInnerTable(Location to) {

		return game.getPlayerInTurn() == Color.RED
				&& BoardImpl.blackInnerTable.contains(to);
	}

	private boolean movesToOpponentsInnerTable(Location to) {

		return blackMovesToOpponentsInnerTable(to)
				|| redMovesToOpponetsInnerTable(to);
	}

	private ArrayList<Integer> indexesOfInnerTable(
			ArrayList<Location> innerTableList) {

		ArrayList<Integer> indexesOfInnerTableList = new ArrayList<Integer>();

		for (int i = 0; i < innerTableList.size(); i++) {
			indexesOfInnerTableList.add(innerTableList.get(i).ordinal());
		}
		return indexesOfInnerTableList;

	}


	private boolean noRedCheckerOnInnerTableFartherAwayFromDieValue(
			) {

		Board board = game.getBoard();
		ArrayList<Location> innerTable = BoardImpl.redInnerTable;

		for (int i = 0; i < innerTable.size(); i++) {

			int distanceFromOther = Location.distance(innerTable.get(i),
					Location.R_BEAR_OFF);
			int indexOfOtherChecker = innerTable.get(i).ordinal();

			if (board.get(indexOfOtherChecker).color == Color.RED
					&& Math.abs(distanceFromOther) > game.diceValuesLeft()[0]) {
				return false;
			}

		}
		return true;
	}

	private boolean noBlackCheckerOnInnerTableFartherAwayFromDieValue(
			) {

		Board board = game.getBoard();
		ArrayList<Location> innerTable = BoardImpl.blackInnerTable;

		for (int i = 0; i < innerTable.size(); i++) {

			int distanceFromBearOff = Location.distance(innerTable.get(i),
					Location.B_BEAR_OFF);
			int indexOfOtherChecker = innerTable.get(i).ordinal();

			if (board.get(indexOfOtherChecker).color == Color.BLACK
					&& Math.abs(distanceFromBearOff) > game.diceValuesLeft()[0]) {
				return false;
			}

		}
		return true;

	}

	public boolean noCheckerOnInnerTableFartherAwayThanDieValue(Location from) {
		return game.getPlayerInTurn() == Color.RED ? noRedCheckerOnInnerTableFartherAwayFromDieValue()
				: noBlackCheckerOnInnerTableFartherAwayFromDieValue();

	}

	private boolean allCheckersAreInHisInnerTable(Color player,
			ArrayList<Location> innerTable, Location bearOff) {

		Board board = game.getBoard();

		for (int i = 0; i < board.size(); i++) {

			Square container = board.get(i);
			if (container.color == player
					&& (indexesOfInnerTable(innerTable).indexOf(i) == -1 && i != bearOff
							.ordinal())) {

				return false;
			}
		}
		return true;
	}

	private boolean currentPlayerHasAllCheckersOnInnerTable() {

		return game.getPlayerInTurn() == Color.RED ? allCheckersAreInHisInnerTable(
				Color.RED, BoardImpl.redInnerTable, Location.R_BEAR_OFF)
				: allCheckersAreInHisInnerTable(Color.BLACK,
						BoardImpl.blackInnerTable, Location.B_BEAR_OFF);

	}

	private boolean attemptsToBearOff(Location to) {
		return to == Location.R_BEAR_OFF || to == Location.B_BEAR_OFF;
	}
	
	

}
