package frs.hotgammon.tests.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.*;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.tests.stub.StubGame1;
import frs.hotgammon.view.drawings.BackGammonDrawing;
import frs.hotgammon.view.figures.CheckerFigure;
import frs.hotgammon.view.figures.DieFigure;
import frs.hotgammon.view.tools.BackgammonTool;
import frs.hotgammon.view.tools.CheckerMoveTool;
import frs.hotgammon.view.tools.DiceRollTool;
import frs.hotgammon.variants.*;
import frs.hotgammon.variants.rules.AlphaMon;

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
	  
	 //Game game = new GameImpl(new AlphaMon());
	  Game game = new StubGame1();


    DrawingEditor editor = 
      new MiniDrawApplication( "Show HotGammon figures...",  
                               new HotGammonFactory() );
    editor.open();

    
    //the beggining display should be read from model not just displayed like this
    
    Figure redDie = new DieFigure("die4", new Point(216, 202));
    Figure blackDie = new DieFigure("die2", new Point(306, 202));
    editor.drawing().add(redDie);
    editor.drawing().add(blackDie);
    
    Figure bc = new CheckerFigure("blackchecker", new Point(21,21));
    editor.drawing().add(bc);
    Figure rc = new CheckerFigure("redchecker", new Point(507,390));
    editor.drawing().add(rc);

    //editor.setTool( new SelectionTool(editor) );
    editor.setTool(new BackgammonTool(editor, game));
    
    //is it where the new game starts?
    
    System.out.println(game.getGameState());
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


