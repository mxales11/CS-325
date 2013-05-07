package frs.hotgammon.common;

import java.util.ArrayList;
import frs.hotgammon.Board;
import frs.hotgammon.Square;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;

public class BoardImpl extends ArrayList<Square> implements Board {

	public static final ArrayList<Location> blackInnerTable = new ArrayList<Location>() {
		{

			this.add(Location.B1);
			this.add(Location.B2);
			this.add(Location.B3);
			this.add(Location.B4);
			this.add(Location.B5);
			this.add(Location.B6);
		}
	};

	public static final ArrayList<Location> redInnerTable = new ArrayList<Location>() {
		{

			this.add(Location.R1);
			this.add(Location.R2);
			this.add(Location.R3);
			this.add(Location.R4);
			this.add(Location.R5);
			this.add(Location.R6);
		}
	};

	public void initializeBoard() {

		for (Location location : Location.values()) {
			this.add(new Square());
		}
	}

	public BoardImpl(int numberOfAllLocations) {
		initializeBoard();
	}

	public void move(Location from, Location to) {

		Square fromC = this.get(from.ordinal());
		Square toC = this.get(to.ordinal());

		this.updateCounts(fromC, toC);
		this.updateColors(fromC, toC);

	}

	public void updateCounts(Square fromC, Square toC) {
		fromC.occupants--;
		toC.occupants++;
	}

	public void updateColors(Square fromC, Square toC) {

		toC.color = fromC.color;

		if (fromC.occupants == 0) {
			fromC.color = Color.NONE;

		}
	}

	public boolean place(Color player, int index) {

		this.get(index).occupants++;
		this.get(index).color = player;

		return true;
	}

	public Square getSquare(int index) {

		return this.get(index);

	}

	public boolean remove(Color color, int index) {

		if (this.get(index).color == color) {

			this.get(index).occupants--;
			this.get(index).color = Color.NONE;
			return true;
		}
		return false;
	}

}