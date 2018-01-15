import org.junit.Test;

import java.util.Arrays;

import static algorithmic_toolbox.PointsAndSegments.*;
import static org.junit.Assert.assertEquals;

public class PointsAndSegmentsTest {

    @Test
    public void testPointsAndSegments() {
        assertEquals(
                Arrays.toString(new int[]{1, 0, 0}),
                Arrays.toString(getPointsRangesCount(3, Arrays.asList(
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 5),
                        new Point(PointType.START, 7),
                        new Point(PointType.END, 10),
                        new Point(PointType.POINT, 1, 0),
                        new Point(PointType.POINT, 6, 1),
                        new Point(PointType.POINT, 11, 2)))));
        assertEquals(
                Arrays.toString(new int[]{0, 0, 1}),
                Arrays.toString(getPointsRangesCount(3, Arrays.asList(
                        new Point(PointType.START, -10),
                        new Point(PointType.END, 10),
                        new Point(PointType.POINT, -100, 0),
                        new Point(PointType.POINT, 100, 1),
                        new Point(PointType.POINT, 0, 2)))));
        assertEquals(
                Arrays.toString(new int[]{2, 0}),
                Arrays.toString(getPointsRangesCount(2, Arrays.asList(
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 5),
                        new Point(PointType.START, -3),
                        new Point(PointType.END, 2),
                        new Point(PointType.START, 7),
                        new Point(PointType.END, 10),
                        new Point(PointType.POINT, 1, 0),
                        new Point(PointType.POINT, 6, 1)))));
        assertEquals(
                Arrays.toString(new int[]{1}),
                Arrays.toString(getPointsRangesCount(1, Arrays.asList(
                        new Point(PointType.START, 1),
                        new Point(PointType.END, 1),
                        new Point(PointType.START, 2),
                        new Point(PointType.END, 4),
                        new Point(PointType.POINT, 4, 0)))));
        assertEquals(
                Arrays.toString(new int[]{0, 1, 0, 1}),
                Arrays.toString(getPointsRangesCount(4, Arrays.asList(
                        new Point(PointType.START, 2),
                        new Point(PointType.END, 4),
                        new Point(PointType.START, -4),
                        new Point(PointType.END, -2),
                        new Point(PointType.START, 9),
                        new Point(PointType.END, 11),
                        new Point(PointType.POINT, 14, 0),
                        new Point(PointType.POINT, 2, 1),
                        new Point(PointType.POINT, 30, 2),
                        new Point(PointType.POINT, 9, 3)))));
        assertEquals(
                Arrays.toString(new int[]{1, 1, 1}),
                Arrays.toString(getPointsRangesCount(3, Arrays.asList(
                        new Point(PointType.START, -9),
                        new Point(PointType.END, 52),
                        new Point(PointType.START, 14),
                        new Point(PointType.END, 97),
                        new Point(PointType.START, 32),
                        new Point(PointType.END, 77),
                        new Point(PointType.POINT, -9, 0),
                        new Point(PointType.POINT, -9, 1),
                        new Point(PointType.POINT, -9, 2)))));
        assertEquals(
                Arrays.toString(new int[]{3, 3, 3}),
                Arrays.toString(getPointsRangesCount(3, Arrays.asList(
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 2),
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 2),
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 2),
                        new Point(PointType.POINT, 1, 0),
                        new Point(PointType.POINT, 1, 1),
                        new Point(PointType.POINT, 1, 2)))));
        assertEquals(
                Arrays.toString(new int[]{3, 3, 3}),
                Arrays.toString(getPointsRangesCount(3, Arrays.asList(
                        new Point(PointType.START, 0),
                        new Point(PointType.START, 0),
                        new Point(PointType.START, 0),
                        new Point(PointType.END, 0),
                        new Point(PointType.END, 0),
                        new Point(PointType.END, 0),
                        new Point(PointType.POINT, 0, 0),
                        new Point(PointType.POINT, 0, 1),
                        new Point(PointType.POINT, 0, 2)))));
    }
}
