package frs.hotgammon;

import frs.hotgammon.framework.Color;

public class Square {
	
	public Color color;
	public int occupants;
	//public Object occupants;

	public Square() {
		this.occupants = 0;
		this.color = Color.NONE;
	}

}