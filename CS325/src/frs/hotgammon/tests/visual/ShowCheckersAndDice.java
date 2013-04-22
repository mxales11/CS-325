package frs.hotgammon.tests.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.*;
import frs.hotgammon.framework.Color;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.tests.stub.StubGame1;
import frs.hotgammon.view.drawings.HotgammonDrawing;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.HotgammonTool;
import frs.hotgammon.view.tools.CheckerMoveTool;
import frs.hotgammon.view.tools.DiceRollTool;
import frs.hotgammon.variants.*;
import frs.hotgammon.variants.rules.AlphaMon;
import frs.hotgammon.variants.rules.BetaMon;
import frs.hotgammon.variants.rules.GammaMon;
import frs.hotgammon.variants.rules.SemiMon;

/**
 * Show the dice and some checkers on the backgammon board.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class ShowCheckersAndDice {

	public static void main(String[] args) {

		Game game = new GameImpl(new SemiMon());
		game.newGame();

		DrawingEditor editor = new MiniDrawApplication(
				"Show HotGammon figures...", new HotGammonFactory(game));
		editor.open();

		HotgammonDrawing model = (HotgammonDrawing) editor.drawing();
		game.addObserver(model);

		Figure redDie = new DieFigure("die4", new Point(216, 202));
		Figure blackDie = new DieFigure("die2", new Point(306, 202));
		model.add(redDie);
		model.add(blackDie);

		editor.setTool(new HotgammonTool(editor, game));

	}
}

class HotGammonFactory implements Factory {

	private Game game;

	public HotGammonFactory(Game game) {
		this.game = game;

	}

	public DrawingView createDrawingView(DrawingEditor editor) {
		DrawingView view = new StdViewWithBackground(editor, "board");
		return view;
	}

	public Drawing createDrawing(DrawingEditor editor) {

		return new HotgammonDrawing(editor, game);

	}

	public JTextField createStatusField(DrawingEditor editor) {
		JTextField statusField = new JTextField("Hello HotGammon...");
		statusField.setEditable(false);
		return statusField;
	}

}
