package algorithms_on_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Acyclicity {
    private static Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
    private static Map<Integer, VertexColor> status = new HashMap<>();
    private static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        int edgesNumber;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] firstLine = br.readLine().split(" ");
            edgesNumber = Integer.parseInt(firstLine[1]);
            for (int i = 0; i < edgesNumber; i++) {
                String[] edgeString = br.readLine().split(" ");
                int firstVertex = Integer.parseInt(edgeString[0]);
                int secondVertex = Integer.parseInt(edgeString[1]);
                status.put(firstVertex, VertexColor.WHITE);
                status.put(secondVertex, VertexColor.WHITE);
                updateAdjacencyLists(firstVertex, secondVertex);
            }
        }
        checkPath();
        System.out.println(hasCycle ? 1 : 0);
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
                if (status.get(neighbour) == VertexColor.GREY) {
                    hasCycle = true;
                }
            }
        }
        status.put(vertex, VertexColor.BLACK);
    }

    private static void updateAdjacencyLists(int key, int value) {
        if (!adjacencyLists.containsKey(key)) {
            adjacencyLists.put(key, new HashSet<>());
        }
        adjacencyLists.get(key).add(value);
    }

    enum VertexColor {
        WHITE,
        GREY,
        BLACK
    }
}
