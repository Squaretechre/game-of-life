import java.awt.*;

class neighbourhoodFactory {
    private final TwoDimensionalSpace twoDimensionalSpace;

    neighbourhoodFactory(TwoDimensionalSpace twoDimensionalSpace) {
        this.twoDimensionalSpace = twoDimensionalSpace;
    }

    Neighbourhood createFor(Point point) {
        return new Neighbourhood(point, twoDimensionalSpace);
    }
}