public class Life {
    private final String world;

    public Life(String world) {
        this.world = world;
    }

    public String go() {

        String nextWorld = "";

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
