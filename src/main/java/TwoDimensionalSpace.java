import java.awt.*;
import java.util.ArrayList;

class TwoDimensionalSpace {
    private static final int NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH = 3;
    private final ArrayList<Cell> aliveCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this.aliveCells = new ArrayList<>();
        this.deadCellsForRemoval = new ArrayList<>();
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells();
        removeDeadCells();
    }

    private void calculateNextGenerationOfCells() {
        for (int y = 0; y < yAxis; y++) {
            for (int x = 0; x < xAxis; x++) {
                Point currentPoint = new Point(x, y);

                if (cellExistsAt(currentPoint)) {
                    cellAt(currentPoint).tick();
                    continue;
                }

                if (totalCellsNeighbouring(currentPoint) == NUMBER_OF_NEIGHBOURS_FOR_NEW_CELL_BIRTH) {
                    birthNewCellAt(currentPoint);
                }
            }
        }
    }

    void registerCell(Cell cell) {
        aliveCells.add(cell);
    }

    void registerForRemoval(Cell cell) {
        deadCellsForRemoval.add(cell);
    }

    boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    boolean cellExistsAt(Point point) {
        return aliveCells.contains(new Cell(point, this, new Neighbourhood(point, this)));
    }

    private void removeDeadCells() {
        for (Cell cell : deadCellsForRemoval) {
            aliveCells.remove(cell);
        }
    }

    private void birthNewCellAt(Point point) {
        aliveCells.add(new Cell(point, this, new Neighbourhood(point, this)));
    }

    private int totalCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(c -> c.neighbours(point)).count();
    }

    private Cell cellAt(Point point) {
        return aliveCells.stream().filter(c -> c.equals(new Cell(point, this, new Neighbourhood(point, this)))).findFirst().get();
    }
}
