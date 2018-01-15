//package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BracketsInCode {
    public static void main (String[] args) throws IOException {
        String string;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            string = br.readLine();
        }
        Deque<Bracket> stack = new ArrayDeque<>();
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (currentChar == '[' || currentChar == '{' || currentChar == '(') {
                stack.add(new Bracket(currentChar, i));
            } else if (currentChar == ']' || currentChar == '}' || currentChar == ')') {
                if (stack.isEmpty()) {
                    System.out.println(i + 1);
                    return;
                } else {
                    Bracket previousBracket = stack.removeLast();
                    if (previousBracket.value == '[' && currentChar != ']'
                            || previousBracket.value == '{' && currentChar != '}'
                            || previousBracket.value == '(' && currentChar != ')') {
                        System.out.println(i + 1);
                        return;
                    }
                }
            }
        }
        String result = stack.isEmpty() ? "Success" : Integer.toString(stack.pop().index + 1);
        System.out.println(result);
    }

    static class Bracket {
        char value;
        int index;

        public Bracket(char value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
