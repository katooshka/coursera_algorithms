package data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindSubstring {
    public static void main(String[] args) throws IOException {
        String pattern;
        String string;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            pattern = br.readLine();
            string = br.readLine();
        }
        List<Integer> result = new ArrayList<>();
        int p = 500009;
        int x = 2;
//        Random random = new Random();
//        int x = random.nextInt(p - 1) + 1;
        int patternHash = getPolyHash(pattern, p, x);
        int[] precomputedHashes = precomputeHashes(string, pattern.length(), p, x);
        for (int i = 0; i < string.length() - pattern.length() + 1; i++) {
            if (patternHash != precomputedHashes[i]) {
                continue;
            }
            if (areEqual(string.substring(i, i + pattern.length()), pattern)) {
                result.add(i);
            }
        }
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }

    private static boolean areEqual(String substring, String pattern) {
        if (substring.length() != pattern.length()){
            return false;
        }
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static int getPolyHash(String pattern, int p, int x) {
        int result = 0;
        int y = 1;
        for (int i = 0; i < pattern.length(); i++) {
//            result = (result + pattern.charAt(i) * x) % p;
//            result = (int)((((result + pattern.charAt(i) * Math.pow(x, i)  % p) + p) % p));
            result = ((((result + pattern.charAt(i) * y) % p) + p) % p);
            y *= x;
            y %= p;
        }
        return result;
    }

    private static int[] precomputeHashes(String string, int patternLength, int p, int x) {
        int[] result = new int[string.length() - patternLength + 1];
        String substring = string.substring(string.length() - patternLength, string.length());
        result[string.length() - patternLength] = getPolyHash(substring, p, x);
        int y = 1;
        for (int i = 0; i < patternLength; i++) {
            y = (y * x) % p;
        }
        for (int i = string.length() - patternLength - 1; i >= 0; i--) {
//            result[i] = (x * result[i + 1] + string.charAt(i) - y * string.charAt(i + patternLength)) % p;
                result[i] = (((x * result[i + 1] + string.charAt(i) - y * string.charAt(i + patternLength)) % p) + p) % p;
        }
        return result;
    }
}
