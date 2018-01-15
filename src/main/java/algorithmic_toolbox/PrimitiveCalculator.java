package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntUnaryOperator;

public class PrimitiveCalculator {

    public static void main(String[] args) throws IOException {
        int number;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            number = Integer.parseInt(br.readLine().split(" ")[0]);
        }
        List<Integer> operations = calculate(number);
        System.out.println(operations.size() - 1);
        for (Integer operation : operations) {
            System.out.print(operation + " ");
        }
    }

    private static List<Integer> calculate(int number) {
        int[] minSteps = new int[number + 1];
        minSteps[1] = 0;
        Operation[] operations = new Operation[number + 1];
        for (int i = 2; i < minSteps.length; i++) {
            minSteps[i] = Integer.MAX_VALUE;
            int multiplyByThreeSteps = Integer.MAX_VALUE;
            int multiplyByTwoSteps = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                multiplyByThreeSteps = minSteps[i / 3] + 1;
            }
            if (i % 2 == 0) {
                multiplyByTwoSteps = minSteps[i / 2] + 1;
            }
            int minCurrentSteps = minSteps[i - 1] + 1;
            if (multiplyByThreeSteps < minCurrentSteps) {
                minCurrentSteps = multiplyByThreeSteps;
                if (multiplyByTwoSteps < minCurrentSteps) {
                    minCurrentSteps = multiplyByTwoSteps;
                    operations[i] = Operation.MULT_TWO;
                } else {
                    operations[i] = Operation.MULT_TREE;
                }
            } else if (multiplyByTwoSteps < minCurrentSteps) {
                minCurrentSteps = multiplyByTwoSteps;
                operations[i] = Operation.MULT_TWO;
            } else {
                operations[i] = Operation.PLUS_ONE;
            }
            minSteps[i] = minCurrentSteps;
        }
        List<Integer> steps = new ArrayList<>();
        steps.add(number);
        for (int i = number; i >= 2;) {
            steps.add(operations[i].getReverseOperation().applyAsInt(i));
            i = operations[i].getReverseOperation().applyAsInt(i);
        }
//        steps.add(1);
        Collections.reverse(steps);
        return steps;
    }
}

enum Operation {
    // public static Operation PLUS_ONE = new Operation(x -> x - 1);
    PLUS_ONE(x -> x - 1),
    MULT_TWO(x -> x / 2),
    MULT_TREE(x -> x / 3);

    private final IntUnaryOperator reverseOperation;

    Operation(IntUnaryOperator reverseOperation) {
        this.reverseOperation = reverseOperation;
    }

    public IntUnaryOperator getReverseOperation() {
        return reverseOperation;
    }
}