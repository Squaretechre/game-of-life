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

        var cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        var cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        var cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        var cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));

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

        var cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));

        cells.add(cell);

        twoDimensionalSpace.tick();

        var cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));

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

        var cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        var cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        var cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        final var point3 = new Point(1, 0);
        final var point4 = new Point(1, 1);

        var cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));
        var cell4 = new Cell(point4, cells, new Neighbourhood(point4, twoDimensionalSpace));

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

        var cell = new Cell(point, cells, new Neighbourhood(point, twoDimensionalSpace));
        var cell1 = new Cell(point1, cells, new Neighbourhood(point1, twoDimensionalSpace));
        var cell2 = new Cell(point2, cells, new Neighbourhood(point2, twoDimensionalSpace));
        var cell3 = new Cell(point3, cells, new Neighbourhood(point3, twoDimensionalSpace));
        var cell4 = new Cell(point4, cells, new Neighbourhood(point4, twoDimensionalSpace));

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

        var cell5 = new Cell(point5, cells, new Neighbourhood(point5, twoDimensionalSpace));

        var cell6 = new Cell(point6, cells, new Neighbourhood(point6, twoDimensionalSpace));
        var cell7 = new Cell(point7, cells, new Neighbourhood(point7, twoDimensionalSpace));
        var cell8 = new Cell(point8, cells, new Neighbourhood(point8, twoDimensionalSpace));
        var cell9 = new Cell(point9, cells, new Neighbourhood(point9, twoDimensionalSpace));
        var cell10 = new Cell(point10, cells, new Neighbourhood(point10, twoDimensionalSpace));
        var cell11 = new Cell(point11, cells, new Neighbourhood(point11, twoDimensionalSpace));
        var cell12 = new Cell(point12, cells, new Neighbourhood(point12, twoDimensionalSpace));
        var cell13 = new Cell(point13, cells, new Neighbourhood(point13, twoDimensionalSpace));

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

        var cell = createCell(neighbourhoodFactory, cells, new Point(0, 1));
        var cell1 = createCell(neighbourhoodFactory, cells, new Point(1, 1));
        var cell2 = createCell(neighbourhoodFactory, cells, new Point(2, 1));
        var cell3 = createCell(neighbourhoodFactory, cells, new Point(3, 1));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);

        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        var cell4 = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell5 = createCell(neighbourhoodFactory, cells, new Point(2, 0));
        var cell6 = createCell(neighbourhoodFactory, cells, new Point(0, 1));
        var cell7 = createCell(neighbourhoodFactory, cells, new Point(3, 1));
        var cell8 = createCell(neighbourhoodFactory, cells, new Point(1, 2));
        var cell9 = createCell(neighbourhoodFactory, cells, new Point(2, 2));

        assertTrue(cells.contains(cell4));
        assertTrue(cells.contains(cell5));
        assertTrue(cells.contains(cell6));
        assertTrue(cells.contains(cell7));
        assertTrue(cells.contains(cell8));
        assertTrue(cells.contains(cell9));
    }

    private Cell createCell(NeighbourhoodFactory neighbourhoodFactory, Cells cells, Point point) {
        return new Cell(point, cells, neighbourhoodFactory.createFor(point));
    }
}
