package playground.LRUCacheLinkedHashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class AccessCountCache<K, V> extends LinkedHashMap<K, V> {
    private final Map<K, Integer> accessCountMap = new HashMap<>();
    private final int maxAccessCount;

    public AccessCountCache(int maxAccessCount) {
        super(16, 0.75f, true);  // true for access-order
        this.maxAccessCount = maxAccessCount;
    }

    @Override
    public V get(Object key) {
        V value = super.get(key);
        if (value != null) {
            accessCountMap.put((K) key, accessCountMap.getOrDefault(key, 0) + 1);
        }
        return value;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        K key = eldest.getKey();
        // Remove if access count exceeds max allowed
        return accessCountMap.getOrDefault(key, 0) > maxAccessCount;
    }

    public static void main(String[] args) {
        AccessCountCache<Integer, String> cache = new AccessCountCache<>(3);  // Max 3 accesses per entry

        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println("After put: " + cache);
        cache.get(1);  // Access entry 1
        cache.get(1);  // Access entry 1
        cache.get(1);  // Access entry 1
        cache.get(1);  // Access entry 1
        cache.get(2);  // Access entry 2
        System.out.println("After access 4 times: " + cache);
        // Entry 1 has been accessed 3 times, so it should be removed
        cache.put(3, "C");
        System.out.println("After put new key: " + cache); // Should only print entries 2 and 3
    }
}
