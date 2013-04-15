package frs.hotgammon.view.figures;
import java.awt.Point;
import frs.hotgammon.framework.Color;
import minidraw.standard.ImageFigure;

public class CheckerFigure extends ImageFigure {
	
	private Color color;

	public CheckerFigure(Color color, Point point) {
		
		super(color.toString().toLowerCase() + "checker", point);
		this.color = color;
		System.out.println("Checker was created");
	}
	
	public Color getColor() {
		return color;
	
	}
	
	public String toString() {
		
		return "checker";
	}

}
