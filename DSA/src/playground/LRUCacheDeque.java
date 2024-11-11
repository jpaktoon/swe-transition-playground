package playground;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUCacheDeque {
    // store keys of cache
    private Deque<Integer> doublyQueue;

    // store references of key in cache, faster lookup
    private HashSet<Integer> hashSet;

    // maximum capacity of cache
    private final int CACHE_SIZE;

    LRUCacheDeque(int capacity)
    {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    /* Refer the page within the LRU cache */
    public void refer(int page)
    {
        if (!hashSet.contains(page)) { // new one - just for faster lookup - O(1)
            if (doublyQueue.size() == CACHE_SIZE) {
                int last = doublyQueue.removeLast(); // remove least one from queue - O(1)
                hashSet.remove(last); // remove it from set too - O(1)
            }
        }
        else { /* The found page may not be always the last
                element, even if it's an intermediate
                element that needs to be removed and added
                to the start of the Queue */
            doublyQueue.remove(page); // just remove it from queue - here is O(n)
        }
        doublyQueue.push(page); // recent refer on to the top of the queue
        hashSet.add(page);
    }

    // display contents of cache
    public void display()
    {
        Iterator<Integer> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
    }

    // Driver code
    public static void main(String[] args)
    {
        LRUCacheDeque cache = new LRUCacheDeque(4);
        cache.refer(1);
        cache.refer(2);
        cache.refer(3);
        cache.display(); // 3 2 1
        cache.refer(1);
        cache.display(); // 1 3 2
        cache.refer(4);
        cache.display(); // 4 1 3 2
        cache.refer(5);
        cache.display(); // 5 4 1 3 remove 2
    }
}
