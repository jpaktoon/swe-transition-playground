package leetcode;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {

    static Map<Integer, char[]> digitSymbols = new HashMap<>() {{
        put(0, new char[] {'I', 'V', 'X'});    // 1, 5, 10
        put(10, new char[] {'X', 'L', 'C'});   // 10, 50, 100
        put(100, new char[] {'C', 'D', 'M'});  // 100, 500, 1000
        put(1000, new char[] {'M'});           // 1000
    }};

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int[] units = new int[] {1000, 100, 10};
        for (int unit : units) {
            int count = num / unit;
            result.append(repeat(count, unit));
            num = num % unit;
        }
        if (num > 0) {
            result.append(repeat(num, 0));
        }
        return result.toString();
    }

    public String repeat(int count, int digit) {
        char[] symbols = digitSymbols.get(digit);
        StringBuilder sb = new StringBuilder();
        if (count < 4) {
            sb.append(String.valueOf(symbols[0]).repeat(Math.max(0, count)));
        } else if (count == 4) {
            sb.append(symbols[0]).append(symbols[1]);
        } else if (count == 5) {
            sb.append(symbols[1]);
        } else if (count < 9) {
            int overFive = count - 5;
            sb.append(symbols[1]);
            sb.append(String.valueOf(symbols[0]).repeat(overFive));
        } else if (count == 9) {
            sb.append(symbols[0]).append(symbols[2]);
        }
        return sb.toString();
    }
}
