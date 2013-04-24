package frs.hotgammon.common;

import java.util.ArrayList;
import java.util.Arrays;
import frs.hotgammon.Board;
import frs.hotgammon.RulesFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.GameState;
import frs.hotgammon.states.GameStateImpl;
import frs.hotgammon.states.MoveCheckerState;

public class GameImpl implements Game {

	public Color playerInTurn = Color.NONE;
	private Board board;

	public int turnNumber = 0;
	public int[] diceValuesLeft;
	public int numberOfMovesMade = 0;
	public boolean changePlayer = false;
	public boolean movesDoubled = false;
	public int currentDistanceTravelled = 0;
	public static final int NUMBER_OF_DICE = 2;
	public static final int MINIMAL_NUM_OF_MOVES_TO_WIN_GAME = 6;
	public static final int STANDARD_NUM_OF_MOVES = 2;

	private MoveValidator moveValidator;
	private TurnDeterminer turnDeterminer;
	private RollDeterminer rollDeterminer;
	private WinnerDeterminer winnerDeterminer;
	private RulesFactory rulesFactory;

	private GameState currentState;
	private ArrayList<GameObserver> gameObserversList = new ArrayList<GameObserver>();

	public void setState(GameState gameState) {
		this.currentState = gameState;
	}

	public int getNumberOfMovesMade() {

		return numberOfMovesMade;
	}

	public RulesFactory getRulesFactory() {
		return rulesFactory;
	}

	public GameImpl(RulesFactory rulesFactory) {

		setUpRules(rulesFactory);
	}

	public void setUpRules(RulesFactory rulesFactory) {

		this.rulesFactory = rulesFactory;
		this.rulesFactory.setGame(this);

		moveValidator = rulesFactory.createMoveValidator();
		turnDeterminer = rulesFactory.createTurnDeterminer();
		winnerDeterminer = rulesFactory.createWinnerDeterminer();
		rollDeterminer = rulesFactory.createRollDeterminer();
	}

	public void setMoveValidator(MoveValidator moveValidator) {
		this.moveValidator = moveValidator;
	}

	public void setUpBoard(BoardImpl board) {
		board.get(Location.R1.ordinal()).occupants = 2;
		board.get(Location.R6.ordinal()).occupants = 5;
		board.get(Location.R8.ordinal()).occupants = 3;
		board.get(Location.R12.ordinal()).occupants = 5;
		board.get(Location.B1.ordinal()).occupants = 2;
		board.get(Location.B6.ordinal()).occupants = 5;
		board.get(Location.B8.ordinal()).occupants = 3;
		board.get(Location.B12.ordinal()).occupants = 5;
		board.get(Location.R1.ordinal()).color = Color.BLACK;
		board.get(Location.R6.ordinal()).color = Color.RED;
		board.get(Location.R8.ordinal()).color = Color.RED;
		board.get(Location.R12.ordinal()).color = Color.BLACK;
		board.get(Location.B1.ordinal()).color = Color.RED;
		board.get(Location.B6.ordinal()).color = Color.BLACK;
		board.get(Location.B8.ordinal()).color = Color.BLACK;
		board.get(Location.B12.ordinal()).color = Color.RED;

	}

	private void resetGame() {

		playerInTurn = Color.NONE;
		turnNumber = 0;
		board = new BoardImpl(25);
		setUpBoard((BoardImpl) board);
		currentDistanceTravelled = 0;
		diceValuesLeft = null;
		changePlayer = false;

	}

	public void newGame() {

		board = new BoardImpl(25);
		resetGame();
		this.rulesFactory.setGame(this);
		System.out.println("New game was started");
		currentState = new GameStateImpl(this);
	
	}

	public void changePlayer() {

		if (getPlayerInTurn() == Color.BLACK) {

			playerInTurn = Color.RED;

		} else if (getPlayerInTurn() == Color.RED) {

			playerInTurn = Color.BLACK;
		} else {
			playerInTurn = Color.NONE;
		}
		
	}

	public void nextTurn() {

		currentState.nextTurn();

	}

	public Color getStartingPlayer() {

		if ((diceThrown()[0]) != (diceThrown()[1])) {
			return diceThrown()[0] > diceThrown()[1] ? Color.RED : Color.BLACK;
		}

		System.out.println("Starting player is null");
		return Color.NONE;

	}

	public void sendToTheBar(Location from, Location to) {

		System.out
				.println("Was sent to the bar!!!!!!!!!!!!!! Invoke checkerMove to redraw");
		Color player = this.getColor(to);
		Location bar = player == Color.RED ? Location.R_BAR : Location.B_BAR;
		this.getBoard().move(to, bar);

		
	}

