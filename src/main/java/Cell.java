import java.awt.*;

class Cell {
    private final LivingCells livingCells;
    private final Point pointInSpace;
    private final Neighbourhood neighbourhood;

    Cell(LivingCells livingCells, Point point, Neighbourhood neighbourhood) {
        this.livingCells = livingCells;
        this.pointInSpace = point;
        this.neighbourhood = neighbourhood;
    }

    void tick(Point currentTickPoint) {
        if (this.cellIsLocatedAt(currentTickPoint) && shouldDie()) {
            livingCells.notifyOfDeath(this);
        }
    }

    boolean isNeighbourOf(Point point) {
        return neighbourhood.isNeighbourOf(point);
    }

    boolean cellIsLocatedAt(Point currentPoint) {
        return pointInSpace.equals(currentPoint);
    }

    private boolean shouldDie() {
        return dieFromUnderpopulation() || dieFromOvercrowding();
    }

    private boolean dieFromUnderpopulation() {
        return neighbourhood.totalLivingCellsInNeighbourhood() < 2;
    }

    private boolean dieFromOvercrowding() {
        return neighbourhood.totalLivingCellsInNeighbourhood() > 3;
    }
}
