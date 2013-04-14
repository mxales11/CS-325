package frs.hotgammon.view.tools;

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

	public CheckerMoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
		
	}

	public void mouseUp(MouseEvent e, int x, int y) {
				
				game.move(Location.B1, Location.B2);
				System.out.println("You moved from location B1 to B2");
	}

	public String toString() {
		return "CheckerMoveTool";	
	}
}
