//package algorithms_on_graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int[] dist = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            dist[i] = 10000;
        }
        dist[0] = 0;
        for (int i = 0; i < adj.length; i++) {
            boolean distChanged = false;
            for (int j = 0; j < adj.length; j++) {
                List<Integer> outgoingNodes = adj[j];
                for (int k = 0; k < outgoingNodes.size(); k++) {
                    if (dist[outgoingNodes.get(k)] > dist[j] + cost[j].get(k)) {
                        dist[outgoingNodes.get(k)] = dist[j] + cost[j].get(k);
                        distChanged = true;
                    }
                }
            }
            if (!distChanged) {
                return 0;
            }
            if (i == adj.length - 1) {
                return distChanged ? 1 : 0;
            }
        }
        return 0;
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
        System.out.println(negativeCycle(adj, cost));
    }
}

