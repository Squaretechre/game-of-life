import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class Cell {
    private final Point point;
    private final ArrayList<Point> neighbours;
    private TwoDimensionalSpace twoDimensionalSpace;

    Cell(Point point) {
        this.point = point;
        this.neighbours = new ArrayList<>();

        Point top = new Point(point.x, point.y - 1);
        Point topLeft = new Point(point.x - 1, point.y - 1);
        Point topRight = new Point(point.x + 1, point.y - 1);
        Point left = new Point(point.x - 1, point.y);
        Point right = new Point(point.x + 1, point.y);
        Point bottom = new Point(point.x, point.y + 1);
        Point bottomLeft = new Point(point.x - 1, point.y + 1);
        Point bottomRight = new Point(point.x + 1, point.y + 1);

        neighbours.add(top);
        neighbours.add(topLeft);
        neighbours.add(topRight);
        neighbours.add(left);
        neighbours.add(right);
        neighbours.add(bottom);
        neighbours.add(bottomLeft);
        neighbours.add(bottomRight);
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
        System.out.println(point);
        for (Point neighbour : neighbours) {
            if (neighbour.equals(point)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(point, cell.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
