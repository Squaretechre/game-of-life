import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class Cell {
    private final Point point;
    private final Neighbourhood neighbourhood;
    private ArrayList<Point> neighbours;
    private TwoDimensionalSpace twoDimensionalSpace;

    Cell(Point point, TwoDimensionalSpace twoDimensionalSpace, Neighbourhood neighbourhood) {
        this.point = point;
        this.twoDimensionalSpace = twoDimensionalSpace;
        this.neighbours = neighbourhood.build();
        this.neighbourhood = neighbourhood;
    }

    void tick() {
        if (shouldDie()) {
            twoDimensionalSpace.registerDeath(this);
        }
    }

    private boolean shouldDie() {
        return dieFromUnderpopulation() || dieFromOvercrowding();
    }

    private boolean dieFromUnderpopulation() {
        return neighbourhood.totalNeighbours() < 2;
    }

    private boolean dieFromOvercrowding() {
        return neighbourhood.totalNeighbours() > 3;
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
