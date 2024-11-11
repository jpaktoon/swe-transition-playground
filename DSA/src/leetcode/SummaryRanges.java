package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {

    public static List<String> summaryRanges(int[] nums) {
        List<String> formatted = new ArrayList<>();
        if (nums.length == 0) return formatted;

        List<int[]> results = new ArrayList<>();
        int startRange = nums[0];
        //int previous = startRange;
        int endRange;

        // [0,1,2,4,5,7], length = 6
        // startRange = 0, undefined endRange
        // i = 1, isEndArray = false
        // i = 2, previous = 2
        // i = 3, endRange = 2, {0, 2}, startRange = 4, previous = 4
        // i = 4, previous = 5
        // i = 5, endRange = 5, {4, 5}, startRange = 7, previous = 7
        for (int i = 1; i <= nums.length - 1; i++) {
            if (nums[i] - 1 != nums[i - 1]) {
                //Save range
                endRange = nums[i - 1];
                results.add(new int[] {startRange, endRange});
                //Start new one
                startRange = nums[i];
            }
            // End of array
            if ( i == nums.length - 1)
            {
                endRange = nums[i];
                results.add(new int[] {startRange, endRange});
            }
        }

        for (int[] result : results) {
            if (result[0] != result[1]) formatted.add(result[0] + "->" + result[1]);
            else formatted.add(String.valueOf(result[0]));
        }
        return formatted;
    }
}
