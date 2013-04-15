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
	
	//to juz chyba nie potrzebuje States bo  robi wszystko w dobrym momencie
	
	public BackGammonDrawing() {
		
	}
	
	//add that as game observer
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
		
		//jezeli tu doszlo to znaczy ze move jest legal
		System.out.println("CHECKER MOVE METHOD WAS INVOKED");
		//redraw checkers
	}
	
	

	@Override
	public void diceRolled(int[] values) {
		
		System.out.println("DICE ROLLED METHOD WAS INVOKED");
		//redraw diceValues	
	}

}
