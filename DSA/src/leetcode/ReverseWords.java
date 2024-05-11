package leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150
public class ReverseWords {

    // In place with recusive call
    public static String reverseWords(String s) {
        if (s.isBlank()) return s;

        char[] c = s.toCharArray();

        // reverse the whole words
        char[] w = reverse(c, c.length).toCharArray();

        // Find start and end index
        int start = 0;
        int end = 0;
        for (int i = 0; i < w.length; i++) {
            if (end > start && (i == w.length - 1 || w[i + 1] == ' ')) {
                char[] word = Arrays.copyOfRange(w, start, end+1);
                char[] reverseWord = reverse(word, word.length).toCharArray();
                for (int j = 0; j < reverseWord.length; j++) {
                    w[start + j] = reverseWord[j];
                }
            } else {
                end = i + 1;
            }
            if (w[i] == ' ') start = i + 1; // next char of space
        }

        return String.valueOf(w);
    }

    public static String reverse(char[] c, int n) {
        if (n == 1) return String.valueOf(c[0]);
        return c[n - 1] + reverse(c, n - 1);
    }

    // String split, new String
    public String reverseWords2(String s) {
        if (s == null) return null;

        String[] items = s.split(" ");
        StringBuilder t = new StringBuilder();
        for (int i = items.length - 1; i >=0; i--) {
            if (!items[i].isBlank()) {
                if (i != items.length - 1) t.append(" ");
                t.append(items[i]);
            }
        }
        return t.toString();
    }

    // Test cases
    // "This is a book" - pass
    // "" - pass
    // null - pass
    // "This"
    // "    x a b   c  "

    public static void main(String[] args) {
        String result = reverseWords(" dsd test");
        System.out.println(result);
    }
}
