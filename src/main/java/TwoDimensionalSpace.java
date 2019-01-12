import java.awt.*;
import java.util.ArrayList;

class TwoDimensionalSpace {
    private final LivingCells livingCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this.livingCells = new LivingCells(this);
        this.deadCellsForRemoval = new ArrayList<>();
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells();
        removeDeadCells();
    }

    private void calculateNextGenerationOfCells() {
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                var currentPoint = new Point(x, y);

                livingCells.tickFor(currentPoint);
            }
        }
    }

    void registerCell(Cell cell) {
        livingCells.add(cell);
    }

    void registerDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }

    boolean contains(Cell cell) {
        return livingCells.contains(cell);
    }

    boolean cellExistsAt(Point point) {
        return livingCells.contains(new Cell(point, this, new Neighbourhood(point, this)));
    }

    private void removeDeadCells() {
        for (var cell : deadCellsForRemoval) {
            livingCells.remove(cell);
        }
    }
}
