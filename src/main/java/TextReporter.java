import java.awt.*;

class TextReporter {
    private final Cells cells;
    private final int xAxis;
    private final int yAxis;
    private final String[][] grid;

    TextReporter(Cells cells, int xAxis, int yAxis) {
        this.cells = cells;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.grid = new String[yAxis][xAxis];
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
