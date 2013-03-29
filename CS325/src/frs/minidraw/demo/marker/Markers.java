package frs.minidraw.demo.marker;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Demonstrate MiniDraw Observer ability.
 * Demonstrates:
 * A) Register to listen to FigureChangeEvents
 * B) Make a figure react upon events
 * C) Use of Decorator to implement this behavior
 *
 * Move the AU seal using the mouse, and uses the
 * FigureChange Observer pattern mechanisms of MiniDraw
 * to make the two arrows always point to the logo.

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
public class Markers {
  public static void main(String[] args) {
    Factory f = new EmptyCanvasFactory();
    DrawingEditor editor = 
      new MiniDrawApplication( "Markers - move the white seal", f );

    editor.open();
    Figure logo = new ImageFigure( "au-seal-small", new Point(200, 200));

    Figure rightArrow = 
      new MarkerFigureDecorator( new ImageFigure( "arrow-right", 
                                                  new Point(0, 200)),
                                 logo,
                                 false );
    Figure downArrow = 
      new MarkerFigureDecorator( new ImageFigure( "arrow-down", 
                                                  new Point(200, 0)),
                                 logo,
                                 true );

    editor.setTool( new SelectionTool(editor) );

    editor.drawing().add(rightArrow);
    editor.drawing().add(downArrow);
    editor.drawing().add(logo);
  }
}

class EmptyCanvasFactory implements Factory {

  public DrawingView createDrawingView( DrawingEditor editor ) {
    return new StandardDrawingView(editor);
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}
