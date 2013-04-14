package frs.hotgammon.view.drawings;

import java.util.ArrayList;

import minidraw.customized.helpers.Coordinates;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.StandardDrawing;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;
import frs.hotgammon.states.GameState;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.BackgammonTool;

public class BackGammonDrawing  extends StandardDrawing implements GameObserver  {
	
	//this drawing can redraw drawing but not move staff,it's not a mouse up
	//to juz chyba nie potrzebuje States bo  robi wszystko w dobrym momencie
	
	
	public BackGammonDrawing() {
		
	}

	private ArrayList<CheckerFigure> checkerList = new ArrayList<CheckerFigure>();
	private ArrayList<DieFigure> diceList = new ArrayList<DieFigure>();
	
	
	public void addChecker(CheckerFigure checkerFigure) {
		
		checkerList.add(checkerFigure);
	
	}
	
	public void addDie(DieFigure dieFigure){
		
		diceList.add(dieFigure);
		
	}
	
	
	public void HotGammonDrawing() {
		
		
	}

	
	
	
	@Override
	public void checkerMove(Location from, Location to) {
		
		//redraw checkers
	}
	
	

	@Override
	public void diceRolled(int[] values) {
		
		//redraw diceValues
		
	}

}
