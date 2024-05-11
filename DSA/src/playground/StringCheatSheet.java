package playground;

import java.util.ArrayList;
import java.util.Arrays;

public class StringCheatSheet {

    public static void main(String[] args) {
        // String
        String str1 = "Hello"; String str2 = "Hello";
        String str3 = new String("Hello"); // time: O(n), space O(n) - not recommend if not necessary. Only a specific cases such as new String(char[]);
        // String pool
        System.out.println(str1 == str2); // true - O(1)
        System.out.println(str1 == "Hello"); // true - O(1)
        System.out.println(str1.equals(str2)); // true - recommended replace '==' with 'equal()' - time: O(n), space O(1)
        // Heap memory - beware
        System.out.println(str1 == str3); // false - O(1)
        System.out.println(str1.equals(str3)); // true - time: O(n), space O(1)
        System.out.println(str1.compareTo(str3)); // int: 0 means equal - O(n)
        System.out.println(str1.compareTo("test")); // int: -44 negative means not equal - O(n)
        System.out.println(str1.equalsIgnoreCase("HELLO")); // true - O(n)
        // Char Array
        char[] strCharArray = str1.toCharArray(); // char[]: [H, e, l, l, o] - time: O(n), space O(n)
        System.out.println(new String(strCharArray)); // String: "Hello"
        // Helpers
        System.out.println(str1.length()); // int: 5 - O(1)
        System.out.println(str1.charAt(1)); // char: 'e'  - O(1)
        System.out.println(str1.toUpperCase()); // String: "HELLO" - time: O(n), space O(n)
        System.out.println(str1.toLowerCase()); // String: "hello" - time: O(n), space O(n)
        System.out.println(str1.isEmpty()); // false - O(1)

        String str = "Hello I am engineer";
        System.out.println(str.trim()); // String: "Hello I am engineer" beware it is the same since trim() will just remove the Leading and Trailing white spaces - time: O(n), space O(n)
        System.out.println(str.replaceAll("\\s", "")) ; // String: "HelloIamengineer" - time: O(n), space O(n)
        System.out.println(str.replace("engineer", "tester")); // String: "Hello I am tester" - time: O(n), space O(n)
        System.out.println(str.contains("engineer")); // true - time: O(n), space O(1)
        System.out.println(str.contains("Engineer")); // false - time: O(n), space O(1)
        System.out.println(str.indexOf("am")); // int: 8 - time: O (n * m), space O(1)
        System.out.println(str.indexOf("am", 9)); // from index 9, so it return -1 for not found - O (n * m), space O(1)
        System.out.println(str.lastIndexOf("ee")); // int: 16 - O (n * m), space O(1)
        System.out.println(str.endsWith("er")); // true
        System.out.println(str.startsWith("Hell")); // true - O (n * m), space O(1)
        System.out.println(str.substring(0, 5)); // String: "Hello" from index 0 to 4 - time: O(1), space O(n) new string creation
        String[] strSplit = str.split(" "); // String[]: ["Hello", "I", "am", "engineer"] - time: O(n), space O(n)

        String strWithPunctuation = "Hello I'm number 1 engineer";
        // remove Punctuation, using regex to replace all for non char or space or number
        System.out.println(strWithPunctuation.replaceAll("[^a-zA-Z\\s\\d]", "")); // Using regex - O (??), String "Hello Im engineer"

        String a = "a"; String b = "bb";
        System.out.println(a.concat(b)); // String: abb - time: o(n + m), space: o(n + m)
        System.out.println(a); // still be "a"

        // String class helpers
        System.out.println(String.join(" ", strSplit)); // String: "Hello I am engineer" - time: o(n * m), space: o(n + m)
        // Type conversion
        System.out.println(String.valueOf(5000)); // String: "5000"
        System.out.println(Integer.valueOf("5000")); // Integer: 5000
        System.out.println(String.valueOf(88.999)); // String: "88.999"
        System.out.println(Double.valueOf("88.999")); // Double: 88.999

        // Reverse String
        System.out.println(reverseString("Hello")); // "olleH"
        System.out.println(reverseString("am")); // "ma"
        System.out.println(reverseString("b")); // "b"
        System.out.println(reverseString("")); // ""
        System.out.println(reverseString(null)); // null

        // Reverse words
        System.out.println(reverseWords("I am engineer")); // "I ma reenigne"
        System.out.println(reverseWords("ab")); // "ba"
        System.out.println(reverseWords("a")); // "a"
        System.out.println(reverseWords("")); // ""
        System.out.println(reverseWords(null)); // null


        System.out.println("a".repeat(5)); // "aaaaa"
    }

    // Prepend using string builder
    public static String prependPath(String dir) {
        StringBuilder cPath = new StringBuilder();
        cPath.insert(0, "/" + dir);
        return cPath.toString();
    }
    // Reverse words
    public static String reverseWords(String words){
        if (words == null) return null;

        String[] items = words.split(" ");
        for (int i = 0; i < items.length; i++) {
            String reversed = reverseString(items[i]);
            items[i] = reversed;
        }
        return String.join(" ", items);
    }

    // Reverse String
    public static String reverseString(String word){
        if (word == null) return null;
        // time : O(n), space : O(1)
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length / 2; i++) {
            char temp = letters[letters.length - i - 1];
            letters[letters.length - i - 1] = letters[i];
            letters[i] = temp;
        }
        return new String(letters);
    }

    public String reverse(String str)
    {
        if (str.isEmpty())
            return str;
        //Calling Function Recursively
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public boolean isPalindrome(String s) {
        char[] c = s.toCharArray();
        return isPalindrome(c, c.length);
    }

    public boolean isPalindrome(char[] c, int n) {
        if (n <=1 ) return true;
        return c[c.length - n] == c[n-1] && isPalindrome(c, n-1);
    }
}
