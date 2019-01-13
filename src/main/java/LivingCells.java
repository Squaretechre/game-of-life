import java.awt.*;
import java.util.ArrayList;

class LivingCells implements Tickable, TickEndObserver {
    private static final int NUMBER_OF_LIVING_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private ArrayList<Cell> aliveCells;
    private ArrayList<Cell> cellsToBeBorn;
    private ArrayList<Cell> deadCellsForRemoval;

    LivingCells() {
        aliveCells = new ArrayList<>();
        cellsToBeBorn = new ArrayList<>();
        deadCellsForRemoval = new ArrayList<>();
    }

    @Override
    public void tickFor(Point currentPoint) {
        updateLivingCells(currentPoint);
        checkIfNewCellShouldBeBornAt(currentPoint);
    }

    private void updateLivingCells(Point currentPoint) {
        for (Cell cell : aliveCells) {
            cell.tick(currentPoint);
        }
    }

    private void checkIfNewCellShouldBeBornAt(Point currentPoint) {
        if (noCellExistsAt(currentPoint) && totalLivingCellsNeighbouring(currentPoint) == NUMBER_OF_LIVING_NEIGHBOURS_FOR_NEW_CELL_BIRTH) {
            cellsToBeBorn.add(createCellWith(currentPoint));
        }
    }

    private boolean noCellExistsAt(Point currentPoint) {
        return !hasCellAt(currentPoint);
    }

    void notifyOfDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }

    @Override
    public void finishedTicking() {
        removeDeadCells();
        birthNewCells();
    }

    void addAt(Point point) {
        aliveCells.add(createCellWith(point));
    }

    private void removeDeadCells() {
        for (var cell : deadCellsForRemoval) {
            aliveCells.remove(cell);
        }
        deadCellsForRemoval.clear();
    }

    private void birthNewCells() {
        aliveCells.addAll(cellsToBeBorn);
        cellsToBeBorn.clear();
    }

    private int totalLivingCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(cell -> cell.isNeighbourOf(point)).count();
    }

    private Cell createCellWith(Point point) {
        return new Cell(this, point, new Neighbourhood(point, this));
    }

    boolean hasCellAt(Point point) {
        return aliveCells.stream().anyMatch(cell -> cell.cellIsLocatedAt(point));
    }
}
