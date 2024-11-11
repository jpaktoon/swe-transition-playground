package mockwb;

import java.util.*;

public class ConsensusFinderV2 {

    public void consensus(List<Iterator<Integer>> streams, int numToAgree) {
        // Step 1: Initialize a list to store the current head of each iterator
        List<Integer> currentValues = new ArrayList<>(streams.size());
        for (Iterator<Integer> stream : streams) {
            if (stream.hasNext()) {
                currentValues.add(stream.next()); // 1, 0, 0
            } else {
                currentValues.add(null); // If the iterator is empty, set null
            }
        }

        while (true) {
            // Step 2: Find the smallest non-null value
            Integer minValue = null;
            for (Integer value : currentValues) {
                if (value != null && (minValue == null || value < minValue)) {
                    minValue = value;
                }
            }

            // If no more elements in any stream, break the loop
            if (minValue == null) {
                break;
            }

            // Step 3: Count how many streams have this smallest value
            int count = 0;
            for (Integer value : currentValues) {
                if (minValue.equals(value)) {
                    count++;
                }
            }

            // Step 4: If the count meets or exceeds numToAgree, output the value
            if (count >= numToAgree) {
                output(minValue);
            }

            // Step 5: Advance the iterators that had the smallest value
            for (int i = 0; i < streams.size(); i++) {
                if (minValue.equals(currentValues.get(i))) {
                    Iterator<Integer> iterator = streams.get(i);
                    if (iterator.hasNext()) {
                        currentValues.set(i, iterator.next());
                    } else {
                        currentValues.set(i, null); // No more elements in this iterator
                    }
                }
            }
        }
    }

    // Mock output function
    private void output(int n) {
        System.out.println("Output: " + n);
    }

    public static void main(String[] args) {
        // Example usage: Create linked list iterators
        List<Iterator<Integer>> streams = new ArrayList<>();
        streams.add(Arrays.asList(1, 1, 3, 8, 34).iterator());
        streams.add(Arrays.asList(0, 1, 5, 34, 35).iterator());
        streams.add(Arrays.asList(0, 5, 34, 222, 222).iterator());

        ConsensusFinderV2 finder = new ConsensusFinderV2();
        finder.consensus(streams, 2); // Output numbers that appear in at least 2 streams
    }
}
