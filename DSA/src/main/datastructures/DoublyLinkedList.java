package main.datastructures;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    public class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    public DoublyLinkedList(int value){
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void append(int value){
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        length++;
    }

    public Node removeLast(){
        if (length == 0) return null;

        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        return temp;
    }

    public void prepend(int value){
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst(){
        if (length == 0) return null;

        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length)
            return null;
        else {
            Node temp = head;
            if (length / 2 > index) {
                // first half
                for (int i = 0; i < index; i++){
                    temp = temp.next;
                }
            } else {
                // second half
                temp = tail;
                for (int i = length - 1; i > index; i--){
                    temp = temp.prev;
                }
            }
            return temp;
        }
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if(temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {

        if (index < 0 || index > length) return false;

        if (index == 0)
            prepend(value);
        else if (index == length)
            append(value);
        else {

            Node newNode = new Node(value);
            Node prev = get(index - 1);

            Node after = prev.next;

            after.prev = newNode;
            prev.next = newNode;

            newNode.prev = prev;
            newNode.next = after;

            length++;

        }
        return true;
    }

    public Node remove(int index){

        if (index < 0 || index >= length) return null;

        if (index == 0) return removeFirst();
        if (index == length -1 ) return removeLast();

        Node temp = get(index);

        if (temp != null) {
            Node prev = temp.prev;
            Node after = temp.next;

            prev.next = after;
            after.prev = prev;

            temp.next = null;
            temp.prev = null;

            length--;
        }

        return temp;
    }

//    public void swapFirstLast() {
//        if (length <= 1) return;
//
//        Node nextNode = head.next;
//        Node prevNode = tail.prev;
//
//        head.prev = prevNode;
//        head.next = null;
//
//        tail.prev = null;
//        tail.next = nextNode;
//
//        nextNode.prev = tail;
//        prevNode.next = head;
//
//        head = nextNode.prev;
//        tail = prevNode.next;
//    }

    public void swapFirstLast() {
        if (length < 2) return;
        int temp = head.value;
        head.value = tail.value;
        tail.value = temp;
    }

//    public void reverse() {
//        Node current = head;
//
//        head = tail;
//        tail = current;
//
//        while (current != null) {
//            Node temp = current.next;
//            current.next = current.prev;
//            current.prev = temp;
//            current = temp;
//        }
//    }

    public void reverse() {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        temp = head;
        head = tail;
        tail = temp;
    }

//    public boolean isPalindrome(){
//
//        Node a = head;
//        Node b = tail;
//
//        while (a != b && a.next != b.prev) {
//            if (a.value != b.value) {
//                return false;
//            }
//            a = a.next;
//            b = b.prev;
//        }
//
//        return true;
//    }

    public boolean isPalindrome() {
        if (length <= 1) return true;

        Node forwardNode = head;
        Node backwardNode = tail;
        for (int i = 0; i < length / 2; i++) {
            if (forwardNode.value != backwardNode.value) return false;
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
    }

//    public void swapPairs(){
//
//        Node a = head;
//
//        while (a != null && a.next != null) {
//            Node b = a.next;
//            Node c = b.next;
//
//            a.next = b.next;
//            b.next = a;
//            b.prev = a.prev;
//            a.prev = b;
//
//            a = c;
//        }
//        if (head != null) head = head.prev;
//        if (a != null && tail != null) tail = tail.next;
//    }

    public void swapPairs() {
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;

        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;

            previousNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            secondNode.prev = previousNode;
            firstNode.prev = secondNode;

            if (firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }

            head = firstNode.next;
            previousNode = firstNode;
        }

        head = dummyNode.next;
        if (head != null) head.prev = null;
    }

}
