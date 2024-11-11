package mockwb;

class HitCounterV1 {
    // Number of seconds in 5 minutes (300 seconds)
    private static final int WINDOW_SIZE = 300;

    // An array to store the cumulative number of hits at each second
    private final int[] hits;
    // An array to store timestamps corresponding to each slot
    private final int[] times;
    // The current total number of hits in the system
    private int totalHits;
    private int lastestTimestamp;

    // Constructor
    public HitCounterV1() {
        hits = new int[WINDOW_SIZE + 1]; // ignore index 0
        times = new int[WINDOW_SIZE + 1]; // ignore index 0
        totalHits = 0;
        lastestTimestamp = 0;
    }

    // Record a hit.
    public void hit(int timestamp) {
        int index = timestamp % WINDOW_SIZE;
        if (index == 0) index = WINDOW_SIZE; // [1...WINDOW_SIZE]

//        if (timestamp > lastestTimestamp + 300) totalHits = 1;
//        else totalHits++;
        totalHits++;
        // Increment the total number of hits
        lastestTimestamp = timestamp;
        // If the timestamp in the current slot is outdated, reset it
        if (times[index] != timestamp) {
            // Update the time at this index to the current timestamp
            times[index] = timestamp;
            // Store the cumulative total of hits up to this point in the window
            hits[index] = totalHits;
        }
    }

    // Return the number of hits in the past 5 minutes.
    public int getHits(int timestamp) {

        if (timestamp > lastestTimestamp + 300) {
            return 0; // outbound
        }

        if (timestamp <= WINDOW_SIZE) {
            return totalHits;
        }

        int oldIndex = (timestamp - WINDOW_SIZE) % WINDOW_SIZE;
        if (oldIndex == 0) oldIndex = WINDOW_SIZE; // [1...WINDOW_SIZE]

        if (times[oldIndex] != timestamp - WINDOW_SIZE) {
            while (oldIndex > 1) {
                if (times[oldIndex] != 0 && times[oldIndex] < timestamp - WINDOW_SIZE) break;
                oldIndex--;
            }
        }

        // Otherwise, return totalHits minus hits from 300 seconds ago
        //System.out.println("totalHits: " + totalHits + ", time at oldIndex: " + times[oldIndex] + ", hit: " + hits[oldIndex]);
        return totalHits - hits[oldIndex];
    }

    public static void main(String[] args) {
        HitCounterV1 counter = new HitCounterV1();

        // Perform the test
        counter.hit(49);  // hit at 5 sec
        counter.hit(100);  // hit at 100 sec
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
