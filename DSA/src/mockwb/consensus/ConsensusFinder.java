package mockwb.consensus;

import java.util.*;

public class ConsensusFinder {

    public void consensus(List<CustomIterator<Integer>> streams, int numToAgree) {
        // PriorityQueue to track the iterators by their peeked values
        PriorityQueue<CustomIterator<Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(CustomIterator::peek));

        // Initialize the heap with the first value from each iterator
        for (CustomIterator<Integer> stream : streams) {
            if (stream.hasNext()) {
                minHeap.offer(stream);  // Add the iterator itself to the heap
            }
        }

        // Variables to track the current value and how many times we've seen it
        Integer currentValueToTrack = null;
        int iteratorCounter = 0;

        while (!minHeap.isEmpty()) {
            // Get the iterator with the smallest value
            CustomIterator<Integer> minIterator = minHeap.poll();
            int minValue = minIterator.peek();

            if (currentValueToTrack != null && minValue == currentValueToTrack) {
                // If the min value matches the current value we are tracking
                iteratorCounter += 1;
            } else {
                // If a new value is found, check if the old value should be output
                if (currentValueToTrack != null && iteratorCounter >= numToAgree) {
                    output(currentValueToTrack);
                }
                // Reset tracking to the new minimum value
                currentValueToTrack = minValue;
                iteratorCounter = 1;  // We found the new value in one stream so far
            }

            // iterate until all min value in the minIterator removed
            while (minIterator.hasNext() && minIterator.peek() == minValue) {
                minIterator.next();
            }
            if (minIterator.hasNext()) {
                minHeap.offer(minIterator);  // Reinsert the iterator with the new peeked value
            }
        }

        // Edge cases such as
//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));
//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));
//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));
        // Final check: if the last tracked value appears in enough iterators
        // This will not cause duplicate output
        // since if it was output, the inner loop will reset iteratorCounter to one
        if (currentValueToTrack != null && iteratorCounter >= numToAgree) {
            output(currentValueToTrack);
        }
    }

    // Mock output function
    private void output(int n) {
        System.out.println("Output: " + n);
    }

    public static void main(String[] args) {
        // Example usage: Create linked list iterators with CustomIterator
        List<CustomIterator<Integer>> streams = new ArrayList<>();
        streams.add(new CustomIterator<>(Arrays.asList(1, 1, 3, 3, 8, 34, 77, 120, 222).iterator()));
        streams.add(new CustomIterator<>(Arrays.asList(0, 1, 3, 5, 34, 35).iterator()));
        streams.add(new CustomIterator<>(Arrays.asList(0, 5, 34, 222, 222).iterator()));

//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));
//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));
//        streams.add(new CustomIterator<>(Arrays.asList(0).iterator()));

        ConsensusFinder finder = new ConsensusFinder();
        finder.consensus(streams, 2);  // Output numbers that appear in at least 2 streams
    }
}

