package data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        int operationsNumber;
        List<Operation> operations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            operationsNumber = Integer.parseInt(br.readLine());
            for (int i = 0; i < operationsNumber; i++) {
                String[] substrings = br.readLine().split(" ");
                OperationType operationType = OperationType.ADD;
                if (substrings[0].equals("del")) {
                    operationType = OperationType.DEL;
                } else if (substrings[0].equals("find")) {
                    operationType = OperationType.FIND;
                }
                if (operationType.equals(OperationType.ADD)) {
                    operations.add(new Operation(operationType, Integer.parseInt(substrings[1]), substrings[2]));
                } else {
                    operations.add(new Operation(operationType, Integer.parseInt(substrings[1])));
                }
            }
        }
        Map<Integer, String> phoneBook = new HashMap<>();
        for (Operation operation : operations) {
            if (operation.operationType.equals(OperationType.ADD)) {
                phoneBook.put(operation.number, operation.name);
            } else if (operation.operationType.equals(OperationType.FIND)) {
                System.out.println(phoneBook.getOrDefault(operation.number, "not found"));
            } else {
                if (phoneBook.containsKey(operation.number)) {
                    phoneBook.remove(operation.number);
                }
            }
        }
    }

    enum OperationType {
        ADD, DEL, FIND
    }

    static class Operation {
        public OperationType operationType;
        public int number;
        public String name;

        public Operation(OperationType operationType, int number) {
            this.operationType = operationType;
            this.number = number;
        }

        public Operation(OperationType operationType, int number, String name) {
            this.operationType = operationType;
            this.number = number;
            this.name = name;
        }
    }
}
