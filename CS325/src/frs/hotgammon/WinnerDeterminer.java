package frs.hotgammon;


public interface WinnerDeterminer {

	public boolean isWinner(int numberOfTurns);
	public void setGame(Game game);
		
}
