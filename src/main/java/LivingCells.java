import java.awt.*;
import java.util.ArrayList;

class LivingCells {
    private ArrayList<Cell> aliveCells;

    LivingCells() {
        aliveCells = new ArrayList<>();
    }

    void add(Cell cell) {
       this.aliveCells.add(cell);
    }

    boolean contains(Cell cell) {
        return aliveCells.contains(cell);
    }

    void remove(Cell cell) {
        aliveCells.remove(cell);
    }

    int totalCellsNeighbouring(Point point) {
        return (int) aliveCells.stream().filter(c -> c.neighboursCellAt(point)).count();
    }

    Cell findCell(Cell cell) {
        return aliveCells.stream().filter(c -> c.equals(cell)).findFirst().get();
    }
}
