package frs.hotgammon.view.figures;

import java.awt.Point;
import frs.hotgammon.framework.Color;
import minidraw.standard.*;

public class DieFigure extends ImageFigure {

	public DieFigure(String string, Point point) {
		super(string, point);
	}
	
	public String toString() {	
		return "dice";
	}

}
