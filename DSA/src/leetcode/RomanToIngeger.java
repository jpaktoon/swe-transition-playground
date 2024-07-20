package leetcode;

import java.util.HashMap;

public class RomanToIngeger {

    public int romanToInt(String s) {
        char[] romans = s.toCharArray();
        int total = 0;
        for (char c : romans) {
            switch (c) {
                case 'I' : total += 1; break;
                case 'V' : if (total % 5 == 0) total += 5; else total += 3; break;
                case 'X' : if (total % 10 == 0) total += 10; else total += 8; break;
                case 'L' : if (total % 50 == 0) total += 50; else total += 30; break;
                case 'C' : if (total % 100 == 0) total += 100; else total += 80; break;
                case 'D' : if (total % 500 == 0) total += 500; else total += 300; break;
                case 'M' : if (total % 1000 == 0) total += 1000; else total += 800; break;
            }
        }
        return total;
    }
}
