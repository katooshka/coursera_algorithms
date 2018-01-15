package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Knapsack {

    public static void main(String[] args) throws IOException {
        int capacity;
        int[] items;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String firstString = br.readLine();
            capacity = Integer.parseInt(firstString.split(" ")[0]);
            items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(findMaxWeight(capacity, items));
    }

    private static int findMaxWeight(int capacity, int[] items) {
        int[][] table = new int[items.length + 1][capacity + 1];
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                int currentValue = table[i - 1][j];
                if (items[i - 1] <= j) {
                    int alternativeValue = table[i - 1][j - items[i - 1]] + items[i - 1];
                    if (alternativeValue > currentValue) {
                        currentValue = alternativeValue;
                    }
                }
                table[i][j] = currentValue;
            }
        }
        return table[table.length - 1][table[0].length - 1];
    }
}
