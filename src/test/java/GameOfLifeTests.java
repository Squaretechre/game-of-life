import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameOfLifeTests {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        var cells = new Cells(new NeighbourhoodFactory(twoDimensionalSpace));

        twoDimensionalSpace.setCells(cells);

        final var point = new Point(0, 0);
        final var point1 = new Point(0, 1);
        final var point2 = new Point(1, 0);
        final var point3 = new Point(1, 1);

        Cell cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        Cell cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        Cell cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        Cell cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));

        assertTrue(cells.contains(cell));
        assertTrue(cells.contains(cell1));
        assertTrue(cells.contains(cell2));
        assertTrue(cells.contains(cell3));
    }

    @Test
    public void cell_with_fewer_than_two_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        var cells = new Cells(new NeighbourhoodFactory(twoDimensionalSpace));

        twoDimensionalSpace.setCells(cells);

        final var point = new Point(0, 0);
        final var point1 = new Point(0, 0);

        Cell cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));

        cells.add(cell);

        twoDimensionalSpace.tick();

        Cell cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));

        assertFalse(cells.contains(cell1));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var cells = new Cells(new NeighbourhoodFactory(twoDimensionalSpace));

        twoDimensionalSpace.setCells(cells);

        final var point = new Point(0, 0);
        final var point1 = new Point(1, 0);
        final var point2 = new Point(2, 0);

        Cell cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        Cell cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        Cell cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        final var point3 = new Point(1, 0);
        final var point4 = new Point(1, 1);

        Cell cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));
        Cell cell4 = new Cell(point4, cells, new Neighbourhood(point4, twoDimensionalSpace));

        assertTrue(cells.contains(cell3));
        assertTrue(cells.contains(cell4));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var cells = new Cells(new NeighbourhoodFactory(twoDimensionalSpace));

        twoDimensionalSpace.setCells(cells);

        final var point = new Point(1, 0);
        final var point1 = new Point(0, 1);
        final var point2 = new Point(1, 1);
        final var point3 = new Point(2, 1);
        final var point4 = new Point(1, 2);

        Cell cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        Cell cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        Cell cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));
        Cell cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));
        Cell cell4 = new Cell(point4, cells, new Neighbourhood(point4, twoDimensionalSpace));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);

        twoDimensionalSpace.tick();

        final var point5 = new Point(1, 1);
        final var point6 = new Point(0, 0);
        final var point7 = new Point(1, 0);
        final var point8 = new Point(2, 0);
        final var point9 = new Point(0, 1);
        final var point10 = new Point(2, 1);
        final var point11 = new Point(0, 2);
        final var point12 = new Point(1, 2);
        final var point13 = new Point(2, 2);

        Cell cell5 = new Cell(point5, cells, new Neighbourhood(point5, twoDimensionalSpace));

        Cell cell6 = new Cell(point6, cells, new Neighbourhood(point6, twoDimensionalSpace));
        Cell cell7 = new Cell(point7, cells, new Neighbourhood(point7, twoDimensionalSpace));
        Cell cell8 = new Cell(point8, cells, new Neighbourhood(point8, twoDimensionalSpace));
        Cell cell9 = new Cell(point9, cells, new Neighbourhood(point9, twoDimensionalSpace));
        Cell cell10 = new Cell(point10, cells, new Neighbourhood(point10, twoDimensionalSpace));
        Cell cell11 = new Cell(point11, cells, new Neighbourhood(point11, twoDimensionalSpace));
        Cell cell12 = new Cell(point12, cells, new Neighbourhood(point12, twoDimensionalSpace));
        Cell cell13 = new Cell(point13, cells, new Neighbourhood(point13, twoDimensionalSpace));

        assertFalse(cells.contains(cell5));

        assertTrue(cells.contains(cell6));
        assertTrue(cells.contains(cell7));
        assertTrue(cells.contains(cell8));
        assertTrue(cells.contains(cell9));
        assertTrue(cells.contains(cell10));
        assertTrue(cells.contains(cell11));
        assertTrue(cells.contains(cell12));
        assertTrue(cells.contains(cell13));
    }

    @Test
    public void two_generations_produces_correct_space() {
        var twoDimensionalSpace = new TwoDimensionalSpace(4, 4);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);
        var cells = new Cells(neighbourhoodFactory);

        twoDimensionalSpace.setCells(cells);

        final var point = new Point(0, 1);
        final var point1 = new Point(1, 1);
        final var point2 = new Point(2, 1);
        final var point3 = new Point(3, 1);

        Cell cell = new Cell(point, cells, neighbourhoodFactory.createFor(point));
        Cell cell1 = new Cell(point1, cells, neighbourhoodFactory.createFor(point1));
        Cell cell2 = new Cell(point2, cells, neighbourhoodFactory.createFor(point2));
        Cell cell3 = new Cell(point3, cells, neighbourhoodFactory.createFor(point3));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        final var point4 = new Point(1, 0);
        final var point5 = new Point(2, 0);
        final var point6 = new Point(0, 1);
        final var point7 = new Point(3, 1);
        final var point8 = new Point(1, 2);
        final var point9 = new Point(2, 2);

        Cell cell4 = new Cell(point4, cells, neighbourhoodFactory.createFor(point4));
        Cell cell5 = new Cell(point5, cells, neighbourhoodFactory.createFor(point5));
        Cell cell6 = new Cell(point6, cells, neighbourhoodFactory.createFor(point6));
        Cell cell7 = new Cell(point7, cells, neighbourhoodFactory.createFor(point7));
        Cell cell8 = new Cell(point8, cells, neighbourhoodFactory.createFor(point8));
        Cell cell9 = new Cell(point9, cells, neighbourhoodFactory.createFor(point9));

        assertTrue(cells.contains(cell4));
        assertTrue(cells.contains(cell5));
        assertTrue(cells.contains(cell6));
        assertTrue(cells.contains(cell7));
        assertTrue(cells.contains(cell8));
        assertTrue(cells.contains(cell9));
    }
}
