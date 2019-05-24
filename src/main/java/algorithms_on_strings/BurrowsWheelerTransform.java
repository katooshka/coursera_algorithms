package algorithms_on_strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
    }

    String BWT(String text) {
            StringBuilder result = new StringBuilder();
            List<ShiftedString> shiftedStrings = new ArrayList<>();
            for (int i = 0; i < text.length(); i++) {
                shiftedStrings.add(new ShiftedString(text, i));
            }
            Collections.sort(shiftedStrings);
            for (ShiftedString shiftedString : shiftedStrings) {
                int index = shiftedString.shift - 1;
                if (index == -1 ) {
                    index = shiftedString.string.length() - 1;
                }
                result.append(shiftedString.string.charAt(index));
            }
            return result.toString();
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }

    class ShiftedString implements Comparable<ShiftedString>{
        int shift = 0;
        String string = null;

        public ShiftedString(String string, int shift) {
            this.shift = shift;
            this.string = string;
        }

        @Override
        public int compareTo(ShiftedString o) {
            int count = 0;
            for (int i = this.shift, j = o.shift; count < o.string.length(); i++, j++) {
                if (i == o.string.length()) {
                    i = 0;
                }
                if (j == o.string.length()) {
                    j = 0;
                }
                if (this.string.charAt(i) < o.string.charAt(j)) {
                    return -1;
                } else if (this.string.charAt(i) > o.string.charAt(j)) {
                    return 1;
                }
                count++;
            }
            return 0;
        }
    }
}
