package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MajorityElementNlogNComplexity {

    public static void main(String[] args) throws IOException {
        int[] numbers;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.readLine();
            numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(returnResult(findMajorElement(numbers, 0, numbers.length)));
    }

    public static int returnResult(int majorElement) {
        return majorElement == -1 ? 0 : 1;
    }

    public static int findMajorElement(int[] numbers, int start, int end) {
        if (end - start == 1) {
            return numbers[start];
        }
        int firstPartMajorElement = findMajorElement(numbers, start, start + (end - start) / 2);
        int secondPartMajorElement = findMajorElement(numbers, start + (end - start) / 2, end);
        int firstCount = 0;
        int secondCount = 0;
        for (int i = start; i < end; i++) {
            if (numbers[i] == firstPartMajorElement) {
                firstCount++;
            }
            if (numbers[i] == secondPartMajorElement) {
                secondCount++;
            }
        }
        if (firstCount >= (end - start) / 2 + 1) {
            return firstPartMajorElement;
        } else if(secondCount >= (end - start) / 2 + 1) {
            return secondPartMajorElement;
        } return -1;
    }

//    public static int findMajorElement(int[] numbers) {
//        Arrays.sort(numbers);
//        int length = numbers.length;
//        int middle = length / 2;
//        int middleElement = numbers[middle];
//        int i = 0;
//        while (i < numbers.length && numbers[i] != middleElement) {
//            i++;
//        }
//        if (i == numbers.length || i + middle >= numbers.length) {
//            return 0;
//        }
//        if (numbers[i + middle] == middleElement) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }
}
