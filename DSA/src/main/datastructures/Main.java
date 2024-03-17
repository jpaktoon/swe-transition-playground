package main.datastructures;

public class Main {

    public static void main(String[] args) {

        LinkedList myLinkedList = new LinkedList(5);
//        myLinkedList.append(6);
//        myLinkedList.append(1);
//        myLinkedList.append(1);
//        myLinkedList.append(4);
//        myLinkedList.append(1);
//        myLinkedList.append(9);

        // x = 5
        System.out.println("LL before duplicate zero():");
        myLinkedList.printList();

        myLinkedList.duplicateZero();

       System.out.println("\nLL after duplicate zero():");
       myLinkedList.printList();
       myLinkedList.getHead();
    }
}