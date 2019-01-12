import java.awt.*;
import java.util.ArrayList;

class TwoDimensionalSpace {
    final LivingCells livingCells;
    public ArrayList<Cell> deadCellsForRemoval;
    public ArrayList<Cell> cellsToBeBorn;
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
        livingCells.removeDeadCells(this);
        livingCells.birthNewCells(this);
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

    public void registerCell(Cell cell) {
        livingCells.add(cell);
    }

    public boolean contains(Cell cell) {
        return livingCells.contains(cell);
    }

    public boolean cellExistsAt(Point point) {
        return livingCells.contains(new Cell(point, this, new Neighbourhood(point, this)));
    }
}
