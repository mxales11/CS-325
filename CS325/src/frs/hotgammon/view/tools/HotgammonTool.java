package frs.hotgammon.view.tools;

import java.awt.event.MouseEvent;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.view.figures.CheckerFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

public class HotgammonTool extends SelectionTool {

	private Tool checkerMoveTool;
	private Tool diceRollTool;
	private Tool currentTool;
	private GameImpl game;

	public HotgammonTool(DrawingEditor editor, GameImpl game) {
		super(editor);

		this.game = game;
		this.checkerMoveTool = new CheckerMoveTool(editor, game);
		this.diceRollTool = new DiceRollTool(editor, game);
		this.currentTool = getCurrentTool();
	}

	public void mouseDrag(MouseEvent e, int x, int y) {

		for (Figure f : editor().drawing().selection()) {

			if (toolMovesItsFigure(f, currentTool)
					&& currentTool.equals(checkerMoveTool)
					&& ((CheckerFigure) f).getColor() == game.getPlayerInTurn()) {
				super.mouseDrag(e, x, y);
			}
		}
	}

	public void mouseUp(MouseEvent e, int x, int y) {

		for (Figure f : editor().drawing().selection()) {
			
			System.out.println("Current state is "+ game.getGameState());

			if (toolMovesItsFigure(f, currentTool) && currentTool != null
					&& !(currentTool instanceof NullTool)) {
				currentTool.mouseUp(e, x, y);

			}
			super.mouseUp(e, x, y);
		}
	}

	public void mouseDown(MouseEvent e, int x, int y) {

		super.mouseDown(e, x, y);

		currentTool = getCurrentTool();

		for (Figure f : editor().drawing().selection()) {

			if (toolMovesItsFigure(f, currentTool)) {

				System.out.println("CURRENT TOOL IS" + currentTool);
				currentTool.mouseDown(e, x, y);

			}

		}
		if (currentTool instanceof NullTool) {
			printWarning();
		}

	}

	private void printWarning() {

		for (int i = 0; i < game.getGameObserversList().size(); i++) {
			game.getGameObserversList().get(i)
					.changeStatusField("Game has already finished!");
		}
	}

	private Tool getCurrentTool() {

		return (game.getGameState().toString().equals("MoveCheckerState")) ? checkerMoveTool
				: (game.getGameState().toString().equals("DiceRollState")) ? diceRollTool
						: new NullTool();
	}

	public String toString() {
		return super.toString();
	}

	private boolean toolMovesItsFigure(Figure f, Tool tool) {

		boolean toReturn = tool.toString().toLowerCase()
				.indexOf(f.toString().toLowerCase()) != -1 ? true : false;
		return toReturn && tool != null;

	}

}