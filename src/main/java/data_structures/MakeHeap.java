package data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MakeHeap {

    public static void main(String[] args) throws IOException {
        int size;
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            size = Integer.parseInt(br.readLine());
            numbers = new int[size + 1];
            String[] numbersAsStrings = br.readLine().split(" ");
            for (int i = 0; i < numbersAsStrings.length; i++) {
                numbers[i + 1] = Integer.parseInt(numbersAsStrings[i]);
            }
        }
        List<SwappedElements> result = new ArrayList<>();

        for (int i = size / 2; i >= 1; i--) {
            siftDown(i, numbers, result);
        }
        System.out.println(result.size());
        for (SwappedElements swappedElements : result) {
            System.out.println(swappedElements.i + " " + swappedElements.j);
        }
    }

    private static void siftDown(int index, int[] array, List<SwappedElements> result) {
       while (left(index) < array.length) {
           int indexOfMinChild = left(index);
           if (right(index) < array.length && array[right(index)] < array[left(index)]) {
               indexOfMinChild = right(index);
           }
           swap(array, index, indexOfMinChild);
           result.add(new SwappedElements( index - 1, indexOfMinChild - 1));
           index = indexOfMinChild;
       }
    }

    private static int left(int i) {
        return i * 2;
    }

    private static int right(int i) {
        return i * 2 + 1;
    }

    private static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    static class SwappedElements {
        public int i;
        public int j;

        public SwappedElements(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
