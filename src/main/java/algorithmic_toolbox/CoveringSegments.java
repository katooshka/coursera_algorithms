package algorithmic_toolbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.parseLong;

public class CoveringSegments {
    public static void main(String[] args) throws IOException {
        int count;
        List<Segment> segments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                String[] numbers = br.readLine().split(" ");
                segments.add(new Segment(parseLong(numbers[0]), parseLong(numbers[1])));
            }
        }
        Collections.sort(segments);
        int visits = 0;
        List<Long> coordinates = new ArrayList<>();
        long minCurrentEnd = MAX_VALUE;
        for (Segment segment : segments) {
            if (segment.start <= minCurrentEnd) {
                minCurrentEnd = Math.min(minCurrentEnd, segment.end);
            } else {
                visits++;
                coordinates.add(minCurrentEnd);
            }
        }
    }

    static class Segment implements Comparable<Segment> {
        private Long start;
        private Long end;

        public Long getStart() {
            return start;
        }

        public Long getEnd() {
            return end;
        }

        public Segment(Long start, Long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return start.compareTo(o.start);
        }
    }
}
