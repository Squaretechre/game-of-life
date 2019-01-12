import org.junit.Test;
import java.awt.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameOfLifeTests {

    @Test
    public void empty_cell_becomes_populated_when_it_has_three_neighbours() {
        var twoDimensionalSpace = new TwoDimensionalSpace(2, 2);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);

        var cell = createCell(neighbourhoodFactory, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, new Point(0, 1));
        var cell2 = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell3 = createCell(neighbourhoodFactory, new Point(1, 1));

        var livingCells = new ArrayList<Cell>() {{
           add(cell);
           add(cell1);
           add(cell2);
           add(cell3);
        }};

        var cells = new Cells(neighbourhoodFactory, livingCells);

        twoDimensionalSpace.setCells(cells);
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

        var cell = createCell(neighbourhoodFactory, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, new Point(0, 0));

        var livingCells = new ArrayList<Cell>() {{
            add(cell);
            add(cell1);
        }};

        var cells = new Cells(neighbourhoodFactory, livingCells);

        twoDimensionalSpace.setCells(cells);
        twoDimensionalSpace.tick();

        assertFalse(cells.contains(cell1));
    }

    @Test
    public void cell_with_two_neighbours_lives_on() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);

        var cell = createCell(neighbourhoodFactory, new Point(0, 0));
        var cell1 = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell2 = createCell(neighbourhoodFactory, new Point(2, 0));
        var cell3 = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell4 = createCell(neighbourhoodFactory, new Point(1, 1));

        var livingCells = new ArrayList<Cell>() {{
            add(cell);
            add(cell1);
            add(cell2);
            add(cell3);
            add(cell4);
        }};

        var cells = new Cells(neighbourhoodFactory, livingCells);

        twoDimensionalSpace.setCells(cells);
        twoDimensionalSpace.tick();

        assertTrue(cells.contains(cell3));
        assertTrue(cells.contains(cell4));
    }

    @Test
    public void cell_with_more_than_three_neighbours_dies() {
        var twoDimensionalSpace = new TwoDimensionalSpace(3, 3);
        var neighbourhoodFactory = new NeighbourhoodFactory(twoDimensionalSpace);

        var cell = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell1 = createCell(neighbourhoodFactory, new Point(0, 1));
        var cell2 = createCell(neighbourhoodFactory, new Point(1, 1));
        var cell3 = createCell(neighbourhoodFactory, new Point(2, 1));
        var cell4 = createCell(neighbourhoodFactory, new Point(1, 2));
        var cell5 = createCell(neighbourhoodFactory, new Point(1, 1));
        var cell6 = createCell(neighbourhoodFactory, new Point(0, 0));
        var cell7 = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell8 = createCell(neighbourhoodFactory, new Point(2, 0));
        var cell9 = createCell(neighbourhoodFactory, new Point(0, 1));
        var cell10 = createCell(neighbourhoodFactory, new Point(2, 1));
        var cell11 = createCell(neighbourhoodFactory, new Point(0, 2));
        var cell12 = createCell(neighbourhoodFactory, new Point(1, 2));
        var cell13 = createCell(neighbourhoodFactory, new Point(2, 2));

        var livingCells = new ArrayList<Cell>() {{
            add(cell);
            add(cell1);
            add(cell2);
            add(cell3);
            add(cell4);
        }};

        var cells = new Cells(neighbourhoodFactory, livingCells);

        twoDimensionalSpace.setCells(cells);
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

        var cell = createCell(neighbourhoodFactory, new Point(0, 1));
        var cell1 = createCell(neighbourhoodFactory, new Point(1, 1));
        var cell2 = createCell(neighbourhoodFactory, new Point(2, 1));
        var cell3 = createCell(neighbourhoodFactory, new Point(3, 1));

        var livingCells = new ArrayList<Cell>() {{
            add(cell);
            add(cell1);
            add(cell2);
            add(cell3);
        }};

        var cells = new Cells(neighbourhoodFactory, livingCells);

        twoDimensionalSpace.setCells(cells);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        var cell4 = createCell(neighbourhoodFactory, new Point(1, 0));
        var cell5 = createCell(neighbourhoodFactory, new Point(2, 0));
        var cell6 = createCell(neighbourhoodFactory, new Point(0, 1));
        var cell7 = createCell(neighbourhoodFactory, new Point(3, 1));
        var cell8 = createCell(neighbourhoodFactory, new Point(1, 2));
        var cell9 = createCell(neighbourhoodFactory, new Point(2, 2));

        assertTrue(cells.contains(cell4));
        assertTrue(cells.contains(cell5));
        assertTrue(cells.contains(cell6));
        assertTrue(cells.contains(cell7));
        assertTrue(cells.contains(cell8));
        assertTrue(cells.contains(cell9));
    }

    private Cell createCell(NeighbourhoodFactory neighbourhoodFactory, Point point) {
        return new Cell(point, neighbourhoodFactory.createFor(point));
    }
}
