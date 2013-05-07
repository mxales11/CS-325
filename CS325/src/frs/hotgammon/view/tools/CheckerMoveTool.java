package frs.hotgammon.view.tools;

import frs.hotgammon.view.*;
import frs.hotgammon.view.figures.CheckerFigure;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import minidraw.customized.helpers.Coordinates;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class CheckerMoveTool extends AbstractTool {

	private GameImpl game;
	private Location lastFromLocation = Location.R1;
	private Point pointFrom = new Point(0, 0);
	private Point pointTo = new Point(0, 0);

	public CheckerMoveTool(DrawingEditor editor, GameImpl game) {
		super(editor);
		this.game = game;

	}

	public void displayIllegalMoveExplanationInStatusField(Location to) {
		
		String rollDie = game.getNumberOfMovesLeft() == 0 ? " Roll dice"
				: "";
		
		if (game.getMoveValidator().noMovePossible()) {
			game.changeStatusField("No moves are possible. Your turn is skipped. Please roll dice.");
		}

		else if (game.winner() != Color.NONE) {
			game.changeStatusField("The winner is "
					+ game.getPlayerInTurn());
		} else {
			game.changeStatusField("The move from "
					+ lastFromLocation + " to " + to
					+ " is illegal. It is "
					+ game.getPlayerInTurn() + "'s turn. "
					+ game.printDiceValuesLeft() + rollDie);
		}

	}

	public void mouseUp(MouseEvent e, int x, int y) {

		Location to = Convert.xy2Location(x, y);
		pointTo = new Point(x, y);

		for (Figure f : editor().drawing().selection()) {
			if (playerMovesHisChecker(f)) {
				if (!(game.move(lastFromLocation, to))) {
					displayIllegalMoveExplanationInStatusField(to);
					moveBackToOriginalPosition();
				}
			}

			else {
				game.changeStatusField("You cannot move. It is "
						+ game.getPlayerInTurn() + "'s" + " turn. "
						+ game.printDiceValuesLeft());
			}
		}

		super.mouseUp(e, x, y);
	}

	public void mouseDown(MouseEvent e, int x, int y) {

		super.mouseDown(e, x, y);
		Location from = Convert.xy2Location(x, y);
		lastFromLocation = from;
		pointFrom = new Point(x, y);
	}

	private void moveBackToOriginalPosition() {

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
		return ((CheckerFigure) f).getColor() == game.getPlayerInTurn();
	}

}
