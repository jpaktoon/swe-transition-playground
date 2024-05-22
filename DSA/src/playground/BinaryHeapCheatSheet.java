package playground;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BinaryHeapCheatSheet {
    public static class BinaryHeap {
        int arr[];
        int sizeOfTree;

        public BinaryHeap(int size) {
            arr = new int[size + 1]; // skip index 0, arr[1] is the root node
            sizeOfTree = 0;
        }

        public boolean isEmpty() {
            return sizeOfTree == 0;
        }

        public Integer peek() {
            if (!isEmpty()) {
                return arr[1];
            }
            return null;
        }

        public int sizeOfBP() {
            return sizeOfTree;
        }

        public void levelOrder() {
            System.out.print("The current Heap is ");
            // we are not going to use the Zero index, and only the existing nodes
            for (int i = 1; i <= sizeOfTree; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        private void swap(int indexA, int indexB){
            int temp = arr[indexA];
            arr[indexA] = arr[indexB];
            arr[indexB] = temp;
        }

        // Heapify Bottom to Top using recursive
        public void recursiveHeapifyBottomToTop(int index, String heapType) {
            int parent = index / 2;
            if (index <= 1) { // at root
                return;
            }
            if (heapType.equals("Min")) {
                if (arr[index] < arr[parent]) {
                    swap(index, parent);
                } else {
                    return; // fast return, no need to go until root
                }
            } else if (heapType.equals("Max")) {
                if (arr[index] > arr[parent]) {
                    swap(index, parent);
                } else {
                    return; // fast return, no need to go until root
                }
            }

            recursiveHeapifyBottomToTop(parent, heapType); // time O (log n), space O (log n)
        }

        // Heapify Bottom to Top  using iterative
        public void iterativeHeapifyBottomToTop(String heapType) {
            int index = sizeOfTree; // end of tree
            while (index > 0) { // time O (log n), space O (1)
                int parent = index / 2;
                if (parent != 0 && // ignore index 0
                        ((heapType.equals("Min") && arr[index] < arr[parent]) ||
                        (heapType.equals("Max") && arr[index] > arr[parent]))) {
                    swap(index, parent);
                }
                index = parent;
            }
        }

        public boolean isFull() {
            return sizeOfTree + 1 >= arr.length;
        }

        public void insertUsingRecursive(int value, String heapType) {
            if (!isFull()) {
                insert(value);
                recursiveHeapifyBottomToTop(sizeOfTree, heapType);
            }
        }

        public void insertUsingIterative(int value, String heapType) {
            if (!isFull()) {
                insert(value);
                iterativeHeapifyBottomToTop(heapType);
            }
        }

        private void insert(int value) {
            arr[sizeOfTree + 1] = value;
            sizeOfTree++;
        }

        // Heapify Top to Bottom using recursive
        public void recursiveHeapifyTopToBottom(int index, String heapType) {

            int left = index * 2;
            int right = index * 2 + 1;

            // Assumimg it is a binary Heap - completed binary tree
            int child;
            // leaf node, no child. Since it is binary Heap, if there is a child it MUST be left first.
            if (sizeOfTree < left) {
                return;
            }

            if (sizeOfTree == left) {
                // only one left child
                child = left;
            } else {
                // two children
                child = heapType.equals("Min") ? minIndex(left, right) : maxIndex(left, right);
            }

            if (heapType.equals("Min")) {
                if (arr[index] > arr[child]) {
                    swap(index, child);
                } else {
                    return; // fast return, no need to go until leaf
                }
            } else if (heapType.equals("Max")) {
                if (arr[index] < arr[child]) {
                    swap(index, child);
                } else {
                    return; // fast return, no need to go until leaf
                }
            }

            recursiveHeapifyTopToBottom(child, heapType);
        }

        // Heapify Top to Bottom using iterative
        public void iterativeHeapifyTopToBottom(String heapType) {
            int index = 1;
            while (index < sizeOfTree) {
                int left = index * 2;
                int right = index * 2 + 1;

                // Assumimg it is a binary Heap - completed binary tree
                int child;
                // leaf node, no child. Since it is binary Heap, if there is a child it MUST be left first.
                if (sizeOfTree < left) {
                    break;
                }

                if (sizeOfTree == left) {
                    // only one left child
                    child = left;
                } else {
                    // two children
                    child = heapType.equals("Min") ? minIndex(left, right) : maxIndex(left, right);
                }

                if (heapType.equals("Min")) {
                    if (arr[index] > arr[child]) {
                        swap(index, child);
                    }
                } else if (heapType.equals("Max")) {
                    if (arr[index] < arr[child]) {
                        swap(index, child);
                    }
                }

                index = child;
            }
        }

        public int extractHeadOfBPUsingRecursive(String heapType) {
            if (isEmpty()) {
                return -1;
            } else {
                int extractedValue = arr[1]; // root
                arr[1] = arr[sizeOfTree];
                sizeOfTree--;
                recursiveHeapifyTopToBottom(1, heapType);
                return extractedValue;
            }
        }

        public int extractHeadOfBPUsingIterative(String heapType) {
            if (isEmpty()) {
                return -1;
            } else {
                int extractedValue = arr[1]; // root
                arr[1] = arr[sizeOfTree];
                sizeOfTree--;
                iterativeHeapifyTopToBottom(heapType);
                return extractedValue;
            }
        }

        private int minIndex (int left, int right) {
            return arr[left] < arr[right] ? left : right;
        }

        private int maxIndex (int left, int right) {
            return arr[left] > arr[right] ? left : right;
        }

    }

    public static void heapSort(int[] arr) {
        // 1) convert to heap
        BinaryHeap maxHeap = new BinaryHeap(arr.length + 1);
        for (int num : arr) {
            maxHeap.insertUsingIterative(num, "Max"); // O (log n)
        }

        // 2) Delete (extract) the root element, set reversed order
        for (int i = arr.length - 1; i >= 0 ; i--) { // O (n)
            arr[i] = maxHeap.extractHeadOfBPUsingIterative("Max"); // O (log n)
        }
    }

    public static class Candidate implements Comparable {
        int index;
        long cost;

        public Candidate(final int index, final long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            Candidate other = (Candidate) o;
            if (this.cost == other.cost)
            {
                return this.index - other.index;
            } else {
                return (int) (this.cost - other.cost);
            }
        }

        @Override
        public String toString() {
            return String.format("{cost: %d index: %d}",this.cost, this.index);
        }
    }

    public static void main(String[] args) {
        BinaryHeap newRMinBP = new BinaryHeap(5);
        newRMinBP.insertUsingRecursive(10, "Min");
        newRMinBP.insertUsingRecursive(5, "Min");
        newRMinBP.insertUsingRecursive(15, "Min");
        newRMinBP.insertUsingRecursive(1, "Min");
        System.out.println("Insert to min binary heap using recursive");
        // insertUsingRecursive -> Time: O (log n), Space: O (log n)
        newRMinBP.levelOrder();

        System.out.print("Extract Head of heap using recursive");
        int extractedMinUsingRecursive = newRMinBP.extractHeadOfBPUsingRecursive("Min");
        // extractHeadOfBP -> Time: O (log n), Space: O (log n)
        System.out.println(", got: " + extractedMinUsingRecursive);
        newRMinBP.levelOrder();

        BinaryHeap newRMaxBP = new BinaryHeap(5);
        newRMaxBP.insertUsingRecursive(10, "Max");
        newRMaxBP.insertUsingRecursive(5, "Max");
        newRMaxBP.insertUsingRecursive(15, "Max");
        newRMaxBP.insertUsingRecursive(1, "Max");
        System.out.println("Insert to max binary heap using recursive");
        // insertUsingRecursive -> Time: O (log n), Space: O (log n)
        newRMaxBP.levelOrder();

        System.out.print("Extract Head of heap using recursive");
        int extractedMaxUsingRecursive = newRMaxBP.extractHeadOfBPUsingRecursive("Max");
        // extractHeadOfBP -> Time: O (log n), Space: O (log n)
        System.out.println(", got: " + extractedMaxUsingRecursive);
        newRMaxBP.levelOrder();

        BinaryHeap newIMinBP = new BinaryHeap(5);
        newIMinBP.insertUsingIterative(10, "Min");
        newIMinBP.insertUsingIterative(5, "Min");
        newIMinBP.insertUsingIterative(15, "Min");
        newIMinBP.insertUsingIterative(1, "Min");
        System.out.println("Insert to min binary heap using iterative");
        // insertUsingIterative -> Time: O (log n), Space: O (1)
        newIMinBP.levelOrder();

        System.out.print("Extract Head of heap using iterative");
        int extractedMinUsingIterative = newIMinBP.extractHeadOfBPUsingIterative("Min");
        // extractHeadOfBP -> Time: O (log n), Space: O (log n)
        System.out.println(", got: " + extractedMinUsingIterative);
        newIMinBP.levelOrder();

        BinaryHeap newIMaxBP = new BinaryHeap(5);
        newIMaxBP.insertUsingIterative(10, "Max");
        newIMaxBP.insertUsingIterative(5, "Max");
        newIMaxBP.insertUsingIterative(15, "Max");
        newIMaxBP.insertUsingIterative(1, "Max");
        System.out.println("Insert to max binary heap using iterative");
        // insertUsingIterative -> Time: O (log n), Space: O (1)
        newIMaxBP.levelOrder();

        System.out.print("Extract Head of heap using iterative");
        int extractedMaxUsingIterative = newIMaxBP.extractHeadOfBPUsingIterative("Max");
        // extractHeadOfBP -> Time: O (log n), Space: O (log n)
        System.out.println(", got: " + extractedMaxUsingIterative);
        newIMaxBP.levelOrder();

        /*
         ***********
         * Heap sort // O ( n log n)
         * ********
         */
        int[] arr1 = {4, 10, 3, 5, 1};
        heapSort(arr1);
        System.out.println("Sorted array1: " + Arrays.toString(arr1));

        int[] arr2 = { 12, 11, 13, 5, 6, 7 };
        heapSort(arr2);
        System.out.println("Sorted array2: " + Arrays.toString(arr2));

        /*
         ***********
         * PriorityQueue - Min heap
         * ********
         */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(5);
        minHeap.add(7);
        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(1);

        System.out.println("original minHeap:" + minHeap);
        System.out.println("minHeap.peek(): " + minHeap.peek()); // 1
        System.out.println("minHeap.remove(): " + minHeap.remove()); // 1, or poll()
        System.out.println("minHeap.peek() after remove: " + minHeap.peek()); // 2
        System.out.println("current minHeap:" + minHeap);

        /*
         ***********
         * PriorityQueue - Max heap
         * ********
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(5);
        maxHeap.add(7);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(1);

        System.out.println("original maxHeap:" + maxHeap);
        System.out.println("maxHeap.peek(): " + maxHeap.peek()); // 7
        System.out.println("maxHeap.remove(): " + maxHeap.remove()); // 7, or poll()
        System.out.println("maxHeap.peek() after remove: " + maxHeap.peek()); // 5
        System.out.println("current maxHeap:" + maxHeap);

        /*
         ***********
         * PriorityQueue - Comparable object
         * ********
         */
        PriorityQueue<Candidate> priorityQueueCosts = new PriorityQueue<>();
        priorityQueueCosts.add(new Candidate(2, 50));
        priorityQueueCosts.add(new Candidate(1, 50));
        priorityQueueCosts.add(new Candidate(5, 50));
        priorityQueueCosts.add(new Candidate(5, 60));
        priorityQueueCosts.add(new Candidate(8, 60));
        priorityQueueCosts.add(new Candidate(2, 70));

        System.out.println("priorityQueueCosts: " + priorityQueueCosts);
        while (!priorityQueueCosts.isEmpty()) {
            System.out.println("extract priorityQueueCosts : " + priorityQueueCosts.remove());
        }
    }
}
