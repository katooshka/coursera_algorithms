package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangingMoney {

    public static void main(String[] args) throws IOException {
        int m;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            m = Integer.parseInt(br.readLine());
        }
        int result = m / 10 + (m % 10) / 5 + (m % 10) % 5;
        System.out.println(result);
    }
}
