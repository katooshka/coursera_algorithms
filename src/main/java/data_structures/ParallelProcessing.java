package data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParallelProcessing {

    public static void main(String[] args) throws IOException {
        int processesCount;
        int tasksCount;
        int[] taskLengths;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = br.readLine().split(" ");
            processesCount = Integer.parseInt(input[0]);
            tasksCount = Integer.parseInt(input[1]);
            taskLengths = new int[tasksCount];
            String[] tasksString = br.readLine().split(" ");
            for (int i = 0; i < tasksString.length; i++) {
                taskLengths[i] = Integer.parseInt(tasksString[i]);
            }
        }
        List<Process> heap = new ArrayList<>();
        int i;
        int processNumber = 0;
        for (i = 0; processNumber < processesCount && i < taskLengths.length; i++) {
            System.out.println(processNumber + " " + 0);
            if (taskLengths[i] != 0) {
                processNumber++;
                startNewProcess(heap, new Process(i, 0, taskLengths[i]));
            }
        }

        while (!heap.isEmpty()) {
            Process currentProcess = heap.get(0);
            removeCompletedProcess(heap);
            if (i < tasksCount) {
                startNewProcess(heap, new Process(currentProcess.processNumber, currentProcess.newStartTime, taskLengths[i]));
                System.out.println(currentProcess.processNumber + " " + currentProcess.newStartTime);
            }
            i++;
        }
    }

    private static void startNewProcess(List<Process> heap, Process process) {
        heap.add(process);
        for (int i = heap.size() - 1; i > 0; i = parent(i)) {
            Process parent = heap.get(parent(i));
            Process child = heap.get(i);
            if (compare(parent, child) == PriorityOption.SECOND_IS_BETTER) {
                swap(heap, i, parent(i));
            }
        }
    }

    private static void removeCompletedProcess(List<Process> heap) {
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        int i = 0;
        while (left(i) < heap.size()) {
            int minChildIndex = left(i);
            Process left = heap.get(left(i));
            if (right(i) < heap.size()) {
                Process right = heap.get(right(i));
                if (compare(left, right) == PriorityOption.SECOND_IS_BETTER) {
                    minChildIndex = right(i);
                }
            }
            if (compare(heap.get(i), heap.get(minChildIndex)) == PriorityOption.SECOND_IS_BETTER) {
                swap(heap, i, minChildIndex);
                i = minChildIndex;
            } else {
                return;
            }
        }
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static int right(int i) {
        return i * 2 + 2;
    }

    private static int left(int i) {
        return i * 2 + 1;
    }

    private static PriorityOption compare(Process p1, Process p2) {
        return p1.newStartTime > p2.newStartTime || p1.newStartTime == p2.newStartTime && p1.processNumber > p2.processNumber ?
                PriorityOption.SECOND_IS_BETTER :
                PriorityOption.FIRST_IS_BETTER;
    }

    private static void swap(List<Process> heap, int currentProcessIndex, int minChildIndex) {
        Process tmp = heap.get(currentProcessIndex);
        heap.set(currentProcessIndex, heap.get(minChildIndex));
        heap.set(minChildIndex, tmp);

    }

    enum PriorityOption {
        FIRST_IS_BETTER, SECOND_IS_BETTER
    }

    static class Process {
        int processNumber;
        long startTime;
        long taskCompletionTime;
        long newStartTime;

        public Process(int processNumber, long startTime, long taskCompletionTime) {
            this.processNumber = processNumber;
            this.startTime = startTime;
            this.taskCompletionTime = taskCompletionTime;
            this.newStartTime = (this.startTime + this.taskCompletionTime);
        }
    }
}
