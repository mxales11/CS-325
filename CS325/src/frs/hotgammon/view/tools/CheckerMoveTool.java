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
	private Location lastFromLocation;
	private Point pointLastMovedFrom;
	private Point pointLastMovedTo;

	public CheckerMoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;

	}

	public void mouseUp(MouseEvent e, int x, int y) {

		Location to = Convert.xy2Location(x, y);
		pointLastMovedTo = new Point(x, y);

		for (Figure f : editor().drawing().selection()) {

			if (playerMovesHisChecker(f)) {
				if (!(game.move(lastFromLocation, to))) {
					
					System.out
							.println("The move from " + lastFromLocation + " to " + to + " is illegal");
					moveBackToOriginalPosition();
				}
			}

		}
		pointLastMovedTo = null;
		pointLastMovedFrom = null;
		super.mouseUp(e, x, y);
	}

	public void mouseDown(MouseEvent e, int x, int y) {

		Location from = Convert.xy2Location(x, y);
		lastFromLocation = from;
		pointLastMovedFrom = new Point(x, y);
		super.mouseDown(e, x, y);

	}

	private void moveBackToOriginalPosition() {

		for (Figure f : editor().drawing().selection()) {

			int xToMove = -1
					* (int) (pointLastMovedTo.getX() - pointLastMovedFrom
							.getX());
			int yToMove = -1
					* (int) (pointLastMovedTo.getY() - pointLastMovedFrom
							.getY());

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
