import java.awt.*;
import java.util.ArrayList;

class Cells implements CellObserver {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private NeighbourhoodFactory neighbourhoodFactory;
    private ArrayList<Cell> aliveCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private ArrayList<Cell> cellsToBeBorn;

    Cells(NeighbourhoodFactory neighbourhoodFactory) {
        aliveCells = new ArrayList<>();
        deadCellsForRemoval = new ArrayList<>();
        cellsToBeBorn = new ArrayList<>();
        this.neighbourhoodFactory = neighbourhoodFactory;
    }

    void tickFor(Point currentPoint) {
        for (Cell cell : aliveCells) {
            cell.tick(currentPoint);
        }
        birthNewCell(currentPoint);
    }

    boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    void add(Cell cell) {
        aliveCells.add(cell);
    }

    private void remove(Cell cell) {
        aliveCells.remove(cell);
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
        cellsToBeBorn.add(new Cell(point, this, neighbourhoodFactory.createFor(point)));
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

    @Override
    public void notifyOfDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }
}
