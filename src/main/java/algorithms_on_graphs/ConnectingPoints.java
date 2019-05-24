package algorithms_on_graphs;

import java.util.*;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        List<Point> points = new ArrayList<>();
        int[] parents = new int[x.length];
        int[] ranks = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            points.add(new Point(x[i], y[i]));
            parents[i] = i;
            ranks[i] = 0;
        }
        List<Edge> edges = new ArrayList<>();
        int secondPointIndex = 1;
        for (int i = 0; i < points.size() && secondPointIndex < points.size(); i++) {
            for (int j = secondPointIndex; j < points.size(); j++) {
                edges.add(new Edge(i, j, calculateDistance(i, j, points)));
            }
            secondPointIndex++;
        }
        Collections.sort(edges);
        double path = 0;
        for (Edge edge : edges) {
            if (find(parents, edge.startEdgeNumber) != find(parents, edge.endEdgeNumber)) {
                path += edge.distance;
                union(parents, ranks, edge.startEdgeNumber, edge.endEdgeNumber);
            }
        }
        return path;
//        double result = 0.;
//        return result;
    }

    private static int find(int[] parents, int i) {
        if (parents[i] != i) {
            parents[i] = find(parents, parents[i]);
        }
        return parents[i];
    }

    private static void union(int[] parents, int[] ranks, int first, int second) {
        int firstId = find(parents, first);
        int secondId = find(parents, second);
        if (firstId == secondId) {
            return;
        }
        if (ranks[firstId] > ranks[secondId]) {
            parents[secondId] = firstId;
        } else {
            parents[firstId] = secondId;
            if (ranks[firstId] == ranks[secondId]) {
                ranks[secondId] = ranks[secondId] + 1;
            }
        }
    }

    private static double calculateDistance(int first, int second, List<Point> points) {
        double side1 = points.get(first).x - points.get(second).x;
        double side2 = points.get(first).y - points.get(second).y;
        return Math.sqrt(side1 * side1 + side2 * side2);
    }

    static class Edge implements Comparable<Edge>{
        int startEdgeNumber;
        int endEdgeNumber;
        double distance;

        Edge(int startEdgeNumber, int endEdgeNumber, double distance) {
            this.startEdgeNumber = startEdgeNumber;
            this.endEdgeNumber = endEdgeNumber;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.distance > o.distance) {
                return 1;
            } else {
                return this.distance < o.distance ? -1 : 0;
            }
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}
