package frs.hotgammon.view.tools;

import java.awt.event.MouseEvent;
import frs.hotgammon.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;

public class DiceRollTool extends AbstractTool {
	
	private Game game;

	public DiceRollTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		game.nextTurn();	
	}

	
	
	public String toString() {	
		return "DiceRollTool";	
	}
}


