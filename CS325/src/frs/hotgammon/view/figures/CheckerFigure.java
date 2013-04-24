package frs.hotgammon.view.figures;
import java.awt.Point;
import frs.hotgammon.framework.Color;
import minidraw.standard.ImageFigure;

public class CheckerFigure extends ImageFigure {
	
	private Color color = Color.NONE;
	private Point point;

	public CheckerFigure(Color color, Point point) {
		super(color.toString().toLowerCase() + "checker", point);
		this.point = point;
		this.color = color;
	}
	
	
	public boolean equals(CheckerFigure checkerFigure) {
		System.out.println("Correct equals was invoked");
		return this.color==checkerFigure.color;
	}
	
	public boolean atTheSameSpot(CheckerFigure checkerFigure){
		
		return this.point.getX()==checkerFigure.point.getX() && this.point.getY()==checkerFigure.point.getY();
	}
	
	
	
	public Color getColor() {
		return color;
	
	}

	public Point getPoint() {
		
		return point;
	}
		

	
	public String toString() {
		
		return "checker";
	}

}
