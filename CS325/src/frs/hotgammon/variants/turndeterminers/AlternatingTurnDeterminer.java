package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.common.GameImpl;

public class AlternatingTurnDeterminer implements TurnDeterminer {

	Game game;

	@Override
	public void setGame(Game game) {
		this.game = game;

	}

	@Override
	public void nextTurn(boolean changePlayer) {

		if (changePlayer) {
			((GameImpl) game).changePlayer();
			((GameImpl) game).getRulesFactory().setGame(game);
		} else {
			((GameImpl) game).setChangePlayer(true);
		}

	}

}
