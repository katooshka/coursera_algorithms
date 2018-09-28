package algorithms_on_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BFS {
    private static Map<Integer, Integer> distances = new HashMap<>();
    private static Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
    private static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int edgesNumber;
        int u;
        int v;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = br.readLine().split(" ");
            edgesNumber = Integer.parseInt(firstLine[1]);
            for (int i = 0; i < edgesNumber; i++) {
                String[] edgeString = br.readLine().split(" ");
                int firstVertex = Integer.parseInt(edgeString[0]);
                int secondVertex = Integer.parseInt(edgeString[1]);
                distances.put(firstVertex, Integer.MAX_VALUE);
                distances.put(secondVertex, Integer.MAX_VALUE);
                updateAdjacencyLists(firstVertex, secondVertex);
                updateAdjacencyLists(secondVertex, firstVertex);
            }
            String[] edgeString = br.readLine().split(" ");
            u = Integer.parseInt(edgeString[0]);
            v = Integer.parseInt(edgeString[1]);
        }
        if (u == v) {
            System.out.println(0);
            return;
        }
        distances.put(u, 0);
        queue.add(u);
        while (!queue.isEmpty()) {
            int edgeStart = queue.pop();
            for (Integer edgeEnd : adjacencyLists.get(edgeStart)) {
                int currentDistanceIncremented = distances.get(edgeStart) + 1;
                if (currentDistanceIncremented < distances.get(edgeEnd)) {
                    queue.add(edgeEnd);
                    distances.put(edgeEnd, currentDistanceIncremented);
                }
            }
        }
        boolean vertexIsUnreachable = distances.get(v) == null || distances.get(v) == Integer.MAX_VALUE;
        int resultDistance = distances.get(v);
        System.out.println(vertexIsUnreachable ? -1 : resultDistance);
    }

    private static void updateAdjacencyLists(int key, int value) {
        if (!adjacencyLists.containsKey(key)) {
            adjacencyLists.put(key, new HashSet<>());
        }
        adjacencyLists.get(key).add(value);
    }
}
