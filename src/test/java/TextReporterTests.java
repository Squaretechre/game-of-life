import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.containsString;

public class TextReporterTests {
    @Test
    public void reports_correct_cell_output_after_two_generations() throws IOException {
        Point point = new Point(0, 1);
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 1);
        Point point3 = new Point(3, 1);

        var cells = new Cells();
        cells.addAt(point);
        cells.addAt(point1);
        cells.addAt(point2);
        cells.addAt(point3);

        var textReporter = new TextReporter(cells, 4, 4);

        var twoDimensionalSpace = new TwoDimensionalSpace(cells, cells, 4, 4);
        twoDimensionalSpace.tick();
        twoDimensionalSpace.tick();

        Class className = TextReporterTests.class;
        InputStream inputStream = className.getResourceAsStream("/expected-cells-1.txt");
        String expectedData = IOUtils.toString(inputStream, Charset.defaultCharset());

        Assert.assertThat(textReporter.output(), containsString(expectedData));
    }
}
