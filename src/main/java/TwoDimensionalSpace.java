import java.awt.*;

class TwoDimensionalSpace {
    private final Tickable tickable;
    Cells cells;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(int x, int y) {
        this(new Cells(), x, y);
    }

    TwoDimensionalSpace(Tickable tickable, int x, int y) {
        this.tickable = tickable;
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
