package frs.hotgammon.view.drawings;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import minidraw.customized.helpers.Coordinates;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.FigureChangeEvent;
import minidraw.framework.Figure.*;
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
	//no sates here
	public HotgammonDrawing() {

		initializeCheckers();

	}

	//read it from model
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
	
		if (figure instanceof CheckerFigure) {
			checkerList.add((CheckerFigure) figure);
		}

		if (figure instanceof DieFigure) {
			diceList.add((DieFigure) figure);
		}

		System.out.println("ADD WAS INVOKED");
		// repaint the image
		return super.add(figure);

	}

	public Figure remove(Figure figure) {

		// remove not add

		if (figure instanceof CheckerFigure) {
			checkerList.remove((CheckerFigure) figure);
		}

		if (figure instanceof DieFigure) {
			diceList.remove((DieFigure) figure);
		}

		System.out.println("REMOVE WAS INVOKED");
		// repaint the image
		return super.add(figure);

	}


	@Override
	public void checkerMove(Location from, Location to) {
		
		Figure figure =  new CheckerFigure(Color.RED, Convert.locationAndCount2xy(
				Location.R1, 1));
		
		System.out.println("Size before removing " + checkerList.size());
		System.out.println("Was removed " + checkerList.remove(0));
		System.out.println("Size after removing " + checkerList.size());
		//checkerList.add(to);
		
		// remove one Figure from Location, add another Figure
		// repaint drawing

		System.out.println("CHECKER MOVE METHOD WAS INVOKED");
		System.out.println("CHECKER SHOULD BE REMOVED FROM R1");

		 figure.displayBox();
		super.figureChanged(new FigureChangeEvent(figure));
		this.changed();
		
	}

	@Override
	public void diceRolled(int[] values) {

		// remove one die from list, add other die to list
		// repaint drawing

		// add die
		// this.displayBox(); //drawing box
		this.changed(); // it invoked revalidate somewhere

		// jezeli tu doszlo to znaczy ze jest legal

		System.out.println("DICE ROLLED METHOD WAS INVOKED");

	}

}
