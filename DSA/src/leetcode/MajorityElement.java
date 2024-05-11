package leetcode;

import java.util.HashMap;
import java.util.Arrays;

// https://leetcode.com/problems/majority-element
public class MajorityElement {

    // Moore Voting Algorithm - O(n)
    public int majorityElement0(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    // QuickSort, avg/best at O(n log n), worst at O(n^2)
    public int majorityElement1(int[] nums) { // 4ms, avg/best at O(n log n), worst at O(n^2)
        Arrays.sort(nums); // QuickSort
        int n = nums.length;
        return nums[n/2];
    }

    // HashMap with n/2 - O(n)
    public int majorityElement2(int[] nums) { // 18ms
        int n = nums.length / 2;
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums) {
            if (numCounts.containsKey(num)) {
                if (numCounts.get(num) >= n) {
                    return num; // majority
                } else {
                    numCounts.replace(num, numCounts.get(num) + 1);
                }
            } else {
                numCounts.put(num, 1);
            }
        }
        return nums[0];
    }

    // HashMap find max - O(n)
    public int majorityElement3(int[] nums) { // 14ms
        HashMap<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums) {
            if (numCounts.containsKey(num)) {
                numCounts.replace(num, numCounts.get(num) + 1);
            } else {
                numCounts.put(num, 1);
            }
        }

        int max = 0;
        Integer num = null;
        for (int key : numCounts.keySet()) {
            int count = numCounts.get(key);
            if (count > max) {
                max = count;
                num = key;
            }
        }

        return num;
    }

    // 2 loops worst solution - O(n^2)
    public int majorityElement4(int[] nums) {
        int peek = 0;
        int prevCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count > prevCount) {
                peek = nums[i];
                prevCount = count;
            }
        }
        return peek;
    }
}
