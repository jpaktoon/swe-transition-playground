package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/basic-calculator/description/
public class BasicCalculator {

    public static int calculate(String s){
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

    public static void main(String[] args) {
        String s0 = "1 + 1";
        System.out.println(calculate(s0)); // 2

        String s1 = " 2-1 + 2 ";
        System.out.println(calculate(s1)); // 3

        String s3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculate(s3)); // 23

        String s4 = "-1";
        System.out.println(calculate(s4)); // -1

        String s5 = "-(2+1)+2";
        System.out.println(calculate(s5)); // -1

        String s6 = "1-(1-2)";
        System.out.println(calculate(s6)); // 2

//        String s7 = "1-(     -2)";
//        System.out.println(calculate(s7)); // -3

//        String s8 = "1-(-(-(1-2)))";
//        System.out.println(calculate(s8)); // 2

    }
}
