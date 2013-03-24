package frs.hotgammon.variants.rolldeterminers;

import frs.hotgammon.Game;
import frs.hotgammon.RandomDiceGenerator;
import frs.hotgammon.RollDeterminer;

public class PairSequenceDeterminer implements RollDeterminer {

	private Game game;
	private int[] diceThrown;
	
	
	public void setGame(Game game) {
		this.game = game;
	}

	public int[] diceThrown() {
		return diceThrown;
	}


	public void rollDice(int turnNumber) {
		
		if (((turnNumber) % 3) == 0) {
			diceThrown =  new int[] { 1, 2 };

		} else if (((turnNumber) % 3) == 1) {
			diceThrown = new int[] { 3, 4 };
		} else if ((turnNumber) % 3 == 2) {
			diceThrown = new int[] { 5, 6 };
		}

		else {
			diceThrown = null;
		}	
	}

	@Override
	public void setRandomDiceGenerator(RandomDiceGenerator randomDiceGenarator) {
		// TODO Auto-generated method stub
		
	}

	


	

}
