import java.awt.*;

class neighbourhoodFactory {
    private final TwoDimensionalSpace twoDimensionalSpace;

    neighbourhoodFactory(TwoDimensionalSpace twoDimensionalSpace) {
        this.twoDimensionalSpace = twoDimensionalSpace;
    }

    Neighbourhood createNeighbourhood(Point point) {
        return new Neighbourhood(point, twoDimensionalSpace);
    }
}