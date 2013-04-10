package frs.hotgammon;

import frs.hotgammon.framework.Game;

public interface RulesFactory {
	
	public void setGame(Game game);
	
	public MoveValidator createMoveValidator();
	public WinnerDeterminer createWinnerDeterminer();
	public TurnDeterminer createTurnDeterminer();
	public RollDeterminer createRollDeterminer();
	
	
}
