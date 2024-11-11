package playground.LRUCacheLinkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

class ExpiringCache<K, V> extends LinkedHashMap<K, V> {
    private long expirationTime; // Time in milliseconds after which entries should expire

    public ExpiringCache(long expirationTime) {
        super(16, 0.75f, true);  // true for access-order
        this.expirationTime = expirationTime;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Custom logic for expiration
        long currentTime = System.currentTimeMillis();
        long entryTime = (long) eldest.getValue();  // Assuming value contains the timestamp

        // Remove if the entry is older than expiration time
        return currentTime - entryTime > expirationTime;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiringCache<Integer, Long> cache = new ExpiringCache<>(5000);  // Entries expire after 5 seconds

        cache.put(1, System.currentTimeMillis());
        System.out.println("cache after put key 1: " + cache);
        Thread.sleep(2000);  // Sleep for 2 seconds
        System.out.println("cache after 2 secs.: " + cache);
        cache.put(2, System.currentTimeMillis());
        System.out.println("cache after put key 2: " + cache);
        Thread.sleep(4000);  // Sleep for 4 seconds
        // Entry 1 should be expired by now since it's older than 5 seconds
        System.out.println("cache after 2 + 4 secs.: " + cache);  // Should only print entry 2 !! No the 1 is still be there
        // the removeEldestEntry will be triggered when the new entry addaed
        cache.put(3, System.currentTimeMillis());
        System.out.println("cache after put key 3: " + cache); // Now only 2 and 3
    }
}
