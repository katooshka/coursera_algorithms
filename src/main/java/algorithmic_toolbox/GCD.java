package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.*;

public class GCD {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            int first = Integer.parseInt(numbers[0]);
            int second = Integer.parseInt(numbers[1]);
            int max = max(first, second);
            int min = min(first, second);
            int result = -1;
            int remainder = -1;
            while (remainder != 0) {
                remainder = max % min;
                result = min;
                max = min;
                min = remainder;
            }
            System.out.println(result);
        }
    }
}
