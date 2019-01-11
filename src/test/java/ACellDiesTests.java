import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ACellDiesTests {
    @Test
    public void cell_with_fewer_than_two_live_neighbours_dies() {
       assertEquals("...", new Life(".x.").go());
    }

    @Test
    public void cell_with_fewer_than_two_live_neighbours_dies_again() {
        assertEquals(".....", new Life(".x.x.").go());
    }

    @Test
    public void cell_with_fewer_than_two_live_neighbours_dies_again_and_again() {
        assertEquals(".......", new Life(".x.x.x.").go());
    }

    @Test
    public void cell_with_fewer_than_two_live_neighbours_dies_again_and_again_and_again() {
        assertEquals(".........", new Life(".x.x.x.x.").go());
    }

    @Test
    public void cell_with_fewer_than_two_live_neighbours_dies_two_lines() {
        assertEquals("...\n...", new Life(".x.\n.x.").go());
    }
}
