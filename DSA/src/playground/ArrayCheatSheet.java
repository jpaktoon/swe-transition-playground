package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayCheatSheet {
    public static void main(String[] args){
        // Array
        String[] s0 = new String[5]; // fix size = 5, Time O(1), Space O(n)
        String[] s1 = {"a", "b"}; // fix size = 2, Time O(1), Space O(n)

        System.out.println(s0[0]); // time O(1), space O(1), String: null
        System.out.println(s1[0]); // time O(1), space O(1), String: "a"
        System.out.println(s1[1]); // time O(1), space O(1), String: "b"
        System.out.println(Arrays.toString(s1)); // String: [a, b]

        try {
            System.out.println(s1[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        StringBuilder ss = new StringBuilder();
        for (String item : s1) {
            ss.append(item);
        }
        System.out.println(ss);

        char[] cs1 = {'t','e','s','t'};
        System.out.println(new String(cs1)); // time: O(n), space O(n), String: "test"

        // Arrays Helpers
        char[] cs2 = Arrays.copyOfRange(cs1, 1,3); // time: O(to-from), space O(to-from)
        System.out.println(cs2); // "es" from inclusive index 1 to exclude 3
        char[] cs3 = Arrays.copyOfRange(cs1, 1, cs1.length); // time: O(to-from), space O(n)
        System.out.println(cs3); // "est" from inclusive index 1 to the end
        char[] cs4 = Arrays.copyOf(cs1, 2); // time: O(n), space O(newLength)
        System.out.println(cs4); // "te" copy as size 2
        System.out.println(Arrays.toString(cs1)); // time: O(n), space O(n), String: [t, e, s, t]

        // ArrayList
        ArrayList<String> as1 = new ArrayList<>(); // time: O(1), space O(1)/O(n)
        ArrayList<String> as2 = new ArrayList<>(20); // time: O(1), space O(n)

        String[] fruitsArray = {"Apple", "Banana", "Orange"};
        ArrayList<String> fruits1 = new ArrayList<>(Arrays.asList(fruitsArray));
        ArrayList<String> fruits2 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

        // All of these change the original ArrayList
        as1.add("test"); // time: O(1), space O(1) if the size is not exceed 10 else O(n)
        System.out.println(as1); // String: [test]
        as1.add(0, "begin"); // time: O(n), space O(1) if the size is not exceed 10 else O(n)
        System.out.println(as1); // String: [begin, test]
        as1.addAll(fruits1); // time: O(m+n), space O(m+n)
        System.out.println(as1); // String: [begin, test, Apple, Banana, Orange]
        as1.addAll(1, fruits2); // time: O(m+n), space O(m+n)
        System.out.println(as1); // String: [begin, Apple, Banana, Cherry, test, Apple, Banana, Orange]

        // Traversal - all // time O(n), space O(1)
        StringBuilder result = new StringBuilder();
        for (String item : fruits2) { // prefer
            result.append(item);
        }
        for (int i = 0; i < fruits1.size(); i++){
            result.append(fruits1.get(i)); // time O(1), space O(1)
        }
        Iterator<String> iterator = fruits1.iterator();
        while(iterator.hasNext()) {
            result.append(iterator.next());
        }

        // Get
        System.out.println(as1.get(5)); // time O(1), space O(1), String: "Apple"
        try {
            System.out.println(as1.get(20));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        // IndexOf
        System.out.println(as1.indexOf("Apple")); // First found at index 1, time O(n), space O(1). It is linear search
        System.out.println(as1.lastIndexOf("Apple")); // Last found at index 5, time O(n), space O(1). It is linear search
        System.out.println(as1.lastIndexOf("x")); // -1 for not found

        // Remove
        as1.remove(1); // time O(n), space O(1)
        System.out.println(as1); // String : [begin, Banana, Cherry, test, Apple, Banana, Orange]
        as1.remove("Banana"); // time O(n), space O(1)
        System.out.println(as1); // String : [begin, Cherry, test, Apple, Banana, Orange]
    }
}
