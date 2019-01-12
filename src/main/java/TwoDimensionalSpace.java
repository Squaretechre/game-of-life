import java.awt.*;

class TwoDimensionalSpace {
    private final Tickable tickable;
    Cells cells;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(Tickable tickable, int x, int y) {
        this.tickable = tickable;
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        calculateNextGenerationOfCells();
        tickable.finishedTicking();
    }

    private void calculateNextGenerationOfCells() {
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
