package frs.hotgammon.variants.movevalidators;

import java.util.ArrayList;

import frs.hotgammon.Board;
import frs.hotgammon.Square;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.common.BoardImpl;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.DiceRollState;

public class CompleteMoveValidator implements MoveValidator {

	private GameImpl game;
	private static final int DOUBLE = 2;
	private boolean isPotentialMove = false;

	public CompleteMoveValidator() {

	}

	public void setGame(Game game) {
		this.game = (GameImpl) game;
	}

	public boolean coreValidMove(Location from) {
		return thereIsNoWinner() && playerMovesHisChecker(from);
	}

	private boolean playerMovesHisChecker(Location from) {
		return game.getPlayerInTurn() == game.getColor(from);
	}

	private boolean thereIsNoWinner() {
		return game.winner() == Color.NONE;
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
		return ((GameImpl) game).getMovesDoubled();
	}

	@Override
	public boolean isValid(Location from, Location to) {
		boolean valid = coreValidMove(from)
				&& (checkerIsMovedToEmptyLocation(to)
						|| checkerIsPlacedOnTheSameColor(to) || checkerIsMovedToLocationWithOneOpponent(to))
				&& (playerMovesCheckerInLegalDirection(from, to))
				&& ((distanceTravelledEqualsTheValueOfDieRolled(from, to)) || (attemptsToBearOff(to)
						&& currentPlayerHasAllCheckersOnInnerTable() && noCheckerOnInnerTableFartherAwayThanDieValue(from)))
				&& (!attemptsToMoveToTheBar(to))
				&& (!currentPlayerIsInTheBar() || (movesToOpponentsInnerTableFromBar(
						from, to)))
				&& ((!attemptsToBearOff(to)) || (currentPlayerHasAllCheckersOnInnerTable()));

		if (!valid && !isPotentialMove) {
			if (noMovePossible()) {
				game.setState(new DiceRollState(game));
				System.out.println("Your turn was skipped");
				for (int i = 0; i < game.getGameObserversList().size(); i++) {
					game.getGameObserversList().get(i).changeStatusField("No moves legal! Your turn was skipped. Player in turn is " + game.getPlayerInTurn());
				}
			}
		}

		return valid;
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

	private boolean blackMovesToOpponentsInnerTable(Location from, Location to) {

		return game.getPlayerInTurn() == Color.BLACK
				&& BoardImpl.redInnerTable.contains(to)
				&& from == Location.B_BAR;
	}

	private boolean redMovesToOpponetsInnerTable(Location from, Location to) {

		return game.getPlayerInTurn() == Color.RED
				&& BoardImpl.blackInnerTable.contains(to)
				&& from == Location.R_BAR;
	}

	private boolean movesToOpponentsInnerTableFromBar(Location from, Location to) {

		return blackMovesToOpponentsInnerTable(from, to)
				|| redMovesToOpponetsInnerTable(from, to);
	}

	private ArrayList<Integer> indexesOfInnerTable(
			ArrayList<Location> innerTableList) {

		ArrayList<Integer> indexesOfInnerTableList = new ArrayList<Integer>();

		for (int i = 0; i < innerTableList.size(); i++) {
			indexesOfInnerTableList.add(innerTableList.get(i).ordinal());
		}
		return indexesOfInnerTableList;

	}

	private boolean noRedCheckerOnInnerTableFartherAwayFromDieValue() {

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

	private boolean noBlackCheckerOnInnerTableFartherAwayFromDieValue() {

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

	@Override
	public int getNumberOfMovesLeft() {
		return movesDoubled() ? DOUBLE * GameImpl.STANDARD_NUM_OF_MOVES
				- game.getNumberOfMovesMade() : GameImpl.STANDARD_NUM_OF_MOVES
				- game.getNumberOfMovesMade();
	}

	public ArrayList<Location> getPotentialFromLocations() {

		ArrayList<Location> potentialFromLocations = new ArrayList();

		for (Location loc : Location.values()) {
			if (game.getBoard().get(loc.ordinal()).color == game
					.getPlayerInTurn()) {
				potentialFromLocations.add(loc);
			}
		}
		return potentialFromLocations;

	}

	private boolean noMovePossible() {

		ArrayList<Location> potentialToLocations;
		ArrayList<Location> potentialFromLocations = getPotentialFromLocations();

		isPotentialMove = true;

		for (int k = 0; k < potentialFromLocations.size(); k++) {
			potentialToLocations = getPotentialToLocations();

			for (int i = 0; i < potentialToLocations.size(); i++) {
				if (isValid(potentialFromLocations.get(k),
						potentialToLocations.get(i))) {
					isPotentialMove = false;
					return false;
				}
			}
		}

		isPotentialMove = false;
		printPotentialLocations(potentialFromLocations);
		System.out.println("N0 MOVE POSSIBLE!!");
		return true;
	}

	public ArrayList<Location> getPotentialToLocations() {

		ArrayList<Location> potentialToLocations = new ArrayList<Location>();

		for (Location loc : Location.values()) {
			potentialToLocations.add(loc);
		}

		return potentialToLocations;

	}

	private void printPotentialLocations(ArrayList<Location> potentialLocations) {
		System.out.println("Potential locations are: ");
		for (int i = 0; i < potentialLocations.size(); i++) {
			System.out.println(potentialLocations.get(i));
		}
	}

}
