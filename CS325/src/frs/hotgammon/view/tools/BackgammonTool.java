package frs.hotgammon.view.tools;

import java.awt.event.MouseEvent;
import frs.hotgammon.framework.Game;
import frs.hotgammon.states.DiceRollState;
import frs.hotgammon.states.MoveCheckerState;
import frs.hotgammon.view.figures.CheckerFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.DragTracker;

public class BackgammonTool extends SelectionTool {

	private Tool checkerMoveTool;
	private Tool diceRollTool;
	private Tool currentTool;
	private Game game;

	public BackgammonTool(DrawingEditor editor, Game game) {
		super(editor);

		this.game = game;
		this.checkerMoveTool = new CheckerMoveTool(editor, game);
		this.diceRollTool = new DiceRollTool(editor, game);
		this.currentTool = getCurrentTool();
	}

	public void mouseDrag(MouseEvent e, int x, int y) {

		for (Figure f : editor().drawing().selection()) {
			
			if (f instanceof CheckerFigure && currentTool.equals(checkerMoveTool)) {
				super.mouseDrag(e, x, y);
			}
		}
	}

	public void mouseUp(MouseEvent e, int x, int y) {

		currentTool = getCurrentTool();
		
		System.out.println("CURRENT TOOL IS" + currentTool);
		
		currentTool.mouseUp(e, x, y);
		
		if (currentTool instanceof NullTool) {
			printWarning();
		}
		super.mouseUp(e, y, y);

	}

	private void printWarning() {
		
		System.out
				.println("Game has not started yet or game has already finish. Start a new Game!!!");
	}
	
	
	private Tool getCurrentTool() {
		
		return (game.getGameState().toString().equals("MoveCheckerState")) ? checkerMoveTool
				: (game.getGameState().toString().equals("DiceRollState")) ? diceRollTool:
					new NullTool();	
	}

	public String toString() {
		return super.toString();
	}

}