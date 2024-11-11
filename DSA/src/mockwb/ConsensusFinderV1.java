package mockwb;

import java.util.*;

public class ConsensusFinderV1 {

    public void consensus(List<Iterator<Integer>> streams, int numToAgree) {

        for (Iterator<Integer> stream : streams) {
            if (stream.hasNext()) {
                System.out.println(stream.next());
            }
        }

        for (Iterator<Integer> stream : streams) {
            if (stream.hasNext()) {
                System.out.println(stream.next());
            }
        }
//        // Step 1: Initialize the current values list to simulate 'peek'
//        List<Integer> currentValues = new ArrayList<>(streams.size());
//
//        // Priority Queue (Min-Heap) to track the index of the iterator with the smallest current value
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(currentValues::get));
//
//        for (Iterator<Integer> stream : streams) {
//            if (stream.hasNext()) {
//                currentValues.add(stream.next());
//                minHeap.offer(currentValues.size() - 1); // Add index to heap
//            } else {
//                currentValues.add(null); // If stream is empty, store null
//            }
//        }
//
//        while (!minHeap.isEmpty()) {
//            int currentIndex = minHeap.peek(); // Get the index with the smallest value
//            int currentValue = currentValues.get(currentIndex); // Get the smallest value
//            int count = 0;
//
//            // Step 2: Collect all streams that have the same current value
//            List<Integer> sameValueIndexes = new ArrayList<>();
//            while (!minHeap.isEmpty() && currentValues.get(minHeap.peek()).equals(currentValue)) {
//                int index = minHeap.poll();
//                sameValueIndexes.add(index);
//                count++;
//            }
//
//            // Step 3: Output the value if it appears in at least numToAgree streams
//            if (count >= numToAgree) {
//                output(currentValue);
//            }
//
//            // Step 4: Move the iterators that had the current value to their next element
//            for (int index : sameValueIndexes) {
//                Iterator<Integer> iterator = streams.get(index);
//                if (iterator.hasNext()) {
//                    currentValues.set(index, iterator.next()); // Update the current value
//                    minHeap.offer(index); // Re-add the index to the heap
//                } else {
//                    currentValues.set(index, null); // No more elements, set to null
//                }
//            }
//        }
    }

    // Mock output function
    private void output(int n) {
        System.out.println("Output: " + n);
    }

    public static void main(String[] args) {
        // Example usage: Create LinkedLists
        List<Iterator<Integer>> streams = new ArrayList<>();
        streams.add(Arrays.asList(1, 1, 3, 8, 34, 34).iterator());
        streams.add(Arrays.asList(0, 0, 1, 5, 34, 35).iterator());
        streams.add(Arrays.asList(0, 5, 34, 222, 222).iterator());

        ConsensusFinderV1 finder = new ConsensusFinderV1();
        finder.consensus(streams, 2); // Output numbers that appear in at least 2 streams
    }
}
