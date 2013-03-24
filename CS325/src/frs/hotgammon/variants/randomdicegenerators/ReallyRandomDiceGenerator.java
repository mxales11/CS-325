package frs.hotgammon.variants.randomdicegenerators;

import java.util.Random;

import frs.hotgammon.Game;
import frs.hotgammon.RandomDiceGenerator;
import frs.hotgammon.common.GameImpl;

public class ReallyRandomDiceGenerator implements RandomDiceGenerator {

	private Game game;
	private int[] diceThrown = new int[2];

	public void setGame(Game game) {
		this.game = game;
	}

	public int[] generateRandomDice(int turnNumber) {

		Random generator = new Random();
		diceThrown[0] = generator.nextInt(6) + 1;
		diceThrown[1] = generator.nextInt(6) + 1;
		return diceThrown;
	}

	

}
