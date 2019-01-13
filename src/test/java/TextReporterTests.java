import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;

import static junit.framework.TestCase.assertEquals;

public class TextReporterTests {
    @Test
    public void straight_line_of_four_cells_reports_correct_cell_output_after_one_generation() throws IOException {
        var cells = new LivingCells();
        cells.addAt(new Point(0, 1));
        cells.addAt(new Point(1, 1));
        cells.addAt(new Point(2, 1));
        cells.addAt(new Point(3, 1));

        var textReporter = new TextReporter(cells, 4, 4);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 4, 4);
        twoDimensionalSpace.tick();

        assertEquals(cellsIn("/straight-line-one-gen.txt"), textReporter.output());
    }

    @Test
    public void straight_line_of_four_cells_reports_correct_cell_output_after_two_generations() throws IOException {
        var cells = new LivingCells();
        cells.addAt(new Point(0, 1));
        cells.addAt(new Point(1, 1));
        cells.addAt(new Point(2, 1));
        cells.addAt(new Point(3, 1));

        var textReporter = new TextReporter(cells, 4, 4);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 4, 4);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        assertEquals(cellsIn("/straight-line-two-gen.txt"), textReporter.output());
    }

    @Test
    public void fortress_reports_correct_cell_output_after_three_generations() throws IOException {
        var cells = new LivingCells();
        cells.addAt(new Point(2, 0));
        cells.addAt(new Point(3, 0));
        cells.addAt(new Point(6, 0));
        cells.addAt(new Point(7, 0));
        cells.addAt(new Point(2, 2));
        cells.addAt(new Point(7, 2));
        cells.addAt(new Point(2, 3));
        cells.addAt(new Point(3, 3));
        cells.addAt(new Point(6, 3));
        cells.addAt(new Point(7, 3));

        var textReporter = new TextReporter(cells, 10, 5);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 10, 5);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        assertEquals(cellsIn("/fortress-three-gen.txt"), textReporter.output());
    }

    @Test
    public void reverse_pac_man_reports_correct_cell_output_after_two_generations() throws IOException {
        var cells = new LivingCells();
        cells.addAt(new Point(3, 2));
        cells.addAt(new Point(4, 2));
        cells.addAt(new Point(5, 2));
        cells.addAt(new Point(2, 3));
        cells.addAt(new Point(5, 3));
        cells.addAt(new Point(6, 3));
        cells.addAt(new Point(7, 4));
        cells.addAt(new Point(2, 5));
        cells.addAt(new Point(5, 5));
        cells.addAt(new Point(6, 5));
        cells.addAt(new Point(3, 6));
        cells.addAt(new Point(4, 6));
        cells.addAt(new Point(5, 6));

        var textReporter = new TextReporter(cells, 10, 10);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 10, 10);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        assertEquals(cellsIn("/reverse-pac-man-two-gen.txt"), textReporter.output());
    }

    @Test
    public void glider_reports_correct_cell_output_after_ten_generations() throws IOException {
        var cells = new LivingCells();
        cells.addAt(new Point(1, 0));
        cells.addAt(new Point(2, 1));
        cells.addAt(new Point(0, 2));
        cells.addAt(new Point(1, 2));
        cells.addAt(new Point(2, 2));

        var textReporter = new TextReporter(cells, 10, 10);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 10, 10);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        assertEquals(cellsIn("/glider-ten-gen.txt"), textReporter.output());
    }

    private String cellsIn(String s) throws IOException {
        Class className = TextReporterTests.class;
        var inputStream = className.getResourceAsStream(s);
        return IOUtils.toString(inputStream, Charset.defaultCharset());
    }
}
