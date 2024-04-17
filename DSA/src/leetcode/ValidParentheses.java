package leetcode;

import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {
        if (s.isEmpty() || s.length() == 1) return false;

        char[] items = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char i : items){
            if (")]}".contains(String.valueOf(i)) && !stack.isEmpty()) {
                // end
                char begin = stack.pop();
                switch (i) {
                    case ')': if (begin != '(') {return false;} break;
                    case '}': if (begin != '{') {return false;} break;
                    case ']': if (begin != '[') {return false;} break;
                }
            } else {
                // begin
                stack.push(i);
            }
        }
        // has begin but no end - return false
        return stack.isEmpty();
    }
}
