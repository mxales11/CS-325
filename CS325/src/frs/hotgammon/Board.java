package frs.hotgammon;

import java.util.List;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Location;

public interface Board extends List<Square>{

		public void initializeBoard();

		public void move(Location from, Location to);

		public void updateCounts(Square fromC, Square toC);
		
		public void updateColors(Square fromC, Square toC);

		public boolean place(Color player, int integer);

}
