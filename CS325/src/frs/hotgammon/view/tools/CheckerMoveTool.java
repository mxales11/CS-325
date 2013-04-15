package frs.hotgammon.view.tools;

import frs.hotgammon.view.*;
import frs.hotgammon.view.figures.CheckerFigure;

import java.awt.Point;
import java.awt.event.MouseEvent;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class CheckerMoveTool extends AbstractTool {

	private Game game;
	private Location lastFromLocation = Location.R1;
	private Point pointFrom = new Point(0,0);
	private Point pointTo = new Point(0, 0);

	public CheckerMoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;

	}

	public void mouseUp(MouseEvent e, int x, int y) {

		Location to = Convert.xy2Location(x, y);
		pointTo = new Point(x, y);

		for (Figure f : editor().drawing().selection()) {

			if (playerMovesHisChecker(f)) {
				if (!(game.move(lastFromLocation, to))) {

					System.out.println("The move from " + lastFromLocation
							+ " to " + to + " is illegal");
					moveBackToOriginalPosition();
				}
			}

		}

		super.mouseUp(e, x, y);
	}

	public void mouseDown(MouseEvent e, int x, int y) {

		super.mouseDown(e, x, y);
		//if(there is a checker on this location){
		Location from = Convert.xy2Location(x, y);
		//}
		System.out.println("From is " + from);

		lastFromLocation = from;
		pointFrom = new Point(x, y);
		

}

	private void moveBackToOriginalPosition() {

		System.out
				.println("Dragged figure "
						+ Convert.xy2Location((int) pointFrom.getX(),
								(int) pointTo.getX()) + "to " 
						+ Convert.xy2Location((int) pointFrom.getY(), (int) pointTo.getY()));

		for (Figure f : editor().drawing().selection()) {

			int xToMove = (int) (pointFrom.getX() - pointTo.getX());
			int yToMove = (int) (pointFrom.getY() - pointTo.getY());

			f.moveBy(xToMove, yToMove);

		}

	}

	public String toString() {
		return "CheckerMoveTool";
	}

	private boolean playerMovesHisChecker(Figure f) {
		boolean toReturn = ((CheckerFigure) f).getColor() == game
				.getPlayerInTurn();
		System.out.println("Player moves his Checker" + toReturn);
		return toReturn;

	}

}
