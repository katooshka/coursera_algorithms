package algorithms_on_graphs;

import java.util.*;

public class Dijkstra {

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        if (s == t) {
            return 0;
        }
        int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        List<Node> queue = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            queue.add(new Node(i, Integer.MAX_VALUE));
        }
        dist[s] = 0;
        queue.set(s, new Node(s, 0));
        while (!queue.isEmpty()) {
            int minIndex = getMinIndex(queue);
            int currentNodeStart = queue.get(minIndex).number;
            queue.remove(minIndex);
            List<Integer> currentNodeEnds = adj[currentNodeStart];
            for (int i = 0; i < currentNodeEnds.size(); i++) {
                if (dist[currentNodeEnds.get(i)] > dist[currentNodeStart] + cost[currentNodeStart].get(i)) {
                    dist[currentNodeEnds.get(i)] = dist[currentNodeStart] + cost[currentNodeStart].get(i);
                    prev[currentNodeEnds.get(i)] = currentNodeStart;
                    changePriority(queue, currentNodeEnds.get(i), dist[currentNodeEnds.get(i)]);
                }
            }
        }
        int currentPath = t;
        while (currentPath != s) {
            if (prev[currentPath] == -1) {
                return -1;
            }
            currentPath = prev[currentPath];
        }
        return dist[t];
    }

    private static void changePriority(List<Node> queue, Integer currentNode, int newDistance) {
        for (Node node : queue) {
            if (node.number == currentNode) {
                node.distValue = newDistance;
                return;
            }
        }
    }

    private static int getMinIndex(List<Node> list) {
        int currentMinIndex = 0;
        int currentMin = list.get(0).distValue;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).distValue < currentMin) {
                currentMin = list.get(i).distValue;
                currentMinIndex = i;
            }
        }
        return currentMinIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
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

    static class Node {
        int number;
        int distValue;

        public Node(int number, int distValue) {
            this.number = number;
            this.distValue = distValue;
        }
    }
}
