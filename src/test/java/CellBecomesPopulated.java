import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CellBecomesPopulated {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        TwoDimensionalSpace twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        twoDimensionalSpace.registerCell(new Cell(new Point(0, 0), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(0, 1), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(1, 0), twoDimensionalSpace));

        twoDimensionalSpace.tick();

        assertTrue(twoDimensionalSpace.contains(new Cell(new Point(1, 1), twoDimensionalSpace)));
    }

    @Test
    public void cell_with_fewer_than_two_neighbours_dies() {
        TwoDimensionalSpace twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        twoDimensionalSpace.registerCell(new Cell(new Point(0, 0), twoDimensionalSpace));

        twoDimensionalSpace.tick();

        assertFalse(twoDimensionalSpace.contains(new Cell(new Point(0, 0), twoDimensionalSpace)));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        TwoDimensionalSpace twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        twoDimensionalSpace.registerCell(new Cell(new Point(0, 0), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(1, 0), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(2, 0), twoDimensionalSpace));

        twoDimensionalSpace.tick();

        assertTrue(twoDimensionalSpace.contains(new Cell(new Point(1, 0), twoDimensionalSpace)));
        assertTrue(twoDimensionalSpace.contains(new Cell(new Point(1, 1), twoDimensionalSpace)));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        TwoDimensionalSpace twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        twoDimensionalSpace.registerCell(new Cell(new Point(1, 0), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(0, 1), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(1, 1), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(2, 1), twoDimensionalSpace));
        twoDimensionalSpace.registerCell(new Cell(new Point(1, 2), twoDimensionalSpace));

        twoDimensionalSpace.tick();

        assertFalse(twoDimensionalSpace.contains(new Cell(new Point(1, 1), twoDimensionalSpace)));
    }
}
