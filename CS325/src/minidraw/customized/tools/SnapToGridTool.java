package minidraw.customized.tools;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JPanel;
import minidraw.customized.helpers.Coordinates;
import minidraw.customized.helpers.Grid;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.SelectionTool;

public class SnapToGridTool extends SelectionTool {

	public SnapToGridTool(DrawingEditor editor) {
		super(editor);	
	}

	public void mouseUp(MouseEvent e, int x, int y) {
		
		snapFigureToGrid();
		super.mouseUp(e, x, y);
	}


	
	private Coordinates getFigureCenter(double x, double y, double width,
			double height) {

		final double HALF = 2.0;
		return new Coordinates(x + (width / HALF), y + (height / HALF));
	}


	private Coordinates getCoordinatesOfClosestGrid(int x, int y)
			throws NullPointerException {

		String name = ((JPanel) editor().view()).getComponentAt(x, y).getName();
		
		return gridToCoordinatesMap.get(Grid.valueOf(name));
	}

	private HashMap<Grid, Coordinates> gridToCoordinatesMap = new HashMap<Grid, Coordinates>() {
		
		private final Dimension VIEW_SIZE = ((JPanel) editor().view()).getPreferredSize();
		private final int WIDTH = (int) VIEW_SIZE.getWidth();
		private final int HEIGHT = (int) VIEW_SIZE.getHeight();
		private final double ROWS = 3.0;
		private final double COLUMNS = 3.0;
		private final int DOUBLE = 2;
		
		private final int xOfSecondGrid =((int) ((WIDTH/ROWS)-1));
		private final int yOfSecondGrid =((int) ((HEIGHT/COLUMNS)-1));
		private final int xOfThirdGrid = DOUBLE * xOfSecondGrid ;
		private final int yOfThirdGrid = DOUBLE * yOfSecondGrid;
		
		
		{
			put(Grid.NORTH, new Coordinates(xOfSecondGrid, 0));
			put(Grid.SOUTH, new Coordinates(xOfSecondGrid, yOfThirdGrid));
			put(Grid.WEST, new Coordinates(0, yOfSecondGrid));
			put(Grid.EAST, new Coordinates(xOfThirdGrid, yOfSecondGrid));
			put(Grid.NORTHWEST, new Coordinates(0, 0));
			put(Grid.NORTHEAST, new Coordinates(xOfThirdGrid, 0));
			put(Grid.CENTER, new Coordinates(xOfSecondGrid, yOfSecondGrid));
			put(Grid.SOUTHWEST, new Coordinates(0, yOfThirdGrid));
			put(Grid.SOUTHEAST, new Coordinates(xOfThirdGrid, yOfThirdGrid));
		}
	};

	private void snapFigureToGrid(){
		
		for (Figure f : editor().drawing().selection()) {

			Coordinates figureCenter = getFigureCenter(f.displayBox().getX(), f
					.displayBox().getY(), f.displayBox().getWidth(), f
					.displayBox().getHeight());

			Coordinates gridCoordinates;
			
			try {
				gridCoordinates = getCoordinatesOfClosestGrid(
						(int) figureCenter.getX(), (int) figureCenter.getY());
			}

			catch (NullPointerException ex) {
				System.out.println("You cannot put puzzle out of picture. Try again.");
				return;
			}
			int xToMove = (int) (gridCoordinates.getX() - f.displayBox().getX());
			int yToMove = (int) (gridCoordinates.getY() - f.displayBox().getY());

			f.moveBy(xToMove, yToMove);

		}
		
	}
}
