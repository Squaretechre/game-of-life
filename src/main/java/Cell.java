import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class Cell {
    private final Point point;
    private ArrayList<Point> neighbours;
    private TwoDimensionalSpace twoDimensionalSpace;

    Cell(Point point, TwoDimensionalSpace twoDimensionalSpace) {
        this.point = point;
        this.twoDimensionalSpace = twoDimensionalSpace;
        this.neighbours = new Neighbourhood(point, twoDimensionalSpace).build();
    }

    void registerSpace(TwoDimensionalSpace twoDimensionalSpace) {
        this.twoDimensionalSpace = twoDimensionalSpace;
    }

    void tick() {
        if (shouldDie()) {
            twoDimensionalSpace.registerForRemoval(this);
        }
    }

    private boolean shouldDie() {
        return dieFromUnderpopulation() || dieFromOvercrowding();
    }

    private boolean dieFromUnderpopulation() {
        return totalNeighbours() < 2;
    }

    private boolean dieFromOvercrowding() {
        return totalNeighbours() > 3;
    }

    private int totalNeighbours() {
        return (int) neighbours.stream().filter(n -> twoDimensionalSpace.cellExistsAt(n)).count();
    }

    boolean neighbours(Point point) {
        for (var neighbour : neighbours) {
            if (neighbour.equals(point)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var cell = (Cell) o;
        return Objects.equals(point, cell.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
