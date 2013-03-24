package frs.hotgammon.mytests.dicegenerators;

import frs.hotgammon.Game;
import frs.hotgammon.RandomDiceGenerator;

public class DiceGenerator123456 implements RandomDiceGenerator {

	private Game game;
	private int[] diceThrown = new int[2];

	public void setGame(Game game) {
		this.game = game;
	}

	public int[] diceThrown() {
		return diceThrown;
	}

	public int[] generateRandomDice(int turnNumber) {

		if (((turnNumber) % 3) == 0) {
			diceThrown = new int[] { 1, 2 };
			return diceThrown;

		} else if (((turnNumber) % 3) == 1) {
			diceThrown = new int[] { 3, 4 };
			return diceThrown;
		} else if ((turnNumber) % 3 == 2) {
			diceThrown = new int[] { 5, 6 };
			return diceThrown;
		}

		else {
			return null;
		}
	}

}
