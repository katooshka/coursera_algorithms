package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LCM {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            long first = Long.parseLong(numbers[0]);
            long second = Long.parseLong(numbers[1]);
            long gcd = gcd(first, second);
            System.out.println((first / gcd * second));
        }
    }

    private static long gcd(long first, long second) {
        long max = max(first, second);
        long min = min(first, second);
        long result = -1;
        long remainder = -1;
        while (remainder != 0) {
            remainder = max % min;
            result = min;
            max = min;
            min = remainder;
        }
        return result;
    }
}