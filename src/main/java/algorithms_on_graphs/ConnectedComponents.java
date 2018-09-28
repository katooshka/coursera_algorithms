package algorithms_on_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {
    private static Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
    private static Map<Integer, Boolean> vertexVisited = new HashMap<>();
    private static Map<Integer, Integer> vertexGroupNumber = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int verticesNumber;
        int edgesNumber;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = br.readLine().split(" ");
            verticesNumber = Integer.parseInt(firstLine[0]);
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
        }
        System.out.println(checkPath(verticesNumber));
    }

    private static int checkPath(int verticesNumber) {
        int cc = 0;
        for (Integer vertex : adjacencyLists.keySet()) {
            if (!vertexVisited.get(vertex)) {
                explore(vertex, cc);
                cc++;
            }
        }
        return cc + (verticesNumber - adjacencyLists.size());
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