package frs.hotgammon.view.figures;

import java.awt.Point;

import minidraw.standard.ImageFigure;

public class CheckerFigure extends ImageFigure {

	public CheckerFigure(String string, Point point) {
		
		super(string, point);
		//zmien to tak jak G zeby nie byl to string
		System.out.println("Checker figure was invoked");
		
	}


}
