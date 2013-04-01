package minidraw.customized.tools;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import minidraw.customized.helpers.Coordinates;
import minidraw.customized.helpers.Grid;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.SelectionTool;
import minidraw.standard.StdViewWithBackground;
import minidraw.standard.handlers.DragTracker;

public class SnapToGridTool extends SelectionTool {

	// catch exception

	public SnapToGridTool(DrawingEditor editor) {
		super(editor);
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		editor().drawing().unlock();

		fChild.mouseUp(e, x, y);
		fChild = cachedNullTool;
		draggedFigure = null;

		DrawingView dV = editor.view();
		StdViewWithBackground stdV = (StdViewWithBackground) dV;

		for (Figure f : editor().drawing().selection()) {

			Coordinates center = getCenterCoordinates(f.displayBox().getX(), f
					.displayBox().getY(), f.displayBox().getWidth(), f
					.displayBox().getHeight());

			Coordinates c = getClosestGridCoordinates((int) center.getX(),
					(int) center.getY());

			int xNew = (int) (c.getX() - f.displayBox().getX());
			int yNew = (int) (c.getY() - f.displayBox().getY());

			f.moveBy(xNew, yNew);
			System.out.println("Moved by" + xNew + " " + yNew);
		}
	}

	private Coordinates getCenterCoordinates(double x, double y, double width,
			double height) {
		
		System.out.println("x is" + x);
		System.out.println("y is" + y);
		System.out.println("Width is" + width);
		System.out.println("Height is" + height);

		return new Coordinates( x + (width / 2.0), y + (height / 2.0));
	}

	/**
	 * /** Factory method to create a Drag tracker. It is used to drag a figure.
	 */
	protected Tool createDragTracker(Figure f) {
		return new DragTracker(editor(), f);

	}

	public void mouseMove(MouseEvent e, int x, int y) {

		fChild.mouseMove(e, x, y);

	}

	private Coordinates getClosestGridCoordinates(int x, int y) {

		String name = ((JPanel) editor().view()).getComponentAt(x, y).getName();
		return gridToCoordinatesMap.get(Grid.valueOf(name));
	}

	private HashMap<Grid, Coordinates> gridToCoordinatesMap = new HashMap<Grid, Coordinates>() {
		{
			// don't hardCode coordinates
			put(Grid.NORTH, new Coordinates(225, 0));
			put(Grid.SOUTH, new Coordinates(225, 450));
			put(Grid.WEST, new Coordinates(0, 225));
			put(Grid.EAST, new Coordinates(450, 225));
			put(Grid.NORTHWEST, new Coordinates(0, 0));
			put(Grid.NORTHEAST, new Coordinates(450, 0));
			put(Grid.CENTER, new Coordinates(225, 225));
			put(Grid.SOUTHWEST, new Coordinates(0, 450));
			put(Grid.SOUTHEAST, new Coordinates(450, 450));

		}
	};

}
