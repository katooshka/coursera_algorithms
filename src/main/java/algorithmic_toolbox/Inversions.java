package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Inversions {

    private long inversionsCount = 0;

    public static void main(String[] args) throws IOException {
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(new Inversions().inversionsCount(numbers));
    }

    private long inversionsCount(int[] numbers) {
        findInversions(numbers);
        return inversionsCount;
    }

    private int[] findInversions(int[] numbers) {
        if (numbers.length == 1) {
            return numbers;
        }
        int[] first = findInversions(Arrays.copyOfRange(numbers, 0, numbers.length / 2));
        int[] second = findInversions(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length));
        return merge(first, second);
    }

    private int[] merge(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int r = 0;
        int f = 0;
        int s = 0;
        while (f < first.length && s < second.length) {
            if (first[f] <= second[s]) {
                result[r] = first[f];
                f++;
            } else {
                result[r] = second[s];
                for (int i = f; i < first.length; i++) {
                    if (first[f] > second[s]) {
                        inversionsCount++;
                    }
                }
                s++;
            }
            r++;
        }
        if (f >= first.length) {
            for (int i = r; i < result.length; i++) {
                result[i] = second[s];
                s++;
            }
        } else {
            for (int i = r; i < result.length; i++) {
                result[i] = first[f];
                f++;
            }
        }
        return result;
    }
}
