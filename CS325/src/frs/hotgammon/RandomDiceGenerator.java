package frs.hotgammon;

import frs.hotgammon.framework.Game;


public interface RandomDiceGenerator {
	
	public void setGame(Game game);
	public int[] generateRandomDice(int turnNumber);
	
		
}
