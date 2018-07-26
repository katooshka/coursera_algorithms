//package data_structures;\

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IsBstHard {
    public static void main(String[] args) throws IOException {
        Entry[] entries;
        int nodesCount;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            nodesCount = Integer.parseInt(br.readLine());
            entries = new Entry[nodesCount];
            for (int i = 0; i < nodesCount; i++) {
                String[] line = br.readLine().split(" ");
                entries[i] = new Entry(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            }
        }
        if (nodesCount == 0) {
            System.out.println("CORRECT");
            return;
        }
        Node root = processNode(entries);
        if (root == null) {
            System.out.println("INCORRECT");
            return;
        }
        List<List<Node>> nodes = traverseNodes(root);
        for (int i = nodes.size() - 1; i >= 0; i--) {
            for (Node node : nodes.get(i)) {
                if (node.left != null && node.value <= node.left.maxValue || node.right != null && node.value > node.right.minValue) {
                    System.out.println("INCORRECT");
                    return;
                }
                if (node.left == null) {
                    node.minValue = node.value;
                } else {
                    node.minValue = node.left.minValue;
                }
                if (node.right == null) {
                    node.maxValue = node.value;
                } else {
                    node.maxValue = node.right.maxValue;
                }
            }
        }
        System.out.println("CORRECT");
    }

    public static Node processNode(Entry[] entries) {
        Node[] nodes = new Node[entries.length];
        for (int i = 0; i < entries.length; i++) {
            nodes[i] = new Node(entries[i].value);
        }
        for (int i = 0; i < entries.length; i++) {
            if (entries[i].indexLeft != -1) {
                nodes[i].left = nodes[entries[i].indexLeft];
//                Node parent = nodes[entries[i].indexLeft].parent;
//                if (parent != null) {
//                    return null;
//                }
                nodes[entries[i].indexLeft].parent = nodes[i];
            }
            if (entries[i].indexRight != -1) {
                nodes[i].right = nodes[entries[i].indexRight];
//                Node parent = nodes[entries[i].indexLeft].parent;
//                if (parent != null) {
//                    return null;
//                }
                nodes[entries[i].indexRight].parent = nodes[i];
            }
        }
        return nodes[0];
    }

    private static List<List<Node>> traverseNodes(Node root) {
        List<List<Node>> nodes = new ArrayList<>();
        List<Node> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            nodes.add(currentLevel);
            List<Node> nextLevel = new ArrayList<>();
            for (Node node : currentLevel) {
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            currentLevel = nextLevel;
        }
        return nodes;
    }

    static class Entry {
        int value;
        int indexLeft;
        int indexRight;

        public Entry(int value, int indexLeft, int indexRight) {
            this.value = value;
            this.indexLeft = indexLeft;
            this.indexRight = indexRight;
        }
    }

    static class Node {
        int value;
        Node right;
        Node left;
        Node parent;
        int maxValue;
        int minValue;

        public Node(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
            this.parent = null;
            this.maxValue = Integer.MIN_VALUE;
            this.minValue = Integer.MAX_VALUE;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", right=" + right +
                    ", left=" + left +
                    '}';
        }
    }
}
