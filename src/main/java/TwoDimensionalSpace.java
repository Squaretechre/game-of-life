import java.awt.*;

class TwoDimensionalSpace {
    Cells cells;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this.cells = new Cells();
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells(cells);
        cells.removeDeadCells();
        cells.birthNewCells();
    }

    private void calculateNextGenerationOfCells(Tickable tickable) {
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                tickable.tickFor(currentPoint(x, y));
            }
        }
    }

    private Point currentPoint(int x, int y) {
        return new Point(x, y);
    }

    void setCells(Cells cells) {
        this.cells = cells;
    }
}
