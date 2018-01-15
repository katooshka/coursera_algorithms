//package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FractionalKnapsack {
    public static void main(String[] args) throws IOException {
        int itemsNumber;
        int capacity;
        List<Double> valuesPerUnit = new ArrayList<>();
        Map<Double, Double> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] numbers = br.readLine().split(" ");
            itemsNumber = Integer.parseInt(numbers[0]);
            capacity = Integer.parseInt(numbers[1]);
            for (int i = 0; i < itemsNumber; i++) {
                String[] itemValues = br.readLine().split(" ");
                double value = Double.parseDouble(itemValues[0]);
                double weight = Double.parseDouble(itemValues[1]);
                map.put(value / weight, weight);
                valuesPerUnit.add(value / weight);
            }
        }
        Collections.sort(valuesPerUnit, Collections.<Double>reverseOrder());
        double resultValue = 0;
        int spaceLeft = capacity;
        for (Double valuePerUnit : valuesPerUnit) {
            if (spaceLeft != 0) {
                Double itemWeight = map.get(valuePerUnit);
                if (itemWeight <= spaceLeft) {
                    resultValue += valuePerUnit * itemWeight;
                    spaceLeft -= itemWeight;
                } else {
                    resultValue += valuePerUnit * spaceLeft;
                    System.out.println(resultValue);
                    return;
                }
            } else {
                System.out.println(resultValue);
                return;
            }
        }
        System.out.println(resultValue);
    }
}
