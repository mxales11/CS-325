package frs.hotgammon;

public interface MoveValidator {

	public boolean isValid(Location from, Location to);
	public void setGame(Game game);
	
}
