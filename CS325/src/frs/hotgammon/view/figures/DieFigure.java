package frs.hotgammon.view.figures;

import java.awt.Point;
import frs.hotgammon.framework.Color;
import minidraw.standard.*;

public class DieFigure extends ImageFigure {

	public DieFigure(String string, Point point) {

		super(string, point);
		System.out.println("Die was created");

	}
	
	public String toString() {
		
		return "die";
	}

}
