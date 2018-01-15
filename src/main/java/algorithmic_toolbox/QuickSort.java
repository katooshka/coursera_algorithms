package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

import static java.util.stream.Collectors.joining;

public class QuickSort {

    public static void main(String[] args) throws IOException {
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Random random = new Random();
        sort(numbers, 0, numbers.length, random);
        System.out.println(
                Arrays.stream(numbers)
                        .mapToObj(Integer::toString)
                        .collect(joining(" ")));
    }

    private static void sort(int[] numbers, int start, int end, Random random) {
        while (end - start > 1) {
            Pointers pointers = partition(numbers, start, end, random);
            int left = pointers.left;
            int right = pointers.right;
            if (left - start < end - right) {
                sort(numbers, start, left, random);
                start = right;
            } else {
                sort(numbers, right, end, random);
                end = left;
            }
        }
    }

    private static Pointers partition(int[] numbers, int start, int end, Random random){
        int pivot = numbers[random.nextInt((end - start)) + start];
        int l = start;
        int m = start;
        int r = end - 1;
        while (m <= r) {
            if (numbers[m] < pivot) {
                swap(numbers, m, l);
                m++;
                l++;
            } else if (numbers[m] > pivot) {
                swap(numbers, m, r);
                r--;
            } else {
                m++;
            }
        }
        return new Pointers(l, m);
    }

    private static void swap(int[] numbers, int x, int y) {
        int temp = numbers[x];
        numbers[x] = numbers[y];
        numbers[y] = temp;
    }

    private static class Pointers {
        private int left;
        private int right;

        private Pointers(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

}
