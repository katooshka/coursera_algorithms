import algorithmic_toolbox.MajorityElementNComplexity;
import algorithmic_toolbox.MajorityElementNlogNComplexity;
import org.junit.Test;

import static algorithmic_toolbox.MajorityElementNlogNComplexity.*;
import static algorithmic_toolbox.MajorityElementNlogNComplexity.findMajorElement;
import static org.junit.Assert.assertEquals;

public class MajorityElementTest {

    @Test
    public void testMajorElementNlogN() {
        assertEquals(1, returnResult(findMajorElement(new int[]{1}, 0, 1)));
        assertEquals(0, returnResult(findMajorElement(new int[]{1, 2}, 0, 2)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 1, 2}, 0, 3)));
        assertEquals(0, returnResult(findMajorElement(new int[]{1, 2, 3}, 0, 3)));
        assertEquals(0, returnResult(findMajorElement(new int[]{1, 1, 1, 2, 2, 2}, 0, 6)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 1, 1, 2, 2, 2, 2}, 0, 7)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2}, 0, 9)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 1, 1, 2, 2, 2, 2, 2, 1}, 0, 9)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 1, 2, 2, 2, 2, 2, 1, 1}, 0, 9)));
        assertEquals(1, returnResult(findMajorElement(new int[]{1, 2, 2, 2, 2, 2, 1, 1, 1}, 0, 9)));
        assertEquals(1, returnResult(findMajorElement(new int[]{2, 2, 2, 2, 2, 1, 1, 1, 1}, 0, 9)));
    }

    @Test
    public void testMajorElementN() {
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1}));
        assertEquals(0, MajorityElementNComplexity.findMajorElement(new int[]{1, 2}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 2}));
        assertEquals(0, MajorityElementNComplexity.findMajorElement(new int[]{1, 2, 3}));
        assertEquals(0, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 1, 2, 2, 2}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 1, 2, 2, 2, 2}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 1, 2, 2, 2, 2, 2, 1}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 1, 2, 2, 2, 2, 2, 1, 1}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{1, 2, 2, 2, 2, 2, 1, 1, 1}));
        assertEquals(1, MajorityElementNComplexity.findMajorElement(new int[]{2, 2, 2, 2, 2, 1, 1, 1, 1}));
    }
}