	public boolean move(Location from, Location to) {

		return currentState.move(from, to);

	}

	public Color getPlayerInTurn() {

		System.out.println("Player in turn is" + playerInTurn);
		return playerInTurn;
	}

	public void setDieValueLeft(int firstDie, int secondDie) {

		if (diceValuesLeft == null) {
			diceValuesLeft = new int[2];
		}

		diceValuesLeft[0] = firstDie;
		diceValuesLeft[1] = secondDie;
	}

	public boolean diceThrownAre12Or21() {

		int[] diceThrown = diceThrown();
		return (diceThrown[0] == 1 && diceThrown[1] == 2)
				|| (diceThrown[0] == 2 && diceThrown[1] == 1);

	}

	public int[] diceThrown() {

		int[] diceThrown = rollDeterminer.diceThrown();

		if (diceThrown != null) {
			System.out.println("Dice thrown are " + diceThrown[0] + " and "
					+ diceThrown[1]);
		}

		else {
			System.out.println("DICE THROWN ARE NULL");
		}

		return diceThrown;
	}

	public ArrayList<Integer> convertArrayIntoArrayList() {

		ArrayList<Integer> arrayList = new ArrayList<Integer>(
				diceValuesLeft().length);

		for (int i = 0; i < diceValuesLeft.length; i++) {
			arrayList.add(new Integer(diceValuesLeft[i]));
		}
		return arrayList;
	}

	private void convertArrayListIntoArray(ArrayList<Integer> arrayList) {

		int[] diceValuesLeft = new int[arrayList.size()];

		for (int i = 0; i < arrayList.size(); i++) {
			diceValuesLeft[i] = arrayList.get(i);
		}

		this.diceValuesLeft = diceValuesLeft;

	}

	public void updateDiceValuesLeft() {

		ArrayList<Integer> diceValuesLeftList = convertArrayIntoArrayList();
		int index = diceValuesLeftList.indexOf(currentDistanceTravelled);

		if (index != -1) {
			diceValuesLeftList.remove(index);
		} else {
			diceValuesLeftList.remove(0);
		}
		convertArrayListIntoArray(diceValuesLeftList);

	}

	public boolean diceThrownEqual() {

		if (diceThrown() != null && diceThrown()[0] != 0) {
			return (diceThrown()[0] == diceThrown()[1]);
		}
		return false;

	}

	private void reverseArrayWith2Elements(int[] array) {

		if (array.length == NUMBER_OF_DICE) {
			int temp = array[0];
			array[0] = array[1];
			array[1] = temp;
		}
	}

	public int[] diceValuesLeft() {

		if (diceValuesLeft != null && diceValuesLeft.length == NUMBER_OF_DICE) {
			Arrays.sort(diceValuesLeft);
			reverseArrayWith2Elements(diceValuesLeft);
		}
		return diceValuesLeft;
	}

	public Color winner() {

		if (rulesFactory.createWinnerDeterminer().isWinner(turnNumber)) {
			if (turnNumber == MINIMAL_NUM_OF_MOVES_TO_WIN_GAME) {

				return Color.RED;
			} else {
				return this.getPlayerInTurn();
			}
		}
		return Color.NONE;
	}

	public Color getColor(Location location) {
		return ((BoardImpl) board).get(location.ordinal()).color;
	}

	public int getCount(Location location) {
		return ((BoardImpl) board).get(location.ordinal()).occupants;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	public void configure(Placement[] placements) {

		board = new BoardImpl(25);

		if (placements != null) {
			for (int i = 0; i < placements.length; i++) {
				board.place(placements[i].player,
						placements[i].location.ordinal());

			}
		}
	}

	public static class Placement {

		public Location location;
		public Color player;

		public Placement(Color player, Location location) {
			this.player = player;
			this.location = location;
		}
	}

	public RollDeterminer getRollDeterminer() {

		return rollDeterminer;
	}

	public void setChangePlayer(boolean changePlayer) {

		this.changePlayer = changePlayer;
	}

	public boolean getMovesDoubled() {
		return movesDoubled;
	}

	@Override
	public GameState getGameState() {
		return currentState;
	}

	@Override
	public void addObserver(GameObserver observer) {
		gameObserversList.add(observer);
	}

	public void setCurrentState(GameStateImpl gameState) {
		this.currentState = gameState;
	}

	public int getNumberOfMovesLeft() {

		int movesLeft = moveValidator.getNumberOfMovesLeft();
		System.out.println(movesLeft);
		return movesLeft;
	}

	public ArrayList<GameObserver> getGameObserversList() {

		return gameObserversList;
	}

}
