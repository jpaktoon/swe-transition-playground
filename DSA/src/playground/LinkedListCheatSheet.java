package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListCheatSheet {

    public static void main(String[] args){
        // internally it is implemented using doubly linked list
        // all cases, space complexity is O(1) excepts toArray()

        // Constructors without size
        LinkedList ll = new LinkedList();

        //  create an ordered list that contains all the elements of a specified collection
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("head");
        arrayList.add("first");
        arrayList.add("second");
        arrayList.add("tail");
        LinkedList llc = new LinkedList(arrayList);

        // Traversal
        System.out.println(llc.toString()); // String [head, first, second, tail], O(n), internally it uses Iterator
        System.out.println(llc.get(2)); // O(n), String "second"
        System.out.println(llc.getFirst()); // O(1), internally it uses "first" pointer, String "head"
        System.out.println(llc.getLast()); // O(1), internally it uses "last" pointer, String "tail"

        // Contains | Searching
        System.out.println(llc.contains("first")); // O(n), boolean true
        System.out.println(llc.indexOf("first")); // O(n), int 1
        System.out.println(llc.indexOf("dummy")); // O(n), int -1

        // Add | Insert
        llc.add("last"); // add to last, O(1), internally it uses tail (last) pointer
        System.out.println(llc.toString()); // String [head, first, second, tail, last]
        System.out.println(llc.size()); // Int 5, O(1), a defined attribute
        llc.add(3, "third"); // add to index, O(n)
        System.out.println(llc.toString()); // String [head, first, second, third, tail, last]
        llc.addAll(4, new ArrayList<String>(Arrays.asList("dummy1", "dummy2", "dummy3"))); // at to last/index, O (n + m)
        System.out.println(llc.toString()); // String [head, first, second, third, dummy1, dummy2, dummy3, tail, last]

        llc.addFirst("addFirst"); // O(1)
        llc.addLast("addLast"); // O(1)
        System.out.println(llc.toString()); // String [addFirst, head, first, second, third, dummy1, dummy2, dummy3, tail, last, addLast]

        // Delete / remove
        llc.removeFirst(); // O(1)
        llc.remove(); // same as removeFirst
        System.out.println(llc.toString()); // String [first, second, third, dummy1, dummy2, dummy3, tail, last, addLast]
        llc.remove(3); // O(n)
        llc.remove("dummy2"); // O(n)
        System.out.println(llc.toString()); // String [first, second, third, dummy3, tail, last, addLast]
        llc.removeLast(); // O(1)

        // Replace
        llc.set(3, "set"); // O(n)
        System.out.println(llc.toString()); // String [first, second, third, set, tail, last]

        System.out.println(Arrays.toString(llc.toArray())); // time O(n), space O(n)
        // since the array.length < ll.size(), internally it will create new array
        // else use that array.
        // length 0 is the same as toArray()
        String[] redirect = (String[]) llc.toArray(new String[3]);

        // Delete whole
        llc.clear(); // O(n), internally it also clears all the links between nodes
    }
}
