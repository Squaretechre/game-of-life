import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameOfLifeTests {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        final var point = new Point(0, 0);
        final var point1 = new Point(0, 1);
        final var point2 = new Point(1, 0);
        final var point3 = new Point(1, 1);

        twoDimensionalSpace.registerCell(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point1, twoDimensionalSpace, new Neighbourhood(point1, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point2, twoDimensionalSpace, new Neighbourhood(point2, twoDimensionalSpace)));

        twoDimensionalSpace.tick();

        assertTrue(twoDimensionalSpace.contains(new Cell(point3, twoDimensionalSpace, new Neighbourhood(point3, twoDimensionalSpace))));
    }

    @Test
    public void cell_with_fewer_than_two_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        final var point = new Point(0, 0);
        final var point1 = new Point(0, 0);

        twoDimensionalSpace.registerCell(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));

        twoDimensionalSpace.tick();

        assertFalse(twoDimensionalSpace.contains(new Cell(point1, twoDimensionalSpace, new Neighbourhood(point1, twoDimensionalSpace))));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        final var point = new Point(0, 0);
        final var point1 = new Point(1, 0);
        final var point2 = new Point(2, 0);

        twoDimensionalSpace.registerCell(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point1, twoDimensionalSpace, new Neighbourhood(point1, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point2, twoDimensionalSpace, new Neighbourhood(point2, twoDimensionalSpace)));

        twoDimensionalSpace.tick();

        final var point3 = new Point(1, 0);
        final var point4 = new Point(1, 1);

        assertTrue(twoDimensionalSpace.contains(new Cell(point3, twoDimensionalSpace, new Neighbourhood(point3, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point4, twoDimensionalSpace, new Neighbourhood(point4, twoDimensionalSpace))));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        final var point = new Point(1, 0);
        final var point1 = new Point(0, 1);
        final var point2 = new Point(1, 1);
        final var point3 = new Point(2, 1);
        final var point4 = new Point(1, 2);
        final var point5 = new Point(1, 1);

        twoDimensionalSpace.registerCell(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point1, twoDimensionalSpace, new Neighbourhood(point1, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point2, twoDimensionalSpace, new Neighbourhood(point2, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point3, twoDimensionalSpace, new Neighbourhood(point3, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point4, twoDimensionalSpace, new Neighbourhood(point4, twoDimensionalSpace)));

        twoDimensionalSpace.tick();

        assertFalse(twoDimensionalSpace.contains(new Cell(point5, twoDimensionalSpace, new Neighbourhood(point5, twoDimensionalSpace))));
    }

    @Test
    public void two_generations_produces_correct_space() {
        var twoDimensionalSpace = new TwoDimensionalSpace(4, 4);
        final var point = new Point(0, 1);
        final var point1 = new Point(1, 1);
        final var point2 = new Point(2, 1);
        final var point3 = new Point(3, 1);

        twoDimensionalSpace.registerCell(new Cell(point, twoDimensionalSpace, new Neighbourhood(point, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point1, twoDimensionalSpace, new Neighbourhood(point1, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point2, twoDimensionalSpace, new Neighbourhood(point2, twoDimensionalSpace)));
        twoDimensionalSpace.registerCell(new Cell(point3, twoDimensionalSpace, new Neighbourhood(point3, twoDimensionalSpace)));

        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        final var point4 = new Point(1, 0);
        final var point5 = new Point(2, 0);
        final var point6 = new Point(0, 1);
        final var point7 = new Point(3, 1);
        final var point8 = new Point(1, 2);
        final var point9 = new Point(2, 2);

        assertTrue(twoDimensionalSpace.contains(new Cell(point4, twoDimensionalSpace, new Neighbourhood(point4, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point5, twoDimensionalSpace, new Neighbourhood(point5, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point6, twoDimensionalSpace, new Neighbourhood(point6, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point7, twoDimensionalSpace, new Neighbourhood(point7, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point8, twoDimensionalSpace, new Neighbourhood(point8, twoDimensionalSpace))));
        assertTrue(twoDimensionalSpace.contains(new Cell(point9, twoDimensionalSpace, new Neighbourhood(point9, twoDimensionalSpace))));
    }
}
