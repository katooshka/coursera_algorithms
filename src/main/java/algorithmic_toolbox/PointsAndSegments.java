package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PointsAndSegments {
    public static void main(String[] args) throws IOException {
        List<Point> points = new ArrayList<>();
        int pointsCount;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String firstLine = br.readLine();
            int segmentsCount = Integer.parseInt(firstLine.split(" ")[0]);
            pointsCount = Integer.parseInt(firstLine.split(" ")[1]);
            for (int i = 0; i < segmentsCount; i++) {
                String line = br.readLine();
                points.add(new Point(PointType.START, Integer.parseInt(line.split(" ")[0])));
                points.add(new Point(PointType.END, Integer.parseInt(line.split(" ")[1])));
            }
            String[] line = br.readLine().split(" ");
            for (int i = 0; i < pointsCount; i++) {
                points.add(new Point(PointType.POINT, Integer.parseInt(line[i]), i));
            }
        }
        int[] result = getPointsRangesCount(pointsCount, points);
        for (int aResult : result) {
            System.out.print(aResult + " ");
        }
    }

    public static int[] getPointsRangesCount(int pointsCount, List<Point> points) {
        points.sort(new PointsAndSegments.Point.PointsTypeComparator());
        points.sort(new PointsAndSegments.Point.PointsValueComparator());
        int openSegments = 0;
        int[] result = new int[pointsCount];
        for (Point point : points) {
            if (point.type == PointType.START) {
                openSegments++;
            } else if (point.type == PointType.END) {
                openSegments--;
            } else {
                result[point.position] = openSegments;
            }
        }
        return result;
    }

    public enum PointType {
        START(),
        END(),
        POINT()
    }


    public static class Point implements Comparable {
        public PointType type;
        public int value;
        public int position;

        public Point(PointType type, int value) {
            this.type = type;
            this.value = value;
        }

        public Point(PointType type, int value, int position) {
            this.type = type;
            this.value = value;
            this.position = position;
        }

        @Override
        public String toString() {
            return "[" + type +
                    ", " + value + "]";
        }

        @Override
        public int compareTo(Object o) {
            Point point2 = (Point) o;
            if (point2.value > value) {
                return -1;
            } else if (point2.value < value) {
                return 1;
            } else {
                if (type.equals(PointType.START) && point2.type.equals(PointType.POINT)
                        || type.equals(PointType.POINT) && point2.type.equals(PointType.END)) {
                    return -1;
                } else if (type.equals(PointType.END) && point2.type.equals(PointType.POINT)
                        || type.equals(PointType.POINT) && point2.type.equals(PointType.START)) {
                    return 1;
                } else
                    return 0;
            }
        }

        public int compareToByType(Object o) {
            Point point2 = (Point) o;
            if (type.equals(PointType.START) && point2.type.equals(PointType.POINT) || type.equals(PointType.POINT) && point2.type.equals(PointType.END) ||
                    type.equals(PointType.START) && point2.type.equals(PointType.END)) {
                return -1;
            } else if (type.equals(PointType.POINT) && point2.type.equals(PointType.START) || type.equals(PointType.END) && point2.type.equals(PointType.POINT) ||
                    type.equals(PointType.END) && point2.type.equals(PointType.START)) {
                return 1;
            } else
                return 0;
        }

        static class PointsTypeComparator implements Comparator<Point> {

            @Override
            public int compare(Point point1, Point point2) {
                return point1.compareToByType(point2);
            }
        }

        static class PointsValueComparator implements Comparator<Point> {

            @Override
            public int compare(Point point1, Point point2) {
                return point1.compareTo(point2);
            }
        }
    }
}
