package life;

import java.awt.*;
import java.util.ArrayList;

public class Neighbourhood {
    private final Point point;

    public Neighbourhood(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public ArrayList<Point> build() {
        var neighbours = new ArrayList<Point>();
        var top = new Point(getPoint().x, getPoint().y - 1);
        var topLeft = new Point(getPoint().x - 1, getPoint().y - 1);
        var topRight = new Point(getPoint().x + 1, getPoint().y - 1);
        var left = new Point(getPoint().x - 1, getPoint().y);
        var right = new Point(getPoint().x + 1, getPoint().y);
        var bottom = new Point(getPoint().x, getPoint().y + 1);
        var bottomLeft = new Point(getPoint().x - 1, getPoint().y + 1);
        var bottomRight = new Point(getPoint().x + 1, getPoint().y + 1);

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
}
