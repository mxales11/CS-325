package frs.hotgammon.view.drawings;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import minidraw.customized.helpers.Coordinates;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.FigureChangeEvent;
import minidraw.framework.Figure.*;
import minidraw.standard.ImageFigure;
import minidraw.standard.StandardDrawing;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.GameState;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.HotgammonTool;
import frs.hotgammon.view.*;

public class HotgammonDrawing extends StandardDrawing implements GameObserver {

	private ArrayList<CheckerFigure> checkerList = new ArrayList<CheckerFigure>();
	private ArrayList<DieFigure> diceList = new ArrayList<DieFigure>();
	private Game game;
	private DrawingEditor editor;

	public HotgammonDrawing(DrawingEditor editor, Game game) {

		this.editor = editor;
		this.game = game;
		initializeCheckers();

	}

	// read it from model instead of initializing here
	private void initializeCheckers() {

		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R1, 0)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R1, 1)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R6, 0)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R6, 1)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R6, 2)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R6, 3)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R6, 4)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R8, 0)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R8, 1)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R8, 2)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R12, 0)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R12, 1)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R12, 2)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R12, 3)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.R12, 4)));

		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B1, 0)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B1, 1)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B6, 0)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B6, 1)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B6, 2)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B6, 3)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B6, 4)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B8, 0)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B8, 1)));
		this.add(new CheckerFigure(Color.BLACK, Convert.locationAndCount2xy(
				Location.B8, 2)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B12, 0)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B12, 1)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B12, 2)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B12, 3)));
		this.add(new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.B12, 4)));

		System.out.println("Checker list is " + checkerList.size());

	}

	public Figure add(Figure figure) {

		if (figure.toString().equals("checker")) {
			checkerList.add((CheckerFigure) figure);
		}

		if (figure.toString().equals("dice")) {

			diceList.add((DieFigure) figure);
		}

		System.out.println("ADD WAS INVOKED");
		return super.add(figure);

	}

	public Figure remove(Figure figure) {

		if (figure instanceof CheckerFigure) {

			checkerList.remove((CheckerFigure) figure);
		}

		if (figure instanceof DieFigure) {
			System.out.println("Instance of die figure");
			diceList.remove((DieFigure) figure);
		}

		System.out.println("REMOVE WAS INVOKED");
		return super.remove(figure);

	}

	private Location getLocationToDrawTo(CheckerFigure foundFigure) {

		Coordinates figureCenter = getFigureCenter(foundFigure.displayBox()
				.getX(), foundFigure.displayBox().getY(), foundFigure
				.displayBox().getWidth(), foundFigure.displayBox().getHeight());

		Location locationToDrawTo = Convert.xy2Location(
				(int) (figureCenter.getX()), (int) (figureCenter.getY()));

		return locationToDrawTo;

	}

	private CheckerFigure createNewCheckerFigure(Location locationToDrawTo,
			Color color) {

		CheckerFigure newChecker = new CheckerFigure(color,
				Convert.locationAndCount2xy(locationToDrawTo,
						game.getCount((locationToDrawTo)) - 1));
		return newChecker;

	}

	private CheckerFigure checkerAtTargetedSpot(CheckerFigure checkerFigure) {

		for (int i = 0; i < checkerList.size(); i++) {

			if (checkerList.get(i).atTheSameSpot(checkerFigure)) {

				return checkerList.get(i);
			}
		}

		return null;

	}

	@Override
	public void checkerMove(Location from, Location to) {

		CheckerFigure foundFigure;
		System.out.println("CHECKER MOVE METHOD WAS INVOKED");

		for (Figure f : editor.drawing().selection()) {
			foundFigure = (CheckerFigure) f;

			if (foundFigure != null) {

				this.remove(foundFigure);

				Location locationToDrawTo = this
						.getLocationToDrawTo(foundFigure);
				CheckerFigure newChecker = this.createNewCheckerFigure(
						locationToDrawTo, foundFigure.getColor());

				CheckerFigure checkerAtTargetedSpot = checkerAtTargetedSpot(newChecker);
				
				if (checkerAtTargetedSpot == null) {
					this.add(newChecker);
				} 
				
				else if (checkerAtTargetedSpot.getColor() != newChecker
						.getColor()) {
					this.remove(checkerAtTargetedSpot);
					this.add(newChecker);
					moveToTheBar(checkerAtTargetedSpot);
					
				}
				

				else {
					System.out
							.println("Something FAILED");
				}

			}
		}
	}

	private void moveToTheBar(CheckerFigure checkerAtTargetedSpot) {
		
		Color barCheckerColor = checkerAtTargetedSpot.getColor();
		
		//change 0 to how many checkers is already there
		CheckerFigure checkerToGoToTheBar = new CheckerFigure(barCheckerColor, Convert.locationAndCount2xy(
				getCorrectBar(barCheckerColor), 0));
				this.add(checkerToGoToTheBar);
		
	}
	
	private Location getCorrectBar(Color color) {
		
		return (color==Color.RED)?Location.R_BAR:Location.B_BAR;
			
	}
	
	private Coordinates getFigureCenter(double x, double y, double width,
			double height) {

		final double HALF = 2.0;
		return new Coordinates(x + (width / HALF), y + (height / HALF));
	}

	@Override
	public void diceRolled(int[] values) {

		System.out.println("Dice values lenght is 2");
		if (values != null && values.length == 2) {

			System.out.println("DICE ROLLED METHOD WAS INVOKED");

			for (int i = 0; i < values.length; i++) {

				this.remove(diceList.get(0));
			}
			DieFigure redDie = new DieFigure("die" + String.valueOf(values[0]),
					new Point(216, 202));

			DieFigure blackDie = new DieFigure("die"
					+ String.valueOf(values[1]), new Point(306, 202));

			this.add(redDie);
			this.add(blackDie);

			this.figureChanged(new FigureChangeEvent(redDie));
			this.figureChanged(new FigureChangeEvent(blackDie));
			this.changed();
			System.out.println("DICE ROLLED METHOD WAS INVOKED");
		}

	}

}