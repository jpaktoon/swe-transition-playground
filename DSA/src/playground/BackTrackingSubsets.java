package playground;

import java.util.ArrayList;
import java.util.List;

public class BackTrackingSubsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList)); // Add current subset to result
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);  // Include the current number
            backtrack(result, tempList, nums, i + 1);  // Recurse with the next element
            tempList.remove(tempList.size() - 1);  // Backtrack: remove last added number
        }
    }

    public static void main(String[] args) {
        BackTrackingSubsets solution = new BackTrackingSubsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> results = solution.subsets(nums);

        // Print all subsets
        for (List<Integer> subset : results) {
            System.out.println(subset);
        }
    }
}

