package leetcode;

import java.util.Set;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        Set<String> operands = Set.of("+","-","*","/");
        Stack<Integer> calculator = new Stack<>();
        int result = 0;
        for (String token : tokens){
            if (!operands.contains(token)) {
                calculator.push(Integer.valueOf(token));
            } else {
                Integer second = calculator.pop();
                Integer first = calculator.pop();
                switch (token) {
                    case "+": result = first + second; break;
                    case "-": result = first - second; break;
                    case "*": result = first * second; break;
                    case "/": result = first / second; break;
                    default: break;
                }
                calculator.push(result);
            }
        }
        return result;
    }

    public static void main(String[] args){

    }
}
