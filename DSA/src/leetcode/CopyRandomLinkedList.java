package leetcode;

import java.util.*;

public class CopyRandomLinkedList {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        // BFS
        Deque<Node> queue = new ArrayDeque<>(); // for old nodes
        HashMap<Integer, Node> visited = new HashMap<>(); // for new nodes

        queue.offer(head);
        Node newHead = null;

        while (!queue.isEmpty()) {
            Node oldNode = queue.poll();
            Node newNode = new Node(head.val);
            if (newHead == null) newHead = newNode;
            visited.put(newNode.val, newNode);
            // next node
            if (oldNode.next != null) {
                if (visited.containsKey(oldNode.next.val)) {
                    newNode.next = visited.get(oldNode.next.val);
                } else {
                    queue.offer(oldNode.next);
                }
            }

            // random node
            if (oldNode.random != null) {
                if (visited.containsKey(oldNode.random.val)) {
                    newNode.random = visited.get(oldNode.random.val);
                } else {
                    queue.offer(oldNode.random);
                }
            }
        }

        return newHead;
    }
}
