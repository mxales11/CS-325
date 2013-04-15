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
import frs.hotgammon.view.drawings.BackGammonDrawing;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.BackgammonTool;
import frs.hotgammon.view.tools.CheckerMoveTool;
import frs.hotgammon.view.tools.DiceRollTool;
import frs.hotgammon.variants.*;
import frs.hotgammon.variants.rules.AlphaMon;
import frs.hotgammon.variants.rules.BetaMon;
import frs.hotgammon.variants.rules.GammaMon;

/** Show the dice and some checkers on the
 * backgammon board.  
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowCheckersAndDice {
	
	
  public static void main(String[] args) {
	
	  Game game = new StubGame1();
	  
	  //Game game = new GameImpl(new AlphaMon());
	  game.newGame();
	  
	  //normalnie to to jest invoked jak dice are clicked, skad sie biora pierwsze kosci?
	  game.nextTurn();
	 
	  
    DrawingEditor editor = 
      new MiniDrawApplication( "Show HotGammon figures...",  
                               new HotGammonFactory() );
    editor.open();

    
    //the beggining display should be read from model not just displayed like this
    
    Figure redDie = new DieFigure("die4", new Point(216, 202));
    Figure blackDie = new DieFigure("die2", new Point(306, 202));
    editor.drawing().add(redDie);
    editor.drawing().add(blackDie);
    
    Figure bc = new CheckerFigure(Color.BLACK, new Point(21,21));
    Figure bcnext = new CheckerFigure(Color.BLACK, new Point(32,372));
    editor.drawing().add(bc);
    editor.drawing().add(bcnext);
    
    Figure rc = new CheckerFigure(Color.RED, new Point(507,390));
    Figure rcnext = new CheckerFigure(Color.RED, new Point(500,390));
    editor.drawing().add(rc);
    editor.drawing().add(rcnext);

    //editor.setTool( new SelectionTool(editor) );
    editor.setTool(new BackgammonTool(editor, game));
    

    game.addObserver((GameObserver)(editor.drawing()));
    
    

  }
}

class HotGammonFactory implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "board");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    //return new StandardDrawing();
	  return new BackGammonDrawing();
	  
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello HotGammon..." );
    statusField.setEditable(false);
    return statusField;
  }
}


