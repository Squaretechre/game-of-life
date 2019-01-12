import java.awt.*;
import java.util.ArrayList;

class LivingCells {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private final TwoDimensionalSpace twoDimensionalSpace;
    private ArrayList<Cell> aliveCells;
    public ArrayList<Cell> deadCellsForRemoval;
    public ArrayList<Cell> cellsToBeBorn;

    LivingCells(TwoDimensionalSpace twoDimensionalSpace) {
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

    void remove(Cell cell) {
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
        twoDimensionalSpace.livingCells.registerBirth(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)), twoDimensionalSpace);
    }

    boolean hasCellAt(Point currentPoint) {
        return aliveCells.stream().anyMatch(c -> c.isAtPoint(currentPoint));
    }

    public void removeDeadCells(TwoDimensionalSpace twoDimensionalSpace) {
        for (var cell : twoDimensionalSpace.deadCellsForRemoval) {
            remove(cell);
        }
    }

    public void birthNewCells(TwoDimensionalSpace twoDimensionalSpace) {
        for (var cell : twoDimensionalSpace.cellsToBeBorn) {
            add(cell);
        }
    }

    public void registerBirth(Cell cell, TwoDimensionalSpace twoDimensionalSpace) {
        cellsToBeBorn.add(cell);
        twoDimensionalSpace.cellsToBeBorn.add(cell);
    }

    public void registerDeath(Cell cell, TwoDimensionalSpace twoDimensionalSpace) {
        deadCellsForRemoval.add(cell);
        twoDimensionalSpace.deadCellsForRemoval.add(cell);
    }
}
