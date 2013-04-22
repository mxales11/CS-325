package frs.hotgammon.mytests.dicegenerators;

import frs.hotgammon.RandomDiceGenerator;
import frs.hotgammon.framework.Game;

public class DiceGenerator44 implements RandomDiceGenerator {
	
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
	
			diceThrown = new int[] { 4, 4 };
			return diceThrown;
	}

	
	
	
}

