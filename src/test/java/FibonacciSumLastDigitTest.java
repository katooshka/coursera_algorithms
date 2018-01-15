import algorithmic_toolbox.FibonacciSumLastDigit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciSumLastDigitTest {

    @Test
    public void testFibonacciSumLastDigit() {
        assertEquals(0, FibonacciSumLastDigit.findSumOfFibonacciByModule(0));
        assertEquals(1, FibonacciSumLastDigit.findSumOfFibonacciByModule(1));
        assertEquals(2, FibonacciSumLastDigit.findSumOfFibonacciByModule(2));
        assertEquals(4, FibonacciSumLastDigit.findSumOfFibonacciByModule(3));
        assertEquals(7, FibonacciSumLastDigit.findSumOfFibonacciByModule(4));
        assertEquals(2, FibonacciSumLastDigit.findSumOfFibonacciByModule(5));
        assertEquals(0, FibonacciSumLastDigit.findSumOfFibonacciByModule(6));
        assertEquals(3, FibonacciSumLastDigit.findSumOfFibonacciByModule(7));
        assertEquals(4, FibonacciSumLastDigit.findSumOfFibonacciByModule(8));
        assertEquals(8, FibonacciSumLastDigit.findSumOfFibonacciByModule(9));
        assertEquals(3, FibonacciSumLastDigit.findSumOfFibonacciByModule(10));

        assertEquals(5, FibonacciSumLastDigit.findSumLastDigit(100));
    }
 }
