package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MajorityElementNComplexity {
    public static void main(String[] args) throws IOException {
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(findMajorElement(numbers));
    }

    public static int findMajorElement(int[] numbers) {
        int element = numbers[0];
        int count = 1;
        for (int i = 1; i < numbers.length - 1; i++) {
            if (numbers[i] == element) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                element = numbers[i + 1];
            }
        }
        count = 0;
        for (int number : numbers) {
            if (number == element) {
                count++;
            }
        }
        return count >= numbers.length / 2 + 1 ? 1 : 0;
    }
}
