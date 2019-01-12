import java.awt.*;
import java.util.ArrayList;

class TwoDimensionalSpace {
    private final LivingCells livingCells;
    private ArrayList<Cell> deadCellsForRemoval;
    private ArrayList<Cell> cellsToBeBorn;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this.livingCells = new LivingCells(this);
        this.deadCellsForRemoval = new ArrayList<>();
        this.cellsToBeBorn = new ArrayList<>();
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells();
        removeDeadCells();
        birthNewCells();
        printSpace();
    }

    private void calculateNextGenerationOfCells() {
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                var currentPoint = new Point(x, y);
                livingCells.tickFor(currentPoint);
            }
        }
    }

    private void printSpace() {
        String grid = "";
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                var currentPoint = new Point(x, y);
                if (livingCells.hasCellAt(currentPoint)) {
                    grid += "x";
                } else {
                    grid += ".";
                }
            }
            grid += "\n";
        }

        System.out.println(grid);
    }

    void registerCell(Cell cell) {
        livingCells.add(cell);
    }

    void registerDeath(Cell cell) {
        deadCellsForRemoval.add(cell);
    }

    void registerBirth(Cell cell) {
        cellsToBeBorn.add(cell);
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

    private void birthNewCells() {
        for (var cell : cellsToBeBorn) {
            livingCells.add(cell);
        }
    }
}
