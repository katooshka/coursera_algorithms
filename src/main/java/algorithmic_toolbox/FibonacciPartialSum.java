//package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciPartialSum {

    public static void main(String[] args) throws IOException {
        long n;
        long m;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            m = Long.parseLong(numbers[0]);
            n = Long.parseLong(numbers[1]);
        }
            int lowerSum = findSumLastDigit(m - 1);
            int upperSum = findSumLastDigit(n);
            System.out.println(((upperSum - lowerSum) + 10) % 10);
    }

    public static int findSumLastDigit(long n) {
        int periodLength = findPisanoPeriodLength(10);
        return (int) ((n / periodLength * findSumOfFibonacciByModule(periodLength)
                + findSumOfFibonacciByModule((int) (n % periodLength))) % 10);
    }

    private static int findPisanoPeriodLength(int m) {
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

    public static int findSumOfFibonacciByModule(int n) {
        int first = 0;
        int second = 1;
        if (n == first || n == second) {
            return n;
        }
        int sum = 1;
        int next;
        for (int i = 2; i <= n; i++) {
            next = (first + second) % 10;
            first = second;
            second = next;
            sum += next;
        }
        return sum % 10;
    }
}
