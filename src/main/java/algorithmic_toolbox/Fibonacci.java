package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int first = 0;
            int second = 1;
            if (n == first || n == second) {
                System.out.println(n);
                return;
            }
            int result = 0;
            for (int i = 2; i <= n; i++) {
                result = first + second;
                first = second;
                second = result;
            }
            System.out.println(result);
        }
    }
}
