package frs.hotgammon.view.figures;

import java.awt.Point;

import minidraw.standard.*;

public class DieFigure extends ImageFigure {

	public DieFigure(String string, Point point) {
		
		super(string, point);
		//zmien to tak jak G zeby nie byl to string
		System.out.println("Die figure was invoked");

	}

}
