package main.datastructures.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private Node head;
    private Node tail;
    private int length;

    public class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    }

    public void getTail() {
        if (head == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
        }
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value)  {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev = get(index - 1);
        Node temp = prev.next;

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    // Interview leetcode

    // 1. find middle node
    public Node findMiddleNode(){
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 2. Check loop
    public boolean hasLoop() {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            if (fast.next == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//
//            if (slow == fast) {
//                return true;
//            }
//        }

        return false;
    }

    public Node findKthFromEnd(int k) {
//        Node x = head;
//        Node y = head;
//
//        int i = 0;
//        while (x != null) {
//            x = x.next; // move x
//            i++;
//            if (i > k) {
//                y = y.next; // start y
//            }
//        }
//        if (i < k) return null;
//        return y;

        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public void partitionList(int x) {
//        Node smallHead = head;
//        Node bigHead = head;
//
//        Node temp1 = head;
//        while (bigHead != null && bigHead.value < x) {
//            if (temp1 == null || temp1.value >= x)
//                bigHead = temp1;
//            else
//                temp1 = temp1.next;
//        }
//
//        Node temp2 = head;
//        while (smallHead != null && smallHead.value >= x) {
//            if (temp2 == null || temp2.value < x)
//                smallHead = temp2;
//            else
//                temp2 = temp2.next;
//        }
//
//        if (smallHead != null) System.out.println("small head " + smallHead.value);
//        if (bigHead != null) System.out.println("big head " + bigHead.value);
//
//        if (smallHead == null || bigHead == null) return;
//
//        Node smallTail = smallHead;
//        Node bigTail = bigHead;
//
//        Node temp3 = head;
//        while (temp3 != null) {
//            if (temp3 != smallHead && temp3.value < x) {
//                smallTail.next = temp3;
//                smallTail = temp3;
//            } else if (temp3 != bigHead && temp3.value >= x) {
//                bigTail.next = temp3;
//                bigTail = temp3;
//            }
//
//            temp3 = temp3.next;
//        }
//
//        bigTail.next = null;
//        smallTail.next = bigHead;
//        head = smallHead;

            if (head == null) return;

            Node d1 = new Node(0);
            Node d2 = new Node(x);

            Node p1 = d1;
            Node p2 = d2;

            Node current = head;

                while (current != null){
                if (current.value < x) {
                    p1.next = current;
                    p1 = current;
                }
                else {
                    p2.next = current;
                    p2 = current;
                }
                current = current.next;
            }
            p2.next = null;
            p1.next = d2.next;
            head = d1.next;
    }

    public void removeDuplicates() {
//        Node temp = head;
//        while (temp != null){
//            Node index = temp.next;
//            while (index != null) {
//                if (index.value == temp.value) {
//                    temp.next = index.next;
//                    length -= 1;
//                }
//                index = index.next;
//            }
//            temp = temp.next;
//        }
        Set<Integer> values = new HashSet<>();
        Node pre = head;
        Node temp = head;
        while (temp != null){
            if (values.contains(temp.value)) {
                pre.next = temp.next;
                length -= 1;
            } else {
                values.add(temp.value);
                pre = temp;
            }
            temp = temp.next;
        }
    }

    public int binaryToDecimal(){
        int num = 0;
        Node current = head;
        while(current != null){
//            if (current.next != null)
//                num = (current.value + num) *2;
//            else
//                num = current.value + num;
            num = current.value + (num * 2);
            current = current.next;
        }
        return num;
    }

    public void reverseBetween(int m, int n) {
//        if (head == null || head.next == null) return;
//
//        Node current = head;
//        int j = 0;
//        Node aT = head;
//        Node bT = head;
//        Node temp = new Node(0);
//
//        while (current != null) {
//            if (j <= m) {
//                if (j == m) {
//                    bT = current;
//                    temp.next = bT;
//                } else aT = current;
//            }
//
//            if (j > m && j <= n) {
//                bT.next = current.next;
//                current.next = temp.next;
//                temp.next = current;
//                current = bT;
//            } else if ( j > n) break;
//
//            j++;
//
//            current = current.next;
//        }
//
//        if (m != 0)
//            aT.next = temp.next;
//        else
//            head = temp.next;

        // Check: If linked list is empty, nothing to reverse.
        // Exit the method.
        if (head == null) return;

        // Create a 'dummyNode' that precedes the head.
        // Simplifies handling edge cases.
        Node dummyNode = new Node(0);
        dummyNode.next = head;

        // 'previousNode' is used to navigate to the node
        // right before our sublist begins.
        Node previousNode = dummyNode;

        // Move 'previousNode' to node just before sublist.
        for (int i = 0; i < m; i++) {
            previousNode = previousNode.next;
        }

        // 'currentNode' marks the first node of sublist.
        Node currentNode = previousNode.next;

        // Loop reverses the section from startIndex to endIndex.
        for (int i = 0; i < n - m; i++) {

            // 'nodeToMove' is the node we'll move to sublist start.
            Node nodeToMove = currentNode.next;

            // Detach 'nodeToMove' from its current position.
            currentNode.next = nodeToMove.next;

            // Attach 'nodeToMove' at the beginning of the sublist.
            nodeToMove.next = previousNode.next;

            // Move 'nodeToMove' to the start of our sublist.
            previousNode.next = nodeToMove;
        }

        // Adjust 'head' if the first node was part of sublist.
        head = dummyNode.next;
    }
}