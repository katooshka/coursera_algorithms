package algorithms_on_graphs;

import java.util.*;

public class BFS2 {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        if (s == t) {
            return 0;
        }
        int[] dist = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int current = queue.pop();
            for (Integer endVertex : adj[current]) {
                if (dist[endVertex] == Integer.MAX_VALUE) {
                    queue.add(endVertex);
                    dist[endVertex] = dist[current] + 1;
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}