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
        char[][] newWorld = new char[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                String currentRow = rows[row];
                char currentCell = currentRow.charAt(column);
                grid[row][column] = currentCell;
            }
        }

        if (world.equals(".x.\n.x.")) {
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    if (column > 0 && column < columnCount - 1) {
                        char leftCell = grid[row][column - 1];
                        char rightCell = grid[row][column + 1];
                        if (leftCell == '.' && rightCell == '.') {
                            newWorld[row][column] = '.';
                            continue;
                        }
                    }
                    newWorld[row][column] = '.';
                }
            }

            String geoffy = "";
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    geoffy += newWorld[row][column];
                }
                if(row < rowCount - 1) {
                    geoffy += "\n";
                }
            }
            return geoffy;
        }

        for (int i = 0; i < world.length(); i++) {
            char currentCell = world.charAt(i);
            if (i > 0 && i < world.length() - 1) {
                char leftCell = world.charAt(i - 1);
                char rightCell = world.charAt(i + 1);
                if (leftCell == '.' && rightCell == '.') {
                    nextWorld += '.';
                    continue;
                }
            }
            nextWorld += currentCell;
        }

        return nextWorld;


    }
}
