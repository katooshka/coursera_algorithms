//package algorithms_on_strings;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TrieMatchingExtended {
//    public static void main(String[] args) throws IOException {
//        String text;
//        int patternNumber;
//        List<String> patterns = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            text = br.readLine();
//            patternNumber = Integer.parseInt(br.readLine());
//            for (int i = 0; i < patternNumber; i++) {
//                patterns.add(br.readLine());
//            }
//        }
//        Node root = createTrie(patterns);
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < text.length(); i++) {
//            boolean patternExists = checkPrefixTrieMatching(text.substring(i), root);
//            if (patternExists) {
//                result.add(i);
//            }
//        }
//        for (Integer integer : result) {
//            System.out.print(integer + " ");
//        }
//    }
//
//    private static boolean checkPrefixTrieMatching(String text, Node root) {
//        int index = 0;
//        char currentSymbol;
//        Node currentNode = root;
//        while (true) {
//            if (currentNode.outGoingNodes.isEmpty()) {
//                return index <= text.length();
//            } else if (!currentNode.outGoingNodes.isEmpty() && index < text.length()) {
//                boolean currentPatternMatches = false;
//                boolean isPatternEnd = false;
//                currentSymbol = text.charAt(index);
//                for (Node outGoingNode : currentNode.outGoingNodes) {
//                    if (outGoingNode.letter == currentSymbol) {
//                        index++;
//                        currentNode = outGoingNode;
//                        currentPatternMatches = true;
//                        isPatternEnd = currentNode.isPatternEnd;
//                        break;
//                    }
//                }
//                if (!currentPatternMatches) {
//                    return false;
//                }
//                if (isPatternEnd) {
//                    return true;
//                }
//            } else {
//                return false;
//            }
//        }
//    }
//
//    private static Node createTrie(List<String> patterns) {
//        int nodesCount = 0;
//        Node root = new Node('R', nodesCount, new ArrayList<Node>(), false);
//        for (String pattern : patterns) {
//            Node currentNode = root;
//            for (int i = 0; i < pattern.length(); i++) {
//                boolean letterExists = false;
//                for (Node outGoingNode : currentNode.outGoingNodes) {
//                    if (outGoingNode.letter.equals(pattern.charAt(i))) {
//                        currentNode = outGoingNode;
//                        letterExists = true;
//                        currentNode.isPatternEnd = true;
//                        break;
//                    }
//                }
//                if (!letterExists) {
//                    nodesCount++;
//                    currentNode.outGoingNodes.add(new Node(pattern.charAt(i), nodesCount, new ArrayList<Node>(), false));
//                    currentNode = currentNode.outGoingNodes.get(currentNode.outGoingNodes.size() - 1);
//                }
//            }
//        }
//        return root;
//    }
//
//    static class Node {
//        Character letter;
////        int number;
//        List<Node> outGoingNodes;
//        boolean isPatternEnd;
//
//        Node a = null;
//        Node g = null;
//        Node t = null;
//        Node цитозин = null;
//
//        public Node(Character letter, int number, List<Node> outGoingNodes, boolean isPatternEnd) {
//            this.letter = letter;
////            this.number = number;
//            this.outGoingNodes = outGoingNodes;
//            this.isPatternEnd = isPatternEnd;
//        }
//
//        void setNode(char ch, Node node) {
//
//        }
//
//        Node getNode(char ch) {
//
//        }
//    }
//}
