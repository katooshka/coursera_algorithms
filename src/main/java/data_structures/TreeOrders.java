package data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TreeOrders {

    static StringBuilder inOrderSb = new StringBuilder();
    static StringBuilder preOrderSb = new StringBuilder();
    static StringBuilder postOrderSb = new StringBuilder();

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
        Node root = processNode(entries);
        traverseTreeInOrder(root);
        System.out.println(inOrderSb.toString());
        traverseTreePreOrder(root);
        System.out.println(preOrderSb.toString());
        traverseTreePostOrder(root);
        System.out.println(postOrderSb.toString());
    }

    public static void processNodeRec(Entry[] entries, Node node, int leftIndex, int rightIndex) {
        if (leftIndex == -1 && rightIndex == -1) {
            return;
        }
        if (leftIndex != -1) {
            node.left = new Node(entries[leftIndex].value);
            processNodeRec(entries, node.left, entries[leftIndex].indexLeft, entries[leftIndex].indexRight);
        }
        if (rightIndex != -1) {
            node.right = new Node(entries[rightIndex].value);
            processNodeRec(entries, node.right, entries[rightIndex].indexLeft, entries[rightIndex].indexRight);
        }
    }

    public static Node processNode(Entry[] entries) {
        Node[] nodes = new Node[entries.length];
        for (int i = 0; i < entries.length; i++) {
            nodes[i] = new Node(entries[i].value);
        }
        for (int i = 0; i < entries.length; i++) {
            if (entries[i].indexLeft != -1) nodes[i].left = nodes[entries[i].indexLeft];
            if (entries[i].indexRight != -1) nodes[i].right = nodes[entries[i].indexRight];
        }
        return nodes[0];
    }

    public static void traverseTreeInOrder(Node node) {
        List<Frame> queue = new ArrayList<>();
        queue.add(new Frame(Status.NOT_STARTED, node));
        while (!queue.isEmpty()) {
            Frame lastFrame = queue.get(queue.size() - 1);
            switch (lastFrame.status) {
                case NOT_STARTED: {
                    if (lastFrame.node == null) {
                        queue.remove(queue.size() - 1);
                        break;
                    }
                    lastFrame.status = Status.LEFT_CHILD_ADDED;
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.left));
                    break;
                }
                case LEFT_CHILD_ADDED: {
                    inOrderSb.append(lastFrame.node.value).append(" ");
                    lastFrame.status = Status.NODE_PRINTED;
                    break;
                }
                case NODE_PRINTED: {
                    lastFrame.status = Status.RIGHT_CHILD_ADDED;
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.right));
                    break;
                }
                case RIGHT_CHILD_ADDED: {
                    queue.remove(queue.size() - 1);
                }
            }
        }
    }

    public static void traverseTreePreOrder(Node node) {
        List<Frame> queue = new ArrayList<>();
        queue.add(new Frame(Status.NOT_STARTED, node));
        while (!queue.isEmpty()) {
            Frame lastFrame = queue.get(queue.size() - 1);
            switch (lastFrame.status) {
                case NOT_STARTED: {
                    if (lastFrame.node == null) {
                        queue.remove(queue.size() - 1);
                        break;
                    }
                    preOrderSb.append(lastFrame.node.value).append(" ");
                    lastFrame.status = Status.NODE_PRINTED;
                    break;
                }
                case NODE_PRINTED: {
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.left));
                    lastFrame.status = Status.LEFT_CHILD_ADDED;
                    break;
                }
                case LEFT_CHILD_ADDED: {
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.right));
                    lastFrame.status = Status.RIGHT_CHILD_ADDED;
                    break;
                }
                case RIGHT_CHILD_ADDED: {
                    queue.remove(queue.size() - 1);
                }
            }
        }
    }

    public static void traverseTreePostOrder(Node node) {
        List<Frame> queue = new ArrayList<>();
        queue.add(new Frame(Status.NOT_STARTED, node));
        while (!queue.isEmpty()) {
            Frame lastFrame = queue.get(queue.size() - 1);
            switch (lastFrame.status) {
                case NOT_STARTED:
                    if (lastFrame.node == null) {
                        queue.remove(queue.size() - 1);
                        break;
                    }
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.left));
                    lastFrame.status = Status.LEFT_CHILD_ADDED;
                    break;
                case LEFT_CHILD_ADDED: {
                    queue.add(new Frame(Status.NOT_STARTED, lastFrame.node.right));
                    lastFrame.status = Status.RIGHT_CHILD_ADDED;
                    break;
                }
                case RIGHT_CHILD_ADDED: {
                    postOrderSb.append(lastFrame.node.value).append(" ");
                    lastFrame.status = Status.NODE_PRINTED;
                    break;
                }
                case NODE_PRINTED: {
                    queue.remove(queue.size() - 1);
                }
            }
        }
    }

    public static void traverseTreeInOrderRec(Node node) {
        if (node == null) {
            return;
        }
        traverseTreeInOrderRec(node.left);
        inOrderSb.append(node.value).append(" ");
        traverseTreeInOrderRec(node.right);
    }


    public static void traverseTreePreOrderRec(Node node) {
        if (node == null) {
            return;
        }
        preOrderSb.append(node.value).append(" ");
        traverseTreePreOrderRec(node.left);
        traverseTreePreOrderRec(node.right);
    }

    public static void traverseTreePostOrderRec(Node node) {
        if (node == null) {
            return;
        }
        traverseTreePostOrderRec(node.left);
        traverseTreePostOrderRec(node.right);
        postOrderSb.append(node.value).append(" ");
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

        public Node(int value) {
            this.value = value;
            this.right = null;
            this.left = null;
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

    static class Frame {
        Status status;
        Node node;

        public Frame(Status status, Node node) {
            this.status = status;
            this.node = node;
        }

        @Override
        public String toString() {
            return "Frame{" +
                    "status=" + status +
                    ", node=" + node +
                    '}';
        }
    }

    enum Status {
        NOT_STARTED,
        LEFT_CHILD_ADDED,
        NODE_PRINTED,
        RIGHT_CHILD_ADDED
    }
}