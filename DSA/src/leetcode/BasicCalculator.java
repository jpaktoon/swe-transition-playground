package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// https://leetcode.com/problems/basic-calculator/description/
public class BasicCalculator {

    public static int calculateV1(String s){
        final Set<String> validOperators = new HashSet<>(Arrays.asList("+", "-", "(", ")", " "));
        final Deque<Integer> numbers = new ArrayDeque<>();
        final Deque<String> operators = new ArrayDeque<>();

        boolean shouldAddZero = true;

        numbers.push(0); // to handle '-' as a unary operation
        String regex = String.join("|", validOperators);
        String[] sc = s.split("(?<=[" + regex + "])|(?=[" + regex + "])");

        //System.out.println(Arrays.toString(sc));

        for (String i : sc) {
            if (!i.isBlank()) {
                if (i.equals("+") || i.equals("-") || i.equals("(")) {
                    operators.push(i);
                    continue;
                } else if (i.equals(")")) {
                    if (!operators.isEmpty() && operators.peek().equals("(")) {
                        operators.pop(); // this one should be '('
                    }
                }

                if (!operators.isEmpty() && !operators.peek().equals("(")) {
                    String operator = operators.pop();
                    int secondNumber = i.equals(")") ? numbers.pop() : Integer.valueOf(i);
                    int firstNumber = numbers.pop();
                    int result = numberCalculate(firstNumber, secondNumber, operator);
                    numbers.push(result);
                } else if (!validOperators.contains(i)){
                    numbers.push(Integer.valueOf(i));
                }
            }
        }
        return numbers.pop();
    }

    public static int numberCalculate(int first, int second, String operator) {
        return operator.equals("+") ? first + second : first - second;
    }

    public int evaluateExpr(Stack<Object> stack) {

        // If stack is empty or the expression starts with
        // a symbol, then append 0 to the stack.
        // i.e. [1, '-', 2, '-'] becomes [1, '-', 2, '-', 0]
        if (stack.empty() || !(stack.peek() instanceof Integer)) {
            stack.push(0);
        }

        int res = (int) stack.pop();

        // Evaluate the expression till we get corresponding ')'
        while (!stack.empty() && !((char) stack.peek() == ')')) {

            char sign = (char) stack.pop();

            if (sign == '+') {
                res += (int) stack.pop();
            } else {
                res -= (int) stack.pop();
            }
        }
        return res;
    }

    public int calculate(String s) {

        int operand = 0;
        int n = 0;
        Stack<Object> stack = new Stack<Object>();

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                // Forming the operand - in reverse order.
                operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {

                    // Save the operand on the stack
                    // As we encounter some non-digit.
                    stack.push(operand);
                    n = 0;
                    operand = 0;

                }
                if (ch == '(') {

                    int res = evaluateExpr(stack);
                    stack.pop();

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res);

                } else {
                    // For other non-digits just push onto the stack.
                    stack.push(ch);
                }
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand);
        }

        // Evaluate any left overs in the stack.
        return evaluateExpr(stack);
    }

    public static void main(String[] args) {
        BasicCalculator calc = new BasicCalculator();
        String s0 = "1 + 1";
        System.out.println(calc.calculate(s0)); // 2

        String s1 = " 2-1 + 2 ";
        System.out.println(calc.calculate(s1)); // 3

        String s3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calc.calculate(s3)); // 23

        String s4 = "-1";
        System.out.println(calc.calculate(s4)); // -1

        String s5 = "-(2+1)+2";
        System.out.println(calc.calculate(s5)); // -1

        String s6 = "1-(1-2)";
        System.out.println(calc.calculate(s6)); // 2

        String s7 = "1-(     -2)";
        System.out.println(calc.calculate(s7)); // 3

        String s8 = "1-(-(-(1-2)))";
        System.out.println(calc.calculate(s8)); // 2

    }
}
