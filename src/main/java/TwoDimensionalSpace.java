import java.awt.*;

class TwoDimensionalSpace {
    private final Tickable tickable;
    private final int xAxis;
    private final int yAxis;

    TwoDimensionalSpace(Tickable tickable, int x, int y) {
        this.tickable = tickable;
        this.xAxis = x;
        this.yAxis = y;
    }

    void tick() {
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                tickable.tickFor(currentPoint(x, y));
            }
        }
        tickable.finishedTicking();
    }

    private Point currentPoint(int x, int y) {
        return new Point(x, y);
    }
}
