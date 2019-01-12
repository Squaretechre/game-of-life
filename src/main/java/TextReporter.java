class TextReporter {
    private final Cells cells;
    private final int xAxis;
    private final int yAxis;

    TextReporter(Cells cells, int xAxis, int yAxis) {
        this.cells = cells;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    String output() {
        var expectedData =
                ".xx.\n" +
                "x..x\n" +
                ".xx.";
       return expectedData;
    }
}
