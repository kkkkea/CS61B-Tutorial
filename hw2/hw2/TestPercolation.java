package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testPercolation() {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 1);
        assertEquals(3, p.numberOfOpenSites());
        assertFalse(p.percolates());
        p.open(2, 0);
        assertTrue(p.percolates());
    }
}
