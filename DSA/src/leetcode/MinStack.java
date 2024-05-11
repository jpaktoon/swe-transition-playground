package leetcode;

// https://leetcode.com/problems/min-stack/description/?envType=study-plan-v2&envId=top-interview-150
public class MinStack {

    int min = Integer.MAX_VALUE;
    int size = 0;

    Node head;

    public class Node {
        public int value;
        public int restMin;

        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public MinStack() {

    }

    public void push(int val) {
        Node newNode = new Node(val);
        newNode.restMin = min; // min of the items in stack excluding newNode
        if (val < min) {
            min = val; // set a new min, if the newNode is lower
        }
        if (size == 0) {
            head = newNode;
            newNode.restMin = val; // reset for the first node
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void pop() {
        if (size != 0) {
            min = head.restMin; // set the min for the rest items.
            head = head.next;
            size--;
        }

        if (size == 0) {
            min = Integer.MAX_VALUE; // reset min when empty
        }
    }

    public int top() {
        if (size != 0) {
            return head.value;
        }
        return Integer.MIN_VALUE;
    }

    public int getMin() {
        return min;
    }
}
