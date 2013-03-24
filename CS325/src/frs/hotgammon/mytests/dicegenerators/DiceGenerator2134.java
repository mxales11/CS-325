package frs.hotgammon.mytests.dicegenerators;

import frs.hotgammon.Game;
import frs.hotgammon.RandomDiceGenerator;

public class DiceGenerator2134 implements RandomDiceGenerator {


	private Game game;
	private int[] diceThrown = new int[2];
	
	
	public void setGame(Game game) {
		this.game = game;
	}

	public int[] diceThrown() {
		return diceThrown;
	}


	@Override
	public int[] generateRandomDice(int turnNumber) {
		if (((turnNumber) % 2) == 0) {
			diceThrown = new int[] { 2, 1 };
			return diceThrown;

		} else if (((turnNumber) % 2) == 1) {
			diceThrown = new int[] { 3, 4 };
			return diceThrown;
		}
		else {
			return null;
		}	
	}

	
}

