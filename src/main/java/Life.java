class Life {
    private final String world;

    Life(String world) {
        this.world = world;
    }

    String go() {
        String nextWorld = "";
        String[] rows = world.split("\n");
        int rowCount = rows.length;
        int columnCount = rows[0].length();
        char[][] grid = new char[rowCount][columnCount];
        char[][] newGrid = new char[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                String currentRow = rows[row];
                char currentCell = currentRow.charAt(column);
                grid[row][column] = currentCell;
            }
        }

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (column > 0 && column < columnCount - 1) {
                    char leftCell = grid[row][column - 1];
                    char rightCell = grid[row][column + 1];
                    if (leftCell == '.' && rightCell == '.') {
                        newGrid[row][column] = '.';
                        continue;
                    }
                }
                newGrid[row][column] = '.';
            }
        }

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                nextWorld += newGrid[row][column];
            }
            if (row < rowCount - 1) {
                nextWorld += "\n";
            }
        }
        return nextWorld;
    }
}
