package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciHuge {
    public static void main(String[] args) throws IOException {
        long n;
        int m;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            n = Long.parseLong(numbers[0]);
            m = Integer.parseInt(numbers[1]);
        }
        int periodLength = findPisanoPeriodLength(m);
        System.out.println(findFibonacciModulo((int)(n % periodLength), m));
    }

    public static int findPisanoPeriodLength(int m) {
        int first = 1;
        int second = 1;

        int result = 2;

        while (true) {
            int next = (first + second) % m;

            if (next == 1 && second == 0) {
                break;
            }

            first = second;
            second = next;
            result++;
        }
        return result;
    }

    private static long findFibonacciModulo(int n, int m) {
        int first = 0;
        int second = 1;
        if (n == first || n == second) {
            return n;
        }
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (first + second) % m;
            first = second;
            second = result;
        }
        return result;
    }
}
