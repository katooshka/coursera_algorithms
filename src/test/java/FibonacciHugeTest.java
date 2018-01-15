import algorithmic_toolbox.FibonacciHuge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciHugeTest {

    @Test
    public void testFibonacciHuge() {
        assertEquals(3, FibonacciHuge.findPisanoPeriodLength(2));
        assertEquals(8, FibonacciHuge.findPisanoPeriodLength(3));
        assertEquals(6, FibonacciHuge.findPisanoPeriodLength(4));
        assertEquals(20, FibonacciHuge.findPisanoPeriodLength(5));
        assertEquals(24, FibonacciHuge.findPisanoPeriodLength(6));
        assertEquals(16, FibonacciHuge.findPisanoPeriodLength(7));
        assertEquals(12, FibonacciHuge.findPisanoPeriodLength(8));
        assertEquals(24, FibonacciHuge.findPisanoPeriodLength(9));
        assertEquals(60, FibonacciHuge.findPisanoPeriodLength(10));
    }
}
