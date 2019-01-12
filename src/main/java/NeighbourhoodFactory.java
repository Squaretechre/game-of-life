import java.awt.*;

class NeighbourhoodFactory {
    private final TwoDimensionalSpace twoDimensionalSpace;

    NeighbourhoodFactory(TwoDimensionalSpace twoDimensionalSpace) {
        this.twoDimensionalSpace = twoDimensionalSpace;
    }

    Neighbourhood createFor(Point point) {
        return new Neighbourhood(point, twoDimensionalSpace);
    }
}