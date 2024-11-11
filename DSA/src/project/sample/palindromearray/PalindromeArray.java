package project.sample.palindromearray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromeArray {

    public static int minOperationsToPalindromeV1(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int operations = 0;
        // Two-pointer approach
        while (left < right) {
            if (arr[left] != arr[right]) {
                // Replace arr[left] or arr[right] based on the smaller value
                int toReplace = Math.min(arr[left], arr[right]);
                int replaceWith = Math.max(arr[left], arr[right]);
                // Replace only the current left and right elements in this iteration
                // since we only need to handle the current mismatch
                for (int i = left; i <= right; i++) {
                    if (arr[i] == toReplace) {
                        arr[i] = replaceWith;
                    }
                }
                operations++;  // Increment the operation count
            }
            // Move pointers inward after processing
            left++;
            right--;
        }
        return operations;
    }

    static Map<Integer, Integer> replacedNums = new HashMap<>();

    public static int minOperationsToPalindromeV2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int operations = 0;
        // Two-pointer approach
        while (left < right) {
            // Check if arr[left] and arr[right] have been replaced previously,
            // if so, use the replaced values from the map.
            int numOnLeft = replacedNums.getOrDefault(arr[left], arr[left]);
            int numOnRight = replacedNums.getOrDefault(arr[right], arr[right]);
            // If there's a mismatch between the two pointers
            if (numOnLeft != numOnRight) {
                // Determine the smaller and larger values between the two
                int toReplace = Math.min(numOnLeft, numOnRight);
                int replaceWith = Math.max(numOnLeft, numOnRight);
                // Store the replacement in the map, so future checks know that
                // all occurrences of `toReplace` should be considered as `replaceWith`
                replacedNums.put(toReplace, replaceWith);
                // Increment the operation count since we performed a replacement
                operations++;
            }
            // Move pointers inward after processing the current pair
            left++;
            right--;
        }
        // Optional: Clear the map after each run to reset the state
        // replacedNums.clear();
        return operations;
    }

    public static void main(String[] args) {
        // Test cases
        List<int[]> testDataV1 = new ArrayList<>();
        testDataV1.add(new int[] {1, 2, 3, 4, 5});
        testDataV1.add(new int[] {1, 1, 1, 2, 4, 5, 6});
        testDataV1.add(new int[] {4, 5, 6, 2, 1, 1, 1});
        testDataV1.add(new int[] {7, 8, 7, 6, 2, 7, 8});
        testDataV1.add(new int[] {7, 8, 1, 8});
        testDataV1.add(new int[] {1, 1, 1, 1, 1, 6});

        List<int[]> testDataV2 = new ArrayList<>();
        testDataV2.add(new int[] {1, 2, 3, 4, 5});
        testDataV2.add(new int[] {1, 1, 1, 2, 4, 5, 6});
        testDataV2.add(new int[] {4, 5, 6, 2, 1, 1, 1});
        testDataV2.add(new int[] {7, 8, 7, 6, 2, 7, 8});
        testDataV2.add(new int[] {7, 8, 1, 8});
        testDataV2.add(new int[] {1, 1, 1, 1, 1, 6});

        for (int[] nums : testDataV1) {
            System.out.println("V1 Minimum operations: " + minOperationsToPalindromeV1(nums));
        }

        for (int[] nums : testDataV2) {
            System.out.println("V2 Minimum operations: " + minOperationsToPalindromeV2(nums));
        }
    }
}

