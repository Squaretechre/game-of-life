import java.awt.*;
import java.util.ArrayList;

class Neighbourhood {
    private final Point point;
    private final TwoDimensionalSpace twoDimensionalSpace;
    private final ArrayList<Point> neighbours;

    Neighbourhood(Point point, TwoDimensionalSpace twoDimensionalSpace) {
        this.point = point;
        this.twoDimensionalSpace = twoDimensionalSpace;
        this.neighbours = build();
    }

    ArrayList<Point> build() {
        var neighbours = new ArrayList<Point>();
        var top = new Point(point.x, point.y - 1);
        var topLeft = new Point(point.x - 1, point.y - 1);
        var topRight = new Point(point.x + 1, point.y - 1);
        var left = new Point(point.x - 1, point.y);
        var right = new Point(point.x + 1, point.y);
        var bottom = new Point(point.x, point.y + 1);
        var bottomLeft = new Point(point.x - 1, point.y + 1);
        var bottomRight = new Point(point.x + 1, point.y + 1);

        neighbours.add(top);
        neighbours.add(topLeft);
        neighbours.add(topRight);
        neighbours.add(left);
        neighbours.add(right);
        neighbours.add(bottom);
        neighbours.add(bottomLeft);
        neighbours.add(bottomRight);
        return neighbours;
    }

    int totalNeighbours() {
        return (int) neighbours.stream().filter(n -> twoDimensionalSpace.cellExistsAt(n)).count();
    }

    boolean hasNeighbourAt(Point point) {
        for (var neighbour : neighbours) {
            if (neighbour.equals(point)) return true;
        }
        return false;
    }
}
