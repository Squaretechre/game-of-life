import java.awt.*;
import java.util.ArrayList;

class LivingCells {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private ArrayList<Cell> aliveCells;

    LivingCells() {
        aliveCells = new ArrayList<>();
    }

    void add(Cell cell) {
        this.aliveCells.add(cell);
    }

    boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    void remove(Cell cell) {
        aliveCells.remove(cell);
    }

    void tickFor(Point currentPoint) {
        for (Cell cell : aliveCells) {
            cell.tick(currentPoint);
        }
    }

    void birthNewCell(Point currentPoint, TwoDimensionalSpace twoDimensionalSpace) {
        if (totalCellsNeighbouring(currentPoint) == NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH) {
            birthNewCellAt(currentPoint, twoDimensionalSpace);
        }
    }

    private int totalCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(c -> c.neighboursCellAt(point)).count();
    }

    private void birthNewCellAt(Point point, TwoDimensionalSpace twoDimensionalSpace) {
        add(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
    }
}
