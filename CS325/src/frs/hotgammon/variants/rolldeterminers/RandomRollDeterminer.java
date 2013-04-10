package frs.hotgammon.variants.rolldeterminers;

import frs.hotgammon.RandomDiceGenerator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.framework.Game;

public class RandomRollDeterminer implements RollDeterminer {

	private Game game;
	private RandomDiceGenerator randomDiceGenerator;
	private int[] diceThrown;
	private int turnNumber;
	
	public RandomRollDeterminer(RandomDiceGenerator randomDiceGenerator){
		this.randomDiceGenerator = randomDiceGenerator;
	}
	
	public void setRandomDiceGenerator(RandomDiceGenerator randomDiceGenerator){
		turnNumber = 0;
		this.randomDiceGenerator = randomDiceGenerator;
		
		
	}
	
	public RandomDiceGenerator getRandomRollGenerator(){
		
		return randomDiceGenerator;
	}

	public void setGame(Game game) {
		this.game = game;
		randomDiceGenerator.setGame(game);
	}

		
	public int[] diceThrown() {
		return diceThrown;
	}



	public void rollDice(int turnNumber) {
		this.turnNumber = turnNumber;
		diceThrown = randomDiceGenerator.generateRandomDice(turnNumber);
	}

	



		
}

