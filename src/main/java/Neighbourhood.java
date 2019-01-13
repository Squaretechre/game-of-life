import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

class Neighbourhood {
    private final Point point;
    private final LivingCells livingCells;
    private final ArrayList<Point> neighbouringPoints;

    Neighbourhood(Point point, LivingCells cells) {
        this.point = point;
        this.livingCells = cells;
        this.neighbouringPoints = buildNeighbouringPoints();
    }

    int totalLivingCellsInNeighbourhood() {
        return (int) allNeighbouringPoints()
                .filter(point -> livingCells.hasCellAt(point))
                .count();
    }

    boolean isNeighbourOf(Point point) {
        return allNeighbouringPoints()
                .anyMatch(neighbour -> neighbour.equals(point));
    }

    private ArrayList<Point> buildNeighbouringPoints() {
        var top = new Point(point.x, point.y - 1);
        var topLeft = new Point(point.x - 1, point.y - 1);
        var topRight = new Point(point.x + 1, point.y - 1);
        var left = new Point(point.x - 1, point.y);
        var right = new Point(point.x + 1, point.y);
        var bottom = new Point(point.x, point.y + 1);
        var bottomLeft = new Point(point.x - 1, point.y + 1);
        var bottomRight = new Point(point.x + 1, point.y + 1);

        return new ArrayList<>() {{
            add(top);
            add(topLeft);
            add(topRight);
            add(left);
            add(right);
            add(bottom);
            add(bottomLeft);
            add(bottomRight);
        }};
    }

    private Stream<Point> allNeighbouringPoints() {
        return neighbouringPoints.stream();
    }
}
