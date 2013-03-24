package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.common.GameImpl;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer {

	Game game;

	@Override
	public void setGame(Game game) {
		this.game = game;

	}

	@Override
	public void nextTurn(boolean changePlayer) {

		if (changePlayer) {
			((GameImpl) game).getRulesFactory().setGame(game);
			((GameImpl) game).changePlayer();
		}

		if (((GameImpl) game).diceThrownAre12Or21()
				|| ((GameImpl) game).diceThrownEqual()) {
			((GameImpl) game).setChangePlayer(false);
		}

		else {
			((GameImpl) game).setChangePlayer(true);
		}

	}

}
