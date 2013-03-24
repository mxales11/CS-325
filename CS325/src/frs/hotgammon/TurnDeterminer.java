package frs.hotgammon;

public interface TurnDeterminer {
	
	public void nextTurn(boolean changePlayer);
	public void setGame(Game game);

}
