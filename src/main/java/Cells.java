import java.awt.*;
import java.util.ArrayList;

class Cells implements CellObserver, Tickable {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private ArrayList<Cell> aliveCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private ArrayList<Cell> cellsToBeBorn;

    Cells() {
        aliveCells = new ArrayList<>();
        deadCellsForRemoval = new ArrayList<>();
        cellsToBeBorn = new ArrayList<>();
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

    @Override
    public void notifyOfDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }

    @Override
    public void finishedTicking() {
        removeDeadCells();
        birthNewCells();
    }

    void addAt(Point point) {
        aliveCells.add(createCellAt(point));
    }

    private void removeDeadCells() {
        for (var cell : deadCellsForRemoval) {
            remove(cell);
        }
    }

    private void birthNewCells() {
        for (var cell : cellsToBeBorn) {
            add(cell);
        }
    }

    private void add(Cell cell) {
        cell.registerObserver(this);
        aliveCells.add(cell);
    }

    private void remove(Cell cell) {
        aliveCells.remove(cell);
    }

    private void checkIfNewCellShouldBeBornAt(Point currentPoint) {
        if (totalCellsNeighbouring(currentPoint) == NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH) {
            birthNewCellAt(currentPoint);
        }
    }

    private int totalCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(c -> c.neighboursCellAt(point)).count();
    }

    private void birthNewCellAt(Point point) {
        cellsToBeBorn.add(new Cell(point, new Neighbourhood(point, this)));
    }

    private Cell createCellAt(Point point) {
        var cell = new Cell(point, new Neighbourhood(point, this));
        cell.registerObserver(this);
        return cell;
    }

    boolean hasCellAt(Point point) {
        return aliveCells.contains(new Cell(point, new Neighbourhood(point, this)));
    }
}
