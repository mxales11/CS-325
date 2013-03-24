package frs.hotgammon.variants.rules;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.common.GameImpl.Placement;

public class ZetaMon extends AlphaMon {

	Game game;

	public ZetaMon() {

	}

	public void setGame(Game game) {
		
		this.game = game;
		super.setGame(game);

		((GameImpl) game).configure(new Placement[] {
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R2),
				new Placement(Color.BLACK, Location.R3),
				new Placement(Color.RED, Location.B1),
				new Placement(Color.RED, Location.B2),
				new Placement(Color.RED, Location.B3), });
	}

}
