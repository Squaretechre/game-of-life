import java.awt.*;

class TwoDimensionalSpace {
    final Cells cells;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this.cells = new Cells(this);
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells();
        cells.removeDeadCells();
        cells.birthNewCells();
        printSpace();
    }

    private void calculateNextGenerationOfCells() {
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                var currentPoint = new Point(x, y);
                cells.tickFor(currentPoint);
            }
        }
    }

    private void printSpace() {
        String grid = "";
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                var currentPoint = new Point(x, y);
                if (cells.hasCellAt(currentPoint)) {
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
        cells.add(cell);
    }

    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }

    public boolean cellExistsAt(Point point) {
        return cells.contains(new Cell(point, this, new Neighbourhood(point, this)));
    }
}
