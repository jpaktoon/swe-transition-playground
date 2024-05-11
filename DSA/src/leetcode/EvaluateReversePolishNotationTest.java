package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EvaluateReversePolishNotationTest {

    @Test
    public void evalRPN_1(){
        String[] input = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        int result = EvaluateReversePolishNotation.evalRPN(input);
        Assertions.assertEquals(22, result);
    }
}