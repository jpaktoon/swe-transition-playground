package playground;

import java.awt.desktop.SystemSleepEvent;

public class FuzzySearch {

    public static boolean naiveStringMatcher(String t, String p) {
        int n = t.length(); // "ACAACA" : 6
        int m = p.length(); // "AAC" : 3
        // n - m = 3, loop 0, 1, 2, 3
        // i is the start index of the substring
        for (int i = 0; i <= n - m; i++) {
            // first iterate - i = 0, m (fixed) = 3
            if (p.equals(t.substring(i, i+m))) {
                return true;
            };
        }
        return false;
    }

    public static void main(String[] args) {
        String text = "ACAACA";

        String pattern = "AAC";
        System.out.println("AAC: " + naiveStringMatcher(text, pattern));

        String pattern2 = "ACA";
        System.out.println("ACA: " + naiveStringMatcher(text, pattern2));

        String pattern3 = "AACA";
        System.out.println("AACA: " + naiveStringMatcher(text, pattern3));

        String pattern4 = "B";
        System.out.println("B: " + naiveStringMatcher(text, pattern4));

        String pattern5 = "CAC";
        System.out.println("CAC: " + naiveStringMatcher(text, pattern5));
    }
}
