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
                cells.tickFor(currentPoint(x, y));
            }
        }
    }

    private Point currentPoint(int x, int y) {
        return new Point(x, y);
    }

    private void printSpace() {
        String grid = "";
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                if (cellExistsAt(currentPoint(x, y))) {
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
        cells.add(cell);
    }

    boolean contains(Cell cell) {
        return cells.contains(cell);
    }

    boolean cellExistsAt(Point point) {
        return cells.contains(new Cell(point, this, new Neighbourhood(point, this)));
    }
}
