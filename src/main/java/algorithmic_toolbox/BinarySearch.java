package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class BinarySearch {
    public static void main(String[] args) throws IOException {
        int[] sequence;
        int[] numbersToFind;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).skip(1).toArray();
            numbersToFind = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).skip(1).toArray();
        }
        System.out.println(
                Arrays.stream(numbersToFind)
                        .map(x -> findElement(sequence, x))
                        .mapToObj(Integer::toString)
                        .collect(joining(" ")));
    }

    private static int findElement(int[] sequence, int number) {
        int left = -1;
        int right = sequence.length;
        while (right - left > 1) {
            int middle = left + (right - left) / 2;
            if (sequence[middle] >= number) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return (right < sequence.length && sequence[right] == number) ? right : -1;
    }
}

