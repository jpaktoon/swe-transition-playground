package playground;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashingCollisionResolution {

    // Direct chaining / sepereate chaining
    public static class DirectChaining{
        LinkedList<String>[] hashTable;
        int maxChainSize = 5;

        DirectChaining(int size) {
            hashTable = new LinkedList[size];
        }

        public void insertHashTable(String word) {
            int newIndex = modASCIIHashFunction(word, hashTable.length);
            if(hashTable[newIndex] == null) {
                hashTable[newIndex] = new LinkedList<String>();
                hashTable[newIndex].add(word);
            } else {
                hashTable[newIndex].add(word);
            }
        }

        public boolean searchHashTable(String word) {
            int newIndex = modASCIIHashFunction(word, hashTable.length);
            if (hashTable[newIndex] != null && hashTable[newIndex].contains(word)) {
                System.out.println("\n" + "\"" + word + "\"" + " found in Hastable at lcoation: " +newIndex);
                return true;
            } else {
                System.out.println("\n" + "\"" + word + "\"" + " not found in HashTable");
                return false;
            }
        }

        public void deleteKeyHashTable(String word) {
            int newIndex = modASCIIHashFunction(word, hashTable.length);
            if (searchHashTable(word)) {
                hashTable[newIndex].remove(word);
                System.out.println("\n" + "\"" + word + "\"" + " has been deleted from HashTable ");
            }
        }
    }

    protected static abstract class OpenAddressing {
        String[] hashTable; // not a linked list
        int usedCellNumber;

        protected OpenAddressing(int size) {
            hashTable = new String[size];
            usedCellNumber = 0; // for calculate load factor
        }

        // need to calculate the load factor to consider
        // when we need to create a new hashTable
        protected double getLoadFactor() {
            double loadFactor = usedCellNumber * 1.0/hashTable.length;
            return loadFactor;
        }

        // Rehash - call when load factor >= 0.75
        // create new hashTable then move all elements to the new one
        protected void rehashKeys(String newStringToBeInserted) {
            usedCellNumber = 0;
            ArrayList<String> data = new ArrayList<String>();
            for (String s : hashTable) {
                if (s != null)
                    data.add(s);
            }
            data.add(newStringToBeInserted);
            hashTable = new String[hashTable.length * 2];
            for (String s : data) {
                insertKeyInHashTable(s);
            }
        }

        // Insert in HashTable
        protected abstract void insertKeyInHashTable(String word);

    }

    public static class LinearProbing extends OpenAddressing{

        LinearProbing(int size) {
            super(size);
        }

        @Override
        protected void insertKeyInHashTable(String word) {
            double loadFactor = getLoadFactor();
            if (loadFactor >= 0.75) {
                // need reharsh -> create new hashTable size * 2
                // then move all elements, and also new element to the new hashTable
                rehashKeys(word);
            } else {
                // normal insert
                int index = modASCIIHashFunction(word, hashTable.length);
                for (int i = index; i<index+hashTable.length; i++) {
                    // loop start from the hash code index
                    // normalize it to fit with the hashTable
                    // so the newIndex value can be [0...hashTable.length-1]
                    int newIndex = i % hashTable.length;
                    if (hashTable[newIndex] == null) {
                        // if the index is available
                        hashTable[newIndex] = word;
                        System.out.println("The " + word + " successfully inserted at location: "+newIndex);
                        break;
                    } else {
                        // if not, i++ then %hashTable.length will result the next index
                        // *** The % will help revert the newIndex as well
                        // For example hashTable.length=5, newIndex can be [3,4,0,1,2] etc.
                        System.out.println(newIndex+" is already occupied . Trying next empty cell");
                    }
                }
            }
            usedCellNumber++;
        }

        // same as insert, calculate index -> newIndex checks value if not found check next.
        // finally return or not found
        public boolean searchHashTable(String word) {
            int index = modASCIIHashFunction(word, hashTable.length);
            for (int i = index; i < index+hashTable.length; i++) {
                int newIndex = i % hashTable.length;
                if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                    System.out.println(word +" found at location: "+ newIndex);
                    return true;
                }
            }
            System.out.println(word +" not found in hasbTable");
            return false;
        }

        // same as insert, calculate index -> newIndex checks value if not found check next.
        // finally set to null or not found
        public void deleteKeyHashTable(String word) {
            int index = modASCIIHashFunction(word, hashTable.length);
            for (int i = index; i<index+hashTable.length; i++) {
                int newIndex = i % hashTable.length;
                if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                    hashTable[newIndex] = null;
                    System.out.println(word +" has been deleted from HashTable");
                    return;
                }
            }
            System.out.println(word +" not found in HashTable");
        }
    }

    public static class QuadraticProbing extends OpenAddressing{
        QuadraticProbing(int size){
            super(size);
        }

        @Override
        protected void insertKeyInHashTable(String word) {
            double loadFactor = getLoadFactor();
            if (loadFactor >= 0.75) {
                rehashKeys(word);
            } else {
                int index = modASCIIHashFunction(word, hashTable.length);
                int counter = 0;
                for (int i = index; i< index+hashTable.length; i++) {
                    int newIndex = (index + (counter*counter)) % hashTable.length;
                    if (hashTable[newIndex] == null) {
                        hashTable[newIndex] = word;
                        System.out.println(word + " has been inserted successfully");
                        break;
                    } else {
                        System.out.println(newIndex + " is already occupied. Trying next one...");
                    }
                    counter++;
                }
            }
            usedCellNumber++;
        }
    }

    public static class DoubleHashing extends OpenAddressing{
        DoubleHashing(int size){
            super(size);
        }

        private int addAllDigitsTogether(int sum) {
            int value = 0;
            while (sum > 0) {
                value = sum % 10;
                sum = sum / 10;
            }
            return value;
        }

        public int secondHashFunction(String x, int M) {
            char ch[];
            ch = x.toCharArray();
            int i, sum;
            for (sum = 0, i=0; i<x.length(); i++) {
                sum += ch[i];
            }
            while (sum > hashTable.length) {
                sum = addAllDigitsTogether(sum);
            }

            return sum % M;

        }

        @Override
        protected void insertKeyInHashTable(String word) {
            double loadFactor = getLoadFactor();
            if (loadFactor >= 0.75) {
                rehashKeys(word);
            } else {
                int x = modASCIIHashFunction(word, hashTable.length);
                int y = secondHashFunction(word, hashTable.length);
                for (int i = 0; i<hashTable.length; i++) {
                    int newIndex = (x + i*y) % hashTable.length;
                    if (hashTable[newIndex] == null) {
                        hashTable[newIndex] = word;
                        System.out.println(word +" inserted at location:" +newIndex);
                        break;
                    } else {
                        System.out.println(newIndex +" is occupied. Tryin next empty index..");
                    }

                }
            }
            usedCellNumber++;
        }

    }

    public static <T> void displayHashTable(T[] hashTable) {
        if (hashTable == null) {
            System.out.println("\nHashTable does not exists");
            return;
        } else {
            System.out.println("\n----------HashTable----------");
            for (int i=0; i<hashTable.length; i++) {
                System.out.println("Index " + i + ", key:" + hashTable[i]);
            }
        }
    }

    public static int modASCIIHashFunction(String word, int M) {
        char ch[];
        ch = word.toCharArray();
        int i, sum;
        for (sum=0,i=0; i<word.length(); i++) {
            sum = sum + ch[i];
        }
        return sum % M;
    }

    public static void main(String[] args) {
        DirectChaining directChaining = new DirectChaining(13);
        directChaining.insertHashTable("The");
        directChaining.insertHashTable("quick");
        directChaining.insertHashTable("brown");
        directChaining.insertHashTable("fox");
        directChaining.insertHashTable("over");
        System.out.println("directChaining.displayHashTable");
        displayHashTable(directChaining.hashTable);

        directChaining.deleteKeyHashTable("fox");

        System.out.println("directChaining.displayHashTable after deletion");
        displayHashTable(directChaining.hashTable);

        System.out.println("/************************************************/");

        LinearProbing linearProbing = new LinearProbing(13);
        linearProbing.insertKeyInHashTable("The");
        linearProbing.insertKeyInHashTable("quick");
        linearProbing.insertKeyInHashTable("brown");
        linearProbing.insertKeyInHashTable("fox");
        linearProbing.insertKeyInHashTable("over");
        System.out.println("linearProbing.displayHashTable");
        displayHashTable(linearProbing.hashTable);

        linearProbing.deleteKeyHashTable("fox");

        System.out.println("linearProbing.displayHashTable after deletion");
        displayHashTable(linearProbing.hashTable);

        System.out.println("/************************************************/");

        QuadraticProbing quadraticProbing = new QuadraticProbing(13);
        quadraticProbing.insertKeyInHashTable("The");
        quadraticProbing.insertKeyInHashTable("quick");
        quadraticProbing.insertKeyInHashTable("brown");
        quadraticProbing.insertKeyInHashTable("fox");
        quadraticProbing.insertKeyInHashTable("over");
        System.out.println("quadraticProbing.displayHashTable");
        displayHashTable(quadraticProbing.hashTable);

        System.out.println("/************************************************/");

        DoubleHashing doubleHashing = new DoubleHashing(13);
        doubleHashing.insertKeyInHashTable("The");
        doubleHashing.insertKeyInHashTable("quick");
        doubleHashing.insertKeyInHashTable("brown");
        doubleHashing.insertKeyInHashTable("fox");
        doubleHashing.insertKeyInHashTable("over");
        System.out.println("doubleHashing.displayHashTable");
        displayHashTable(doubleHashing.hashTable);
    }
}
