import java.awt.*;

public class neighbourhoodFactory {
    private final Cells cells;

    public neighbourhoodFactory(Cells cells) {
        this.cells = cells;
    }

    Neighbourhood createNeighbourhood(Point point) {
        return new Neighbourhood(point, cells.getTwoDimensionalSpace());
    }
}