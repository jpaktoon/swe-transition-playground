package playground;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class HashingInJava {

    // Obsolete - no need
    public static void createHashTable() {
        // Create a HashTable to store
        // String values corresponding to integer keys
        Hashtable<Integer, String>
                ht = new Hashtable<>();

        // Input the values
        ht.put(1, "Geeks");
        ht.put(12, "forGeeks");
        ht.put(15, "A computer");
        ht.put(3, "Portal");

        // Printing the Hashtable
        System.out.println(ht);

        // Output
        // {15=A computer, 3=Portal, 12=forGeeks, 1=Geeks}
    }

    // Function to create HashMap from array
    public static void createHashMap(int arr[]) {
        // **************** Example 1
        // Creates an empty HashMap
        HashMap<Integer, Integer> hmap = new HashMap<>();

        // Traverse through the given array
        for (int j : arr) {

            // Get if the element is present
            Integer c = hmap.get(j);

            // If this is first occurrence of element
            // Insert the element
            if (hmap.get(j) == null) {
                hmap.put(j, 1);
            }

            // If elements already exists in hash map
            // Increment the count of element by 1
            else {
                hmap.put(j, ++c);
            }
        }

        // Print HashMap
        System.out.println(hmap);

        // Sample int arr[] = { 10, 34, 5, 10, 3, 5, 10 };
        // Output : {34=1, 3=1, 5=2, 10=3}

        // **************** Example 2
        // Create an empty hash map by declaring object
        // of string and integer type
        HashMap<String, Integer> map = new HashMap<>();

        // Adding elements to the Map
        // using standard put() method
        map.put("vishal", 10);
        map.put("sachin", 30);
        map.put("vaibhav", 20);

        // Print size and content of the Map
        System.out.println("Size of map is:- "
                + map.size());

        map.forEach((key, value) -> System.out.println(key + ": " + value));

        // Printing elements in object of Map
        System.out.println(map);

        // Checking if a key is present and if
        // present, print value by passing
        // random element
        if (map.containsKey("vishal")) {

            // Mapping
            Integer a = map.get("vishal");

            // Printing value for the corresponding key
            System.out.println("value for key"
                    + " \"vishal\" is:- " + a);
        }

        // Output
//        Size of map is:- 3
//        {vaibhav=20, vishal=10, sachin=30}
//        value for key "vishal" is:- 10

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }

        // Other Useful methods
        boolean hasKey = map.containsKey("x");
        boolean hasValue = map.containsValue(12);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Set<String> keys = map.keySet();
        boolean isEmpty = map.isEmpty();
        Collection<Integer> values = map.values();
        map.size();
        map.clear();

        // Inherit from Map
        map.getOrDefault("x", null);
        // If the specified key is not already associated with a value (or is mapped to null)
        // associates it with the given value and returns null, else returns the current value.
        Integer result = map.putIfAbsent("x", 12);
        // Replaces the entry for the specified key only if currently mapped to the specified value.
        map.replace("x", 12, 14);

        Integer xValue = map.get("x");
        System.out.println("value of x before: " + xValue);
        xValue = 77; // it will not replace the value of x in map
        System.out.println("value of x after: " + map.get("x"));

        // Removes the entry for the specified key only if it is currently mapped to the specified value.
        map.remove("x", 12);
    }

    public static void createTreeMap() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // Adding elements to the tree map
        treeMap.put("C", 3);
        treeMap.put("B", 2);
        treeMap.put("A", 1);

        // Getting values from the tree map
        int valueA = treeMap.get("A");
        System.out.println("Value of A: " + valueA);

        // Removing elements from the tree map
        treeMap.remove("B");

        // Iterating over the elements of the tree map
        for (String key : treeMap.keySet()) { // it is sorted
            System.out.println("V1 Key: " + key + ", Value: " + treeMap.get(key));
        }

        for (Map.Entry<String, Integer> e : treeMap.entrySet())
            System.out.println("V2 Key: " + e.getKey() + ", Value: "
                    + e.getValue());

        // Output
//        Value of A: 1
//        Key: A, Value: 1
//        Key: C, Value: 3

        // Sorted
        treeMap.firstKey();
        treeMap.firstEntry();
        treeMap.lastEntry();
        treeMap.lastKey();
        //useful methods
        treeMap.containsKey("A");
        treeMap.containsValue(1);
        treeMap.entrySet();
        treeMap.keySet();
        treeMap.values();
        // The method returns the portion of this map whose keys range
        // from startKey, inclusive, to endKey, exclusive
        treeMap.subMap("A", "C");
        treeMap.clear();
    }

    public static void createLinkedHashMap() {
        LinkedHashMap<String, String> lhm =
                new LinkedHashMap<>();
        lhm.put("one", "practice.geeksforgeeks.org");
        lhm.put("two", "code.geeksforgeeks.org");
        lhm.put("four", "www.geeksforgeeks.org");

        // It prints the elements in same order
        // as they were inserted
        System.out.println(lhm);

        System.out.println("Getting value for key 'one': "
                + lhm.get("one"));
        System.out.println("Size of the map: " + lhm.size());
        System.out.println("Is map empty? " + lhm.isEmpty());
        System.out.println("Contains key 'two'? "+
                lhm.containsKey("two"));
        System.out.println("Contains value 'practice.geeks"
                +"forgeeks.org'? "+ lhm.containsValue("practice"+
                ".geeksforgeeks.org"));
        System.out.println("delete element 'one': " +
                lhm.remove("one"));
        System.out.println(lhm);

        // Output
//        {one=practice.geeksforgeeks.org, two=code.geeksforgeeks.org, four=www.geeksforgeeks.org}
//        Getting value for key 'one': practice.geeksforgeeks.org
//        Size of the map: 3
//        Is map empty? false
//        Contains key 'two'? true
//        Contains value 'practice.geeksforgeeks.org'? true
//        delete element 'one': practice.geeksforgeeks.org
//        {two=code.geeksforgeeks.org, four=www.geeksforgeeks.org}

        // Creating the linked hashmap and implementing
        // removeEldestEntry() to MAX size e.g. 6
        LinkedHashMap<Integer, String> li_hash_map =
                new LinkedHashMap<>() {
                    protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                        return size() > 6;
                    }
                };
        // Adding elements using put()
        li_hash_map.put(0, "Welcome");
        li_hash_map.put(1, "To");
        li_hash_map.put(2, "The");
        li_hash_map.put(3, "World");
        li_hash_map.put(4, "Of");
        li_hash_map.put(5, "geeks");

        System.out.println("" + li_hash_map);
        // {0=Welcome, 1=To, 2=The, 3=World, 4=Of, 5=geeks}

        // Adding more elements
        li_hash_map.put(6, "GeeksforGeeks");

        // Displaying the map after adding one more element
        System.out.println("" + li_hash_map);
        // {1=To, 2=The, 3=World, 4=Of, 5=geeks, 6=GeeksforGeeks}

        // Adding more elements
        li_hash_map.put(7, "Hello");

        // Displaying the map after adding one more element
        System.out.println("" + li_hash_map);
        // {2=The, 3=World, 4=Of, 5=geeks, 6=GeeksforGeeks, 7=Hello}

        li_hash_map.put(7, "Again?");
        System.out.println("" + li_hash_map);
        // {2=The, 3=World, 4=Of, 5=geeks, 6=GeeksforGeeks, 7=Again?}
    }

    public static void createConcurrentHashMap(){
        ConcurrentHashMap<Integer, String> m =
                new ConcurrentHashMap<Integer, String>();
        m.put(100, "Hello");
        m.put(101, "Geeks");
        m.put(102, "Geeks");

        // Printing the ConcurrentHashMap
        System.out.println("ConcurrentHashMap: " + m);

        // Adding Hello at 101 key
        // This is already present in ConcurrentHashMap object
        // Therefore its better to use putIfAbsent for such cases
        m.putIfAbsent(101, "Hello");

        // Printing the ConcurrentHashMap
        System.out.println("\nConcurrentHashMap: " + m);

        // Trying to remove entry for 101 key
        // since it is present
        m.remove(101, "Geeks");

        // Printing the ConcurrentHashMap
        System.out.println("\nConcurrentHashMap: " + m);

        // replacing the value for key 101
        // from "Hello" to "For"
        m.replace(100, "Hello", "For");

        // Printing the ConcurrentHashMap
        System.out.println("\nConcurrentHashMap: " + m);

        // Output
//        ConcurentHashMap: {100=Hello, 101=Geeks, 102=Geeks}
//
//        ConcurentHashMap: {100=Hello, 101=Geeks, 102=Geeks}
//
//        ConcurentHashMap: {100=Hello, 102=Geeks}
//
//        ConcurentHashMap: {100=For, 102=Geeks}
    }

    public static void createHashSet() {
        HashSet<String> h = new HashSet<String>();

        // Adding elements into HashSet using add()
        h.add("India");
        h.add("Australia");
        h.add("South Africa");
        h.add("India"); // adding duplicate elements

        // Displaying the HashSet
        System.out.println(h);

        // Checking if India is present or not
        System.out.println("\nHashSet contains India or not:"
                + h.contains("India"));

        // Removing items from HashSet using remove()
        h.remove("Australia");

        // Printing the HashSet
        System.out.println("\nList after removing Australia:" + h);

        // Iterating over hash set items
        System.out.println("\nIterating over list:");
        Iterator<String> i = h.iterator();
        while (i.hasNext())
            System.out.println(i.next());

        // Output
//        [South Africa, Australia, India]
//
//        HashSet contains India or not:true
//
//        List after removing Australia:[South Africa, India]
//
//        Iterating over list:
//        South Africa
//        India

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        HashSet<ArrayList<Integer>> set = new HashSet<>();
        set.add(list1);
        set.add(list2);

        System.out.println(set);
        System.out.println("Size of set: " + set.size());  // Output: 1
    }

    public static void createLinkedHashSet() {
        LinkedHashSet<String> linkedset =
                new LinkedHashSet<String>();

        // Adding element to LinkedHashSet
        linkedset.add("A");
        linkedset.add("B");
        linkedset.add("C");
        linkedset.add("D");

        // This will not add new element as A already exists
        linkedset.add("A");
        linkedset.add("E");

        System.out.println("Size of LinkedHashSet = " +
                linkedset.size());
        System.out.println("Original LinkedHashSet:" + linkedset);
        System.out.println("Removing D from LinkedHashSet: " +
                linkedset.remove("D"));
        System.out.println("Trying to Remove Z which is not "+
                "present: " + linkedset.remove("Z"));
        System.out.println("Checking if A is present=" +
                linkedset.contains("A"));
        System.out.println("Updated LinkedHashSet: " + linkedset);

        // Output
//        Size of LinkedHashSet = 5
//        Original LinkedHashSet:[A, B, C, D, E]
//        Removing D from LinkedHashSet: true
//        Trying to Remove Z which is not present: false
//        Checking if A is present=true
//        Updated LinkedHashSet: [A, B, C, E]

        // Iterating though the LinkedHashSet
        // using iterators
        Iterator itr = linkedset.iterator();

        while (itr.hasNext())
            System.out.print(itr.next() + ", ");

        // New line
        System.out.println();

        // Using enhanced for loop for iteration
        for (String s : linkedset)
            System.out.print(s + ", ");
        System.out.println();
    }

    public static void createTreeSet() {
        TreeSet<String> ts1 = new TreeSet<String>();

        // Elements are added using add() method
        ts1.add("A");
        ts1.add("B");
        ts1.add("C");

        // Duplicates will not get insert
        ts1.add("C");

        // Elements get stored in default natural
        // Sorting Order(Ascending)
        System.out.println("TreeSet: " + ts1);

        // Checking if A is present or not
        System.out.println("\nTreeSet contains A or not:"
                + ts1.contains("A"));

        // Removing items from TreeSet using remove()
        ts1.remove("A");

        // Printing the TreeSet
        System.out.println("\nTreeSet after removing A:" + ts1);

        // Iterating over TreeSet items
        System.out.println("\nIterating over TreeSet:");
        Iterator<String> i = ts1.iterator();
        while (i.hasNext())
            System.out.println(i.next());

        // Output
//        TreeSet: [A, B, C]
//
//        TreeSet contains A or not:true
//
//        TreeSet after removing A:[B, C]
//
//        Iterating over TreeSet:
//        B
//        C
    }

    public static void main(String[] args) {
        //createLinkedHashMap();
        //createHashMap(new int[]{});
        createHashSet();
    }
}
