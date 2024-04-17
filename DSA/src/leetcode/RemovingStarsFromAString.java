package leetcode;

import java.util.Stack;

public class RemovingStarsFromAString {
    public static String removeStars(String s) {
        Stack<Character> items = new Stack<>();
        char[] charArray = s.toCharArray();
        for (char i : charArray){
            if (i == '*') {
                items.pop();
            } else {
                items.push(i);
            }
        }
        StringBuilder result = new StringBuilder(); // this is much faster than string += ??
        for (char item : items) {
            result.append(item);
        }
        return result.toString();
    }

    public static void main(String[] args){
        removeStars("leet**cod*e");
    }
}
