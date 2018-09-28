package algorithms_on_graphs;

import java.util.*;

public class Dijkstra2 {

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            int currentNodeStart = currentNode.number;
            List<Integer> currentNodeEnds = adj[currentNodeStart];
            for (int i = 0; i < currentNodeEnds.size(); i++) {
                if (dist[currentNodeEnds.get(i)] > dist[currentNodeStart] + cost[currentNodeStart].get(i)) {
                    dist[currentNodeEnds.get(i)] = dist[currentNodeStart] + cost[currentNodeStart].get(i);
                    queue.add(new Node(currentNodeEnds.get(i), dist[currentNodeEnds.get(i)]));
                }
            }
        }
        if (dist[t] == Integer.MAX_VALUE) {
            return -1;
        }
        return dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }

    static class Node implements Comparable<Node> {
        int number;
        int distValue;

        Node(int number, int distValue) {
            this.number = number;
            this.distValue = distValue;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distValue > o.distValue) {
                return 1;
            } else if (this.distValue < o.distValue) {
                return -1;
            }
            return 0;
        }
    }
}
