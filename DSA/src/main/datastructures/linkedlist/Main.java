package main.datastructures.linkedlist;

public class Main {

    public static void main(String[] args) {

        LinkedList myLinkedList = new LinkedList(5);
        myLinkedList.append(6);
        myLinkedList.append(7);
        myLinkedList.append(8);
        myLinkedList.append(4);
        myLinkedList.append(0);
        myLinkedList.append(9);

        // x = 5
        System.out.println("LL before reverse():");
        myLinkedList.printList();

        myLinkedList.reverseBetween(2, 4);

       System.out.println("\nLL after reverse():");
       myLinkedList.printList();
       myLinkedList.getHead();
    }
}