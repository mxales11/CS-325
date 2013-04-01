package minidraw.customized.tools;

import java.awt.Component;
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

	private int upperLeftCornerX;
	private int upperLeftCornerY;
	private int turnNumber = 0;

	public SnapToGridTool(DrawingEditor editor) {
		super(editor);

	}

	public void mouseDown(MouseEvent e, int x, int y) {
		System.out.println("Mouse down was invoked");
		Drawing model = editor().drawing();

		model.lock();

		draggedFigure = model.findFigure(e.getX(), e.getY());

		if (draggedFigure != null) {
			fChild = createDragTracker(draggedFigure);
		} else {
			if (!e.isShiftDown()) {
				model.clearSelection();
			}
			fChild = createAreaTracker();
		}
		fChild.mouseDown(e, x, y);

		System.out.println("Dragged figure x "
				+ draggedFigure.displayBox().getX());
		System.out.println("Dragged figure y  "
				+ draggedFigure.displayBox().getY());
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		editor().drawing().unlock();

		fChild.mouseUp(e, x, y);
		fChild = cachedNullTool;
		draggedFigure = null;

		DrawingView dV = editor.view();
		StdViewWithBackground stdV = (StdViewWithBackground) dV;

		for (Figure f : editor().drawing().selection()) {

			// change x,y to center
			Coordinates c = getClosestGridCoordinates((int) f.displayBox().getX(),
					(int) f.displayBox().getY());

			int xNew = (int) (c.getX() - f.displayBox().getX());
			int yNew = (int) (c.getY() - f.displayBox().getY());

			f.moveBy(xNew, yNew);
			System.out.println("Moved by" + xNew + " " + yNew);
		}
	}

	private int getAppropriateGrid() {

		turnNumber++;
		System.out.println("Turn number is " + turnNumber);
		return turnNumber;

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
