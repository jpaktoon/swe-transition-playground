package mockwb;

import java.util.LinkedList;
import java.util.Queue;

class HitCounter {

    // A queue to store hits as (timestamp, count) pairs.
    private Queue<int[]> hitsQueue;

    // Constructor
    public HitCounter() {
        hitsQueue = new LinkedList<>();
    }

    // Record a hit.
    // timestamp: the current timestamp (in seconds granularity).
    public void hit(int timestamp) {
        // If the queue is not empty and the last hit has the same timestamp, increment its count
        if (!hitsQueue.isEmpty() && hitsQueue.peek()[0] == timestamp) {
            hitsQueue.peek()[1]++;
        } else {
            // Add the new hit with a count of 1
            hitsQueue.add(new int[]{timestamp, 1});
        }
    }

    // Return the number of hits in the past 5 minutes.
    // timestamp: the current timestamp (in seconds granularity).
    public int getHits(int timestamp) {
        // Remove all hits that are older than 300 seconds from the current timestamp
        while (!hitsQueue.isEmpty() && hitsQueue.peek()[0] <= timestamp - 300) {
            hitsQueue.poll();
        }

        // Sum up all remaining hits
        int totalHits = 0;
        for (int[] hit : hitsQueue) {
            totalHits += hit[1];
        }

        return totalHits;
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        // Perform the test
        counter.hit(49);
        counter.hit(49);
        counter.hit(49);
        counter.hit(100);
        System.out.println("101:" +counter.getHits(101));
        counter.hit(149);
        System.out.println("347:" +counter.getHits(347));
        System.out.println("348:" +counter.getHits(348));
        System.out.println("349:" +counter.getHits(349));
        System.out.println("350:" +counter.getHits(350));
        System.out.println("351:" +counter.getHits(351));
        counter.hit(448);
        System.out.println("599:" +counter.getHits(599));
        System.out.println("1350:" +counter.getHits(1350));
        counter.hit(1449);
        System.out.println("1500:" +counter.getHits(1500));
    }
}
