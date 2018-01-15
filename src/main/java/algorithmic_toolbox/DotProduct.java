package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DotProduct {
    public static void main(String[] args) throws IOException {
        List<Long> profitsPerClick = new ArrayList<>();
        List<Long> clicksPerAd = new ArrayList<>();
        int count;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine());
            String[] profitsAsStrings = br.readLine().split(" ");
            String[] clicksAsStrings = br.readLine().split(" ");
            for (String profitAsString : profitsAsStrings) {
                profitsPerClick.add(Long.parseLong(profitAsString));
            }
            for (String clickAsString : clicksAsStrings) {
                clicksPerAd.add(Long.parseLong(clickAsString));
            }
        }
        Collections.sort(profitsPerClick, Collections.<Long>reverseOrder());
        Collections.sort(clicksPerAd, Collections.<Long>reverseOrder());
        long result = 0;
        for (int i = 0; i < count; i++) {
            result += profitsPerClick.get(i) * clicksPerAd.get(i);
        }
        System.out.println(result);
    }
}
