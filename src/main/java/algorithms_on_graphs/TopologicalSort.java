package algorithms_on_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSort {
    private static Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
    private static Map<Integer, VertexColor> status = new HashMap<>();
    private static List<Integer> topologicalOrdering = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int verticesNumber;
        int edgesNumber;
        boolean[] vertices;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = br.readLine().split(" ");
            verticesNumber = Integer.parseInt(firstLine[0]);
            vertices = new boolean[verticesNumber + 1];
            edgesNumber = Integer.parseInt(firstLine[1]);
            for (int i = 0; i < edgesNumber; i++) {
                String[] edgeString = br.readLine().split(" ");
                int firstVertex = Integer.parseInt(edgeString[0]);
                int secondVertex = Integer.parseInt(edgeString[1]);
                vertices[firstVertex] = true;
                vertices[secondVertex] = true;
                status.put(firstVertex, VertexColor.WHITE);
                status.put(secondVertex, VertexColor.WHITE);
                updateAdjacencyLists(firstVertex, secondVertex);
            }
        }
        checkPath();
        Collections.reverse(topologicalOrdering);
        for (int i = 1; i < vertices.length; i++) {
            if (!vertices[i]) {
                topologicalOrdering.add(i);
            }
        }
        for (Integer integer : topologicalOrdering) {
            System.out.print(integer + " ");
        }
    }

    private static void checkPath() {
        for (Integer vertex : adjacencyLists.keySet()) {
            if (status.get(vertex) == VertexColor.WHITE) {
                explore(vertex);
            }
        }
    }

    private static void explore(int vertex) {
        status.put(vertex, VertexColor.GREY);
        if (adjacencyLists.containsKey(vertex)) {
            for (Integer neighbour : adjacencyLists.get(vertex)) {
                if (status.get(neighbour) == VertexColor.WHITE) {
                    explore(neighbour);
                }
            }
        }
        topologicalOrdering.add(vertex);
    }

    private static void updateAdjacencyLists(int key, int value) {
        if (!adjacencyLists.containsKey(key)) {
            adjacencyLists.put(key, new HashSet<>());
        }
        adjacencyLists.get(key).add(value);
    }

    enum VertexColor {
        WHITE,
        GREY
    }
}
