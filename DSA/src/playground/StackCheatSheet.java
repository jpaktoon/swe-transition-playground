package playground;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StackCheatSheet {
    public static void main(String[] args) {

        /*
         ***********
         * Stack : push() and pop() and peek()
         * ********
         */
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        // Stack.push(4) -> Vactor.add(item) -> elementData[length] = 4
        stack.push(4);  // Last-in & First-out

        System.out.println(stack); // [1, 2, 3, 4], here the elements are not being stacked up
        System.out.println(stack.size()); // 4

        System.out.println(stack.contains(1)); // O(n)
        System.out.println(stack.get(0)); // 1 - Gets the first element
        System.out.println(stack.peek()); // 4 - Shows the last element
        // Stack.pop() -> Vactor.removeElementAt(len - 1) -> elementData[index] = null
        System.out.println(stack.pop()); // 4 - Remove the last element

        showAndRemoveStackElements(stack); // 3 2 1

        stack.clear(); // O(n), assigned each element index to null

        /*
         ***********
         * Deque: ArrayDeque : push() and pop() and peek()
         * ********
         */
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        // push() -> ArrayDeque.addFirst(item)
        arrayDeque.push(1);  // Inserts element at the first position
        arrayDeque.push(2);

        System.out.println("Current arrayDeque: " + arrayDeque);
        System.out.println("getFirst: " + arrayDeque.getFirst());
        System.out.println("getLast: " + arrayDeque.getLast());
        System.out.println("After get arrayDeque: " + arrayDeque);

        arrayDeque.contains(2); // O(n), for loop
        arrayDeque.size(); // O(1), using marked index of head and tail to calculate the size
        traversalDeque(arrayDeque); // O(n), [2, 1] iterator

        System.out.println(arrayDeque); // [2, 1], LIFO insertion
        System.out.println(arrayDeque.peek()); // 2 - Shows first element
        // pop() -> ArrayDeque.removeFirst()
        System.out.println(arrayDeque.pop());  // 2 - Remove and return first element

        arrayDeque.clear(); // set head and tail index = 0, also O(n) since internally it loop sets all elements in the array to null

        /*
         ***********
         * Deque: LinkedList : push() and pop() and peek()
         * ********
         */
        Deque<Integer> linkedListDeque = new LinkedList<>();
        // push() -> LinkedList.addFirst(item)
        linkedListDeque.push(1);  // Inserts element at the first position
        linkedListDeque.push(2);

        System.out.println("Current linkedList: " + linkedListDeque);
        System.out.println("getFirst: " + linkedListDeque.getFirst());
        System.out.println("getLast: " + linkedListDeque.getLast());
        System.out.println("After get linkedList: " + linkedListDeque);

        linkedListDeque.contains(1); // O(n), LinkedList.indexOf(1) >= 0; for loop
        linkedListDeque.size(); // O(1), property int size

        traversalDeque(linkedListDeque); // O(n), [2, 1] iterator

        System.out.println(linkedListDeque); // [2, 1], LIFO insertion
        System.out.println(linkedListDeque.peek()); // 2 - Shows first element
        // pop() -> LinkedList.removeFirst()
        System.out.println(linkedListDeque.pop());  // 2 - Remove and return first element

        linkedListDeque.clear(); // same as LinkedList O(n), internally it also clears all the links between nodes
    }

    private static void showAndRemoveStackElements(Stack<Integer> stack) {
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop() + " "); // pop() - O(1)
        }
        System.out.println();
    }

    private static void traversalDeque(Deque deque) {
        Iterator iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}
