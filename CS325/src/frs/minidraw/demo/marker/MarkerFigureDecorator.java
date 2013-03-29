package frs.minidraw.demo.marker;
import java.awt.*;

import minidraw.framework.*;

/** This decorator can decorate any figure to become either a
 * horizontal or vertical marker figure - that is a figure that moves
 * to the same horizontal (vertical) position as a target figure.

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
public class MarkerFigureDecorator implements Figure {

  private Figure decoratee;
  private boolean horizontal;

  public MarkerFigureDecorator(Figure decoratee, 
                               Figure target,
                               boolean horizontal ) {
    this.decoratee = decoratee;
    this.horizontal = horizontal;
    Observer o = new Observer();
    target.addFigureChangeListener(o);
  }

  public void draw(Graphics g) { decoratee.draw(g); }
  public Rectangle displayBox() { return decoratee.displayBox(); }
  public void moveBy(int dx, int dy) { decoratee.moveBy(dx,dy); }
  public void invalidate() { decoratee.invalidate(); }
  public void changed() { decoratee.changed(); }
  public void addFigureChangeListener(FigureChangeListener l) { 
    decoratee.addFigureChangeListener(l);
  }
  public void removeFigureChangeListener(FigureChangeListener l) {
    decoratee.removeFigureChangeListener(l);
  }

  private class Observer implements FigureChangeListener {
    public void figureInvalidated(FigureChangeEvent e){
    }
    public void figureChanged(FigureChangeEvent e){
      Figure f = e.getFigure();
      Rectangle target_r = f.displayBox();
      Rectangle marker_r = decoratee.displayBox();
      int dx = 0, dy = 0;
      if ( horizontal ) {
        dx = target_r.x - marker_r.x;
      } else { 
        dy = target_r.y - marker_r.y;
      }        
      decoratee.moveBy(dx, dy);
    }
    public void figureRemoved(FigureChangeEvent e){}
    public void figureRequestRemove(FigureChangeEvent e){}
    public void figureRequestUpdate(FigureChangeEvent e){}
  }
}


