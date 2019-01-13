import java.awt.*;
import java.util.ArrayList;

class Cell {
    private final Point pointInSpace;
    private final Neighbourhood neighbourhood;
    private ArrayList<CellObserver> observers;

    Cell(Point point, Neighbourhood neighbourhood) {
        this.pointInSpace = point;
        this.neighbourhood = neighbourhood;
        this.observers = new ArrayList<>();
    }

    void tick(Point currentTickPoint) {
        if (this.cellIsLocatedAt(currentTickPoint) && shouldDie()) {
            notifyObserversOfDeath();
        }
    }

    void registerObserver(CellObserver observer) {
        observers.add(observer);
    }

    boolean isNeighbourOf(Point point) {
        return neighbourhood.isNeighbourOf(point);
    }

    boolean cellIsLocatedAt(Point currentPoint) {
        return pointInSpace.equals(currentPoint);
    }

    private void notifyObserversOfDeath() {
        for (CellObserver observer : observers) {
           observer.notifyOfDeath(this);
        }
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
