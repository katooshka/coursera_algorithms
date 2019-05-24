package algorithms_on_strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InverseBWT {
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

    String inverseBWT(String bwt) {
        StringBuilder result = new StringBuilder();
        List<IndexedString> lastIndexedString = countOccurrences(bwt);

        char[] bwtArray = bwt.toCharArray();
        Arrays.sort(bwtArray);
        StringBuilder firstString = new StringBuilder();
        for (char bwtChar : bwtArray) {
            firstString.append(bwtChar);
        }
        List<IndexedString> firstIndexedString = countOccurrences(firstString.toString());

        Map<IndexedString, IndexedString> map = new HashMap<>();
        for (int i = 0; i < bwt.length(); i++) {
            map.put(firstIndexedString.get(i), lastIndexedString.get(i));
        }

        IndexedString currentString = firstIndexedString.get(0);
        result.append(currentString.letter);
        for (int i = 0; i < bwt.length() - 1; i++) {
            IndexedString currentInsertion = map.get(currentString);
            result.append(currentInsertion.letter);
            currentString = currentInsertion;
        }
        return result.reverse().toString();
    }

    private List<IndexedString> countOccurrences(String bwt) {
        Map<Character, Integer> occurrences = new HashMap<>();
        List<IndexedString> result = new ArrayList<>();
        for (int i = 0; i < bwt.length(); i++) {
            char letter = bwt.charAt(i);
            int occurrence;
            if (occurrences.get(letter) == null) {
                occurrences.put(letter, 0);
                occurrence = 0;
            } else {
                occurrence = occurrences.get(letter) + 1;
                occurrences.put(letter, occurrence);
            }
            result.add(new IndexedString(letter, occurrence));
        }
        return result;
    }

    private String convertToString(IndexedString indexedString) {
        return "" + indexedString.letter + Integer.toString(indexedString.occurrence);
    }

    static public void main(String[] args) throws IOException {
        new InverseBWT().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String bwt = scanner.next();
        System.out.println(inverseBWT(bwt));
    }

    class IndexedString {
        char letter;
        int occurrence;

        public IndexedString(char letter, int occurrence) {
            this.letter = letter;
            this.occurrence = occurrence;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IndexedString that = (IndexedString) o;

            if (letter != that.letter) return false;
            return occurrence == that.occurrence;
        }

        @Override
        public int hashCode() {
            int result = (int) letter;
            result = 31 * result + occurrence;
            return result;
        }
    }
}
