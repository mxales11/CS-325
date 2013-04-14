package frs.hotgammon.view.tools;

import frs.hotgammon.view.*;
import java.awt.event.MouseEvent;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;
import frs.hotgammon.view.figures.CheckerFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;

import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;

public class CheckerMoveTool extends AbstractTool {

	private Game game;
	private Location lastFromLocation;

	public CheckerMoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
		
	}
	
	public void mouseUp(MouseEvent e, int x, int y) {
		
		super.mouseUp(e, x, y);
		Location to = Convert.xy2Location(x, y); 
		game.move(lastFromLocation, to);
		lastFromLocation = null;
	}

	public void mouseDown(MouseEvent e, int x, int y) {
		
		Location from = Convert.xy2Location(x, y);
		lastFromLocation = from;
		//how do i get back if it returns false
		
	}

	public String toString() {
		return "CheckerMoveTool";	
	}
}
