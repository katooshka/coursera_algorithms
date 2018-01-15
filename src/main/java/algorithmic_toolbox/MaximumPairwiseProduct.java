package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximumPairwiseProduct {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
            long max = Long.MIN_VALUE;
            long secondMax = Long.MIN_VALUE;
            String[] numbers = br.readLine().split(" ");
            for (int i = 0; i < numbers.length; i++) {
                long current = Long.parseLong(numbers[i]);
                if (current > max) {
                    secondMax = max;
                    max = current;
                } else if (current > secondMax) {
                    secondMax = current;
                }
            }
            System.out.println((max * secondMax));
        }
    }
}
