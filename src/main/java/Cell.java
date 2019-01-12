import java.awt.*;
import java.util.Objects;

class Cell {
    private final Point pointInSpace;
    private final Neighbourhood neighbourhood;
    private final Cells cells;

    Cell(Point point, Neighbourhood neighbourhood) {
        this(point, null, neighbourhood);
    }

    Cell(Point point, Cells cells, Neighbourhood neighbourhood) {
        this.pointInSpace = point;
        this.cells = cells;
        this.neighbourhood = neighbourhood;
    }

    void tick(Point currentTickPoint) {
        if (thisCellIsLocatedAt(currentTickPoint) && shouldDie()) {
            cells.registerDeath(this);
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
}
