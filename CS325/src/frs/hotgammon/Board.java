package frs.hotgammon;

import java.util.List;

public interface Board extends List<Square>{

		public void initializeBoard();

		public void move(Location from, Location to);

		public void updateCounts(Square fromC, Square toC);
		
		public void updateColors(Square fromC, Square toC);

		public boolean place(Color player, int integer);

}
