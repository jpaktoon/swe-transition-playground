package playground;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueCheatSheet {
    public static void main(String[] args) {

        /*
         ***********
         * Queue : LinkedList - add() and poll() / remove() and peek()
         * ********
         */
        Queue<String> queueLinkedList = new LinkedList<>();

        // add elements to the queue
        queueLinkedList.add("apple"); // add(item) -> linkLast(item)
        queueLinkedList.add("banana");
        queueLinkedList.add("cherry");

        // print the queue
        System.out.println("Queue: " + queueLinkedList); // [apple, banana, cherry]

        // remove the element at the front of the queue
        String front = queueLinkedList.remove(); // remove() -> removeFirst() -> unlinkFirst() , poll() -> unlinkFirst()
        System.out.println("Removed element: " + front); // apple

        // print the updated queue
        System.out.println("Queue after removal: " + queueLinkedList); // [banana, cherry]

        // add another element to the queue
        queueLinkedList.add("date");

        // peek at the element at the front of the queue
        String peeked = queueLinkedList.peek();
        System.out.println("Peeked element: " + peeked); // banana

        // print the updated queue
        System.out.println("Queue after peek: " + queueLinkedList); // [banana, cherry, date]

        System.out.println("Size of queueLinkedList-"+ queueLinkedList.size()); // 3

        /*
         ***********
         * Queue : PriorityQueue - add() and poll() / remove() and peek()
         * ********
         */
        Queue<Integer> pQueue = new PriorityQueue<>(); // Creating empty priority queue

        // Adding items to the pQueue using add()
        pQueue.add(10); // O(log n)
        pQueue.add(20);
        pQueue.add(15);
        pQueue.add(15);

        // Printing the top element of PriorityQueue
        System.out.println(pQueue.peek()); // 10

        // For each
        for (Integer integer : pQueue) {
            System.out.print(integer + " "); // 10 15 15 20
        }
        System.out.println();

        // Printing the top element and removing it
        // from the PriorityQueue container
        System.out.println("poll: " +pQueue.poll()); // 10 - O(log n)

        // For each
        for (Integer integer : pQueue) {
            System.out.print(integer + " "); // 15 20 15 *** be careful, it is Heap so the elements is not sorted.
        }
        System.out.println();

        // Printing the top element again
        System.out.println("peek after poll: " + pQueue.peek()); // 15
        System.out.println("remove: " + pQueue.remove()); // 15
        System.out.println("peek after remove: " + pQueue.peek()); // 15

        // For each
        for (Integer integer : pQueue) {
            System.out.print(integer + " "); // 15 20
        }
        System.out.println();

        // Or use Iterator
        Iterator iterator = pQueue.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // 15 20
        }
        System.out.println();

        /*
         ***********
         * Deque : ArrayDeque - add() and poll() / remove() and peek()
         * ********
        */
        Deque<String> xmenQueue = new ArrayDeque<>();
        xmenQueue.add("Wolverine"); // O(1) First in & first out
        xmenQueue.add("Juggernaut"); // O(1)
        xmenQueue.add("Xavier"); // O(1)
        xmenQueue.add("Beast"); // O(1) Last in & last out

        int queueSize = xmenQueue.size();
        for (int i = 0; i < queueSize; i++) {
            System.out.print(xmenQueue.poll() + " "); // Wolverine Juggernaut Xavier Beast
        }
        System.out.println();

        xmenQueue.add("Wolverine"); // O(1)
        xmenQueue.addFirst("Cyclops"); // O(1)
        xmenQueue.addLast("Xavier"); // O(1)

        queueSize = xmenQueue.size();
        for (int i = 0; i < queueSize; i++) { // O(n)
            System.out.print(xmenQueue.poll() + " "); // Cyclops Wolverine Xavier - O(1)
        }
        System.out.println();

        xmenQueue.add("Wolverine"); // O(1)
        xmenQueue.addFirst("Cyclops"); // O(1)
        xmenQueue.addLast("Xavier"); // O(1)

        System.out.println("Removing first: " + xmenQueue.removeFirst()); // Cyclops
        System.out.println("Removing last: " + xmenQueue.removeLast()); // Xavier
    }
}
