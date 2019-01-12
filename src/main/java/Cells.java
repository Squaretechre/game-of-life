import java.awt.*;
import java.util.ArrayList;

class Cells {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private final TwoDimensionalSpace twoDimensionalSpace;
    private ArrayList<Cell> aliveCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private ArrayList<Cell> cellsToBeBorn;

    Cells(TwoDimensionalSpace twoDimensionalSpace) {
        this.twoDimensionalSpace = twoDimensionalSpace;
        aliveCells = new ArrayList<>();
        deadCellsForRemoval = new ArrayList<>();
        cellsToBeBorn = new ArrayList<>();
    }

    void add(Cell cell) {
        this.aliveCells.add(cell);
    }

    boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    private void remove(Cell cell) {
        aliveCells.remove(cell);
    }

    void tickFor(Point currentPoint) {
        for (Cell cell : aliveCells) {
            cell.tick(currentPoint);
        }
        birthNewCell(currentPoint);
    }

    private void birthNewCell(Point currentPoint) {
        if (totalCellsNeighbouring(currentPoint) == NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH) {
            birthNewCellAt(currentPoint);
        }
    }

    private int totalCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(c -> c.neighboursCellAt(point)).count();
    }

    private void birthNewCellAt(Point point) {
        registerBirth(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
    }

    void removeDeadCells() {
        for (var cell : deadCellsForRemoval) {
            remove(cell);
        }
    }

    void birthNewCells() {
        for (var cell : cellsToBeBorn) {
            add(cell);
        }
    }

    private void registerBirth(Cell cell) {
        cellsToBeBorn.add(cell);
    }

    void registerDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }
}
