import org.junit.Test;
import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameOfLifeTests {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        Point point = new Point(0, 0);
        Point point1 = new Point(0, 1);
        Point point2 = new Point(1, 0);
        Point point3 = new Point(1, 1);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);
        cells.addAt(point2);
        cells.addAt(point3);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 2, 2);
        twoDimensionalSpace.tick();

        assertTrue(cells.hasCellAt(point));
        assertTrue(cells.hasCellAt(point1));
        assertTrue(cells.hasCellAt(point2));
        assertTrue(cells.hasCellAt(point3));
    }

    @Test
    public void cell_with_fewer_than_two_neighbours_dies() {
        Point point = new Point(0, 0);
        Point point1 = new Point(1, 0);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 2, 2);
        twoDimensionalSpace.tick();

        assertFalse(cells.hasCellAt(point));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        Point point = new Point(0, 0);
        Point point1 = new Point(1, 0);
        Point point2 = new Point(2, 0);
        Point point3 = new Point(1, 0);
        Point point4 = new Point(1, 1);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);
        cells.addAt(point2);
        cells.addAt(point3);
        cells.addAt(point4);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 3, 3);
        twoDimensionalSpace.tick();

        assertTrue(cells.hasCellAt(point3));
        assertTrue(cells.hasCellAt(point4));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        Point point = new Point(1, 0);
        Point point1 = new Point(0, 1);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(2, 1);
        Point point4 = new Point(1, 2);

        Point point5 = new Point(1, 1);
        Point point6 = new Point(0, 0);
        Point point7 = new Point(1, 0);
        Point point8 = new Point(2, 0);
        Point point9 = new Point(0, 1);
        Point point10 = new Point(2, 1);
        Point point11 = new Point(0, 2);
        Point point12 = new Point(1, 2);
        Point point13 = new Point(2, 2);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);
        cells.addAt(point2);
        cells.addAt(point3);
        cells.addAt(point4);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 3, 3);
        twoDimensionalSpace.tick();

        assertFalse(cells.hasCellAt(point5));

        assertTrue(cells.hasCellAt(point6));
        assertTrue(cells.hasCellAt(point7));
        assertTrue(cells.hasCellAt(point8));
        assertTrue(cells.hasCellAt(point9));
        assertTrue(cells.hasCellAt(point10));
        assertTrue(cells.hasCellAt(point11));
        assertTrue(cells.hasCellAt(point12));
        assertTrue(cells.hasCellAt(point13));
    }

    @Test
    public void two_generations_produces_correct_space() {
        Point point = new Point(0, 1);
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 1);
        Point point3 = new Point(3, 1);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);
        cells.addAt(point2);
        cells.addAt(point3);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 4, 4);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        Point point4 = new Point(1, 0);
        Point point5 = new Point(2, 0);
        Point point6 = new Point(0, 1);
        Point point7 = new Point(3, 1);
        Point point8 = new Point(1, 2);
        Point point9 = new Point(2, 2);

        assertTrue(cells.hasCellAt(point4));
        assertTrue(cells.hasCellAt(point5));
        assertTrue(cells.hasCellAt(point6));
        assertTrue(cells.hasCellAt(point7));
        assertTrue(cells.hasCellAt(point8));
        assertTrue(cells.hasCellAt(point9));
    }
}
