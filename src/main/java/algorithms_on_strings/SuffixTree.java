//package algorithms_on_strings;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class SuffixTree {
//    class FastScanner {
//        StringTokenizer tok = new StringTokenizer("");
//        BufferedReader in;
//
//        FastScanner() {
//            in = new BufferedReader(new InputStreamReader(System.in));
//        }
//
//        String next() throws IOException {
//            while (!tok.hasMoreElements())
//                tok = new StringTokenizer(in.readLine());
//            return tok.nextToken();
//        }
//    }
//
//    // Build a suffix tree of the string text and return a list
//    // with all of the labels of its edges (the corresponding
//    // substrings of the text) in any order.
//    public List<String> computeSuffixTreeEdges(String text) {
//        int textLength = text.length();
//        List<String> result = new ArrayList<>();
//        Node root = new Node(0, 0, new ArrayList<Node>());
//        for (int i = 0; i < text.length(); i++) {
//            int currentStart = i;
//            Node currentNode = root;
//            for (int j = 0; j < currentNode.outGoingNodes.size(); j++) {
//                Node outgoingNode = currentNode.outGoingNodes.get(0);
//                int start = outgoingNode.start;
//                int length = outgoingNode.length;
//                String substring = text.substring(start, start + length);
//                for (int j = currentStart + 1; j < textLength; j++) {
//                    if (!substring.equals(text.substring(currentStart, j))) {
//                        currentNode.outGoingNodes.add(new Node(currentStart, textLength, new ArrayList<Node>()));
//                        break;
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//
//
//    static public void main(String[] args) throws IOException {
//        new SuffixTree().run();
//    }
//
//    public void print(List<String> x) {
//        for (String a : x) {
//            System.out.println(a);
//        }
//    }
//
//    public void run() throws IOException {
//        FastScanner scanner = new FastScanner();
//        String text = scanner.next();
//        List<String> edges = computeSuffixTreeEdges(text);
//        print(edges);
//    }
//
//    static class Node {
//        int start;
//        int length;
//        List<Node> outGoingNodes;
//
//        public Node(int start, int length, List<Node> outGoingNodes) {
//            this.start = start;
//            this.length = length;
//            this.outGoingNodes = outGoingNodes;
//        }
//    }
//}