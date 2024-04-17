package playground;

public class StringCheatSheet {

    public static void main(String[] args) {
        // String
        String str1 = "Hello"; String str2 = "Hello";
        String str3 = new String("Hello"); // not recommend if not necessary. Only a specfic cases such as new String(char[]);
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
    }
}
