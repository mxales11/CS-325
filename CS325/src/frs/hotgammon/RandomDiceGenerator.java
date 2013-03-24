package frs.hotgammon;


public interface RandomDiceGenerator {
	
	public void setGame(Game game);
	public int[] generateRandomDice(int turnNumber);
	
		
}
