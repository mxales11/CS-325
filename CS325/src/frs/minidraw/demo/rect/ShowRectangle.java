package frs.minidraw.demo.rect;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Demonstrate MiniDraw ability to:
 * A) Handle multiple windows 
 * B) Define new Figure instances (green rectangles)
 * C) Define a new tool to create rectangles
 * D) Switching tools at run-time
 * E) Showing text in the status window
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
public class ShowRectangle {
  
  public static void main(String[] args) {
    Factory f = new EmptyCanvasFactory();
    DrawingEditor editor = 
      new MiniDrawApplication( "Create and move rectangles "+
                               "- use the mouse", f );
    Tool 
      rectangleDrawTool = new RectangleTool(editor),
      selectionTool = new SelectionTool(editor);
    addToolSelectMenusToWindow( editor, 
                                rectangleDrawTool,
                                selectionTool );
    editor.open();

    editor.setTool( rectangleDrawTool );
    editor.showStatus( "MiniDraw version: "+DrawingEditor.VERSION );

    // create second view
    JFrame newWindow = new JFrame("Extra View");
    newWindow.setLocation( 620, 20 );
    newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawingView extraView = f.createDrawingView(editor);
    JPanel panel = (JPanel) extraView;
    newWindow.getContentPane().add(panel);
    newWindow.pack();
    newWindow.setVisible(true);
  }
  
  public static void addToolSelectMenusToWindow(final DrawingEditor window,
        final Tool rectangleTool, 
        final Tool selectionTool) {
    // Here I use the knowledge that 'window' is a MiniDrawApplication
    JFrame frame = (JFrame) window;

    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    
    menuBar = new JMenuBar();
    menu = new JMenu("HotgammonTool");
    menuBar.add(menu);

    menuItem = new JMenuItem("Rectangle");
    menuItem.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        window.setTool(rectangleTool);
        window.showStatus("Rectangle tool active.");
      }
    });
    menu.add(menuItem);

    menuItem = new JMenuItem("Selection");
    menuItem.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        window.setTool(selectionTool);
        window.showStatus("Selection tool active.");
      }
    });
    menu.add(menuItem);

    frame.setJMenuBar( menuBar );
  }
}

class EmptyCanvasFactory implements Factory {

  public DrawingView createDrawingView( DrawingEditor editor ) {
    return new StandardDrawingView(editor, new Dimension(400,200));
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return new JTextField();
  }
}

/** A tool to create rectangles */
class RectangleTool extends AbstractTool {
  private Point corner;
  private RectangleFigure f;

  public RectangleTool(DrawingEditor editor) {
    super(editor);
    f = null;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e,x,y);
    f = new RectangleFigure( new Point(x,y) );
    editor.drawing().add(f);
  }
  
  public void mouseDrag(MouseEvent e, int x, int y) {
    f.resize(new Point(fAnchorX, fAnchorY),
             new Point(x,y) );
  }
  
  public void mouseUp(MouseEvent e, int x, int y) { 
    if (f.displayBox().isEmpty()) {
      editor.drawing().remove(f);
    }
    f = null;
  }
}
