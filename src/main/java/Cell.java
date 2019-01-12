import java.awt.*;
import java.util.Objects;

class Cell {
    private final Point pointInSpace;
    private final Neighbourhood neighbourhood;
    private TwoDimensionalSpace twoDimensionalSpace;

    Cell(Point point, TwoDimensionalSpace twoDimensionalSpace, Neighbourhood neighbourhood) {
        this.pointInSpace = point;
        this.twoDimensionalSpace = twoDimensionalSpace;
        this.neighbourhood = neighbourhood;
    }

    void tick(Point currentPoint) {
        if (thisCellIsLocatedAt(currentPoint) && shouldDie()) {
            twoDimensionalSpace.livingCells.registerDeath(this, twoDimensionalSpace);
        }
    }

    boolean neighboursCellAt(Point point) {
        return neighbourhood.hasNeighbourAt(point);
    }

    private boolean thisCellIsLocatedAt(Point currentPoint) {
        return pointInSpace.equals(currentPoint);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var cell = (Cell) o;
        return Objects.equals(pointInSpace, cell.pointInSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointInSpace);
    }

    boolean isAtPoint(Point currentPoint) {
        return pointInSpace.equals(currentPoint);
    }
}
