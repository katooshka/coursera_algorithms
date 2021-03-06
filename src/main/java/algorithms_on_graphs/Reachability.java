package algorithms_on_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Reachability {
    private static Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
    private static Map<Integer, Boolean> vertexVisited = new HashMap<>();
    private static Map<Integer, Integer> vertexGroupNumber = new HashMap<>();

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
                vertexVisited.put(firstVertex, false);
                vertexGroupNumber.put(firstVertex, 0);
                int secondVertex = Integer.parseInt(edgeString[1]);
                vertexVisited.put(secondVertex, false);
                vertexGroupNumber.put(secondVertex, 0);
                updateAdjacencyLists(firstVertex, secondVertex);
                updateAdjacencyLists(secondVertex, firstVertex);
            }
            String[] lastLine = br.readLine().split(" ");
            u = Integer.parseInt(lastLine[0]);
            v = Integer.parseInt(lastLine[1]);
        }
        System.out.println(checkPath(u, v));
    }

    private static int checkPath(int u, int v) {
        int cc = 1;
        for (Integer vertex : adjacencyLists.keySet()) {
            if (!vertexVisited.get(vertex)) {
                explore(vertex, cc);
                cc++;
            }
        }
        if (!vertexGroupNumber.containsKey(u) || !vertexGroupNumber.containsKey(v)) return 0;
        return vertexGroupNumber.get(u).equals(vertexGroupNumber.get(v)) ? 1 : 0;
    }

    private static void explore(int vertex, int cc) {
        vertexVisited.put(vertex, true);
        vertexGroupNumber.put(vertex, cc);
        for (Integer neighbour : adjacencyLists.get(vertex)) {
            if (!vertexVisited.get(neighbour)) {
                explore(neighbour, cc);
            }
        }
    }

    private static void updateAdjacencyLists(int key, int value) {
        if (!adjacencyLists.containsKey(key)) {
            adjacencyLists.put(key, new HashSet<>());
        }
        adjacencyLists.get(key).add(value);
    }
}
