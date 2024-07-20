package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

    // 47 ms
    // Beats 34.49%
    // O(n^2)
    static HashSet<Integer> calculated = new HashSet<>();
    public static List<List<Integer>> threeSum(int[] nums) {
        // set targat to each value in array and find the match
        // sort the array
        // two pointers to sum pairs for remaining values in the array - in sperate function
        calculated = new HashSet<>();
        Arrays.sort(nums);
        HashSet<List<Integer>> finalResults = new HashSet<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            int target = nums[i];
            if (!calculated.contains(target)) {
                List<List<Integer>> results = twoSum(Arrays.copyOfRange(nums, i + 1, nums.length), target);
                calculated.add(target);
                if (!results.isEmpty()) {
                    finalResults.addAll(results);
                }
            }
        }
        return finalResults.stream().toList();
    }

    public static List<List<Integer>> twoSum(int[] numbers, int target) {
        //Arrays.sort(numbers); // O(n)
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) { // O(n)
            if (numbers[i] + numbers[j] == -1 * target) {
                Integer[] resultArray = new Integer[]{target, numbers[i], numbers[j]};
                Arrays.sort(resultArray);
                result.add(Arrays.asList(resultArray));
                i++;
                j--;
            }
            else if (numbers[i] + numbers[j] > -1 * target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //int[] input = new int[] {-1, 0, 1, 2, -1, -4};
        int[] input = new int[] {0, 0, 0};
        //int[] input = new int[] {3, 0, -2, -1 , 1, 2};
        //int[] input = new int[] {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        List<List<Integer>> results = threeSum(input);
        System.out.println(results);
    }
}
