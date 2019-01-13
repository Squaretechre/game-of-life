import java.awt.*;

class TextReporter {
    private final LivingCells cells;
    private final int xAxis;
    private final int yAxis;

    TextReporter(LivingCells cells, int xAxis, int yAxis) {
        this.cells = cells;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    String output() {
        String gridText = "";
        for (var y = 0; y < yAxis; y++) {
            for (var x = 0; x < xAxis; x++) {
                if (cells.hasCellAt(new Point(x, y))) {
                    gridText += "x";
                } else {
                    gridText += ".";
                }
            }
            if (y < yAxis - 1) {
                gridText += "\n";
            }
        }
        return gridText;
    }
}
