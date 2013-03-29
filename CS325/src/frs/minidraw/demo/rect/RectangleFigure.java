package frs.minidraw.demo.rect;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;

/** A MiniDraw rectangle figure.

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
public class RectangleFigure extends AbstractFigure {

  private Rectangle     fDisplayBox;
  private Color         color;

  /** Create a green rectangle figure.
      @param origin the coordinate of the origin
      @param corner the coordinate of the corner */
  public RectangleFigure(Point origin, Point corner) {
    basicResize(origin,corner);
    color = Color.GREEN;
  }
  /** Create a rectangle figure of a given color.
      @param origin the coordinate of the origin
      @param corner the coordinate of the corner 
      @param color the color of the rectangle */
  public RectangleFigure(Point origin, Point corner, Color color) {
    basicResize(origin,corner);
    this.color = color;
  }

  /** Create an 'empty' rectangle figure.
      @param origin the coordinate of the origin */
  public RectangleFigure(Point origin) {
    this(origin, origin);
  }
 
  protected void basicResize(Point origin, Point corner) {
    fDisplayBox = new Rectangle(origin);
    fDisplayBox.add(corner);
  }

  public Rectangle displayBox() {
    return new Rectangle(
                         fDisplayBox.x,
                         fDisplayBox.y,
                         fDisplayBox.width,
                         fDisplayBox.height);
  }

  protected void basicMoveBy(int x, int y) {
    fDisplayBox.translate(x,y);
  }

  protected void resize(Point origin, Point corner) {
    willChange();
    basicResize(origin,corner);
    changed();
  }
  
  public void draw(Graphics g) {
    Rectangle r = displayBox();
    g.setColor(color);
    g.fillRect(r.x, r.y, r.width, r.height);
    g.setColor(Color.BLACK);
    g.drawRect(r.x, r.y, r.width-1, r.height-1);
  }
}
