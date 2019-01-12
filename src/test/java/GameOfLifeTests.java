import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameOfLifeTests {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);

        var cells = new Cells(neighbourhoodFactory);

        twoDimensionalSpace.setCells(cells);

        var cell = createCell(neighbourhoodFactory, cells, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, cells, new Point(0, 1));
        var cell2 = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell3 = createCell(neighbourhoodFactory, cells, new Point(1, 1));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        assertTrue(cells.contains(cell));
        assertTrue(cells.contains(cell1));
        assertTrue(cells.contains(cell2));
        assertTrue(cells.contains(cell3));
    }

    @Test
    public void cell_with_fewer_than_two_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);
        var cells = new Cells(neighbourhoodFactory);

        twoDimensionalSpace.setCells(cells);

        var cell = createCell(neighbourhoodFactory, cells, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, cells, new Point(0, 0));

        cells.add(cell);

        twoDimensionalSpace.tick();

        assertFalse(cells.contains(cell1));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);
        var cells = new Cells(neighbourhoodFactory);

        twoDimensionalSpace.setCells(cells);

        var cell = createCell(neighbourhoodFactory, cells, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell2 = createCell(neighbourhoodFactory, cells, new Point(2, 0));
        var cell3 = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell4 = createCell(neighbourhoodFactory, cells, new Point(1, 1));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);

        twoDimensionalSpace.tick();

        assertTrue(cells.contains(cell3));
        assertTrue(cells.contains(cell4));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);
        var cells = new Cells(neighbourhoodFactory);

        twoDimensionalSpace.setCells(cells);

        var cell = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell1 = createCell(neighbourhoodFactory, cells, new Point(0, 1));
        var cell2 = createCell(neighbourhoodFactory, cells, new Point(1, 1));
        var cell3 = createCell(neighbourhoodFactory, cells, new Point(2, 1));
        var cell4 = createCell(neighbourhoodFactory, cells, new Point(1, 2));
        var cell5 = createCell(neighbourhoodFactory, cells, new Point(1, 1));
        var cell6 = createCell(neighbourhoodFactory, cells, new Point(0, 0));
        var cell7 = createCell(neighbourhoodFactory, cells, new Point(1, 0));
        var cell8 = createCell(neighbourhoodFactory, cells, new Point(2, 0));
        var cell9 = createCell(neighbourhoodFactory, cells, new Point(0, 1));
        var cell10 = createCell(neighbourhoodFactory, cells, new Point(2, 1));
        var cell11 = createCell(neighbourhoodFactory, cells, new Point(0, 2));
        var cell12 = createCell(neighbourhoodFactory, cells, new Point(1, 2));
        var cell13 = createCell(neighbourhoodFactory, cells, new Point(2, 2));

        cells.add(cell);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);

        twoDimensionalSpace.tick();

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
