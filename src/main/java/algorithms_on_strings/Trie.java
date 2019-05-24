package algorithms_on_strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int patternNumber;
        List<String> patterns = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            patternNumber = Integer.parseInt(br.readLine());
            for (int i = 0; i < patternNumber; i++) {
                patterns.add(br.readLine());
            }
        }
        int nodesCount = 0;
        Node root = new Node('R', nodesCount, new ArrayList<Node>());
        for (String pattern : patterns) {
            Node currentNode = root;
            for (int i = 0; i < pattern.length(); i++) {
                boolean letterExists = false;
                for (Node outGoingNode : currentNode.outGoingNodes) {
                    if (outGoingNode.letter.equals(pattern.charAt(i))) {
                        currentNode = outGoingNode;
                        letterExists = true;
                        break;
                    }
                }
                if (!letterExists) {
                    nodesCount++;
                    currentNode.outGoingNodes.add(new Node(pattern.charAt(i), nodesCount, new ArrayList<Node>()));
                    currentNode = currentNode.outGoingNodes.get(currentNode.outGoingNodes.size() - 1);
                }
            }
        }
        traverseTrie(root, -1);
        for (String r : result) {
            System.out.println(r);
        }
    }

    private static void traverseTrie(Node node, int previousNumber) {
        if (node.letter != 'R') {
            result.add(previousNumber + "->" + node.number + ':' + node.letter);
        }
        if (!node.outGoingNodes.isEmpty()) {
            for (Node outGoingNode : node.outGoingNodes) {
                traverseTrie(outGoingNode, node.number);
            }
        }

    }

    static class Node {
        Character letter;
        int number;
        List<Node> outGoingNodes;

        public Node(Character letter, int number, List<Node> outGoingNodes) {
            this.letter = letter;
            this.number = number;
            this.outGoingNodes = outGoingNodes;
        }
    }
}
