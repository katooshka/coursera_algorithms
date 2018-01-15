package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ThreeHeight {
    public static void main (String[] args) throws IOException {
        int count;
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine());
            String[] numbersAsStrings = br.readLine().split(" ");
            numbers = new int[count];
            for (int i = 0; i < count; i++) {
                numbers[i] = Integer.parseInt(numbersAsStrings[i]);
            }
        }

        Node[] tree = new Node[count];
        int rootIndex = 0;
        for (int i = 0; i < count; i++) {
            if (numbers[i] == -1) {
                rootIndex = i;
            } else {
                if (tree[numbers[i]] == null) {
                    tree[numbers[i]] = new Node();
                }
                tree[numbers[i]].addChild(i);
            }
        }
        int height = 0;
        Deque<Integer> parentsQueue = new ArrayDeque<>();
        Deque<Integer> childrenQueue = new ArrayDeque<>();
        parentsQueue.add(rootIndex);
        while (!parentsQueue.isEmpty()) {
            while (!parentsQueue.isEmpty()) {
                for (Integer nodeIndex : parentsQueue) {
                    if (tree[nodeIndex]!= null) {
                        childrenQueue.addAll(tree[nodeIndex].children);
                    }
                    parentsQueue.pop();
                }
                height++;
            }
            parentsQueue = childrenQueue;
            childrenQueue = new ArrayDeque<>();
        }
        System.out.println(height);
    }

    static class Node {
        private List<Integer> children = new ArrayList<>();

        public void addChild(int index) {
            children.add(index);
        }
    }
}
