package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    // merge sort array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//        Output: [1,2,2,3,5,6]
        int j = 0;
        for (int i = m; i < m + n; i++) {
            if (nums1[i] == 0) {
                nums1[i] = nums2[j];
                j++;
            }
        }

        Arrays.sort(nums1);
    }

    public static int removeElement() {

        //Input: nums = [3,2,2,3], val = 3
        //Output: 2, nums = [2,2,_,_]
        // eage cases
        // - no value equal
        // - all value equal
        // - empty array

        int[] nums = new int[] {3,2,2,2,3,4,5,6,2,2};
        int val = 2;

        int i = 0;
        int j = nums.length - 1;
        while (j >= i) { // O(n)
            if (nums[i] == val && nums[j] != val) {
                nums[i] = nums[j];
                nums[j] = val;
                i++;
                j--;
            } else if (nums[i] == val && nums[j] == val) {
                j--;
            } else if (nums[i] != val && nums[j] != val) {
                i++;
            }
            else {
                i++;
                j--;
            }
        }

        return i;
    }

    public static int removeDuplicates() {

        int[] nums = new int[] {1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 5};

        int x = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1] && x == 0) {
                x = i + 1; // get the first position to be replace
            }

            if (nums[i] != nums[i+1] && x != 0) {
                nums[x] = nums[i+1]; // replace
                x++; // move to next
            }
        }
        return x == 0 ? nums.length: x;
    }

    public static int removeDuplicates2() {
//        Input: nums = [1,1,1,2,2,3]
//        Output: 5, nums = [1,1,2,2,3,_]
        int[] nums = new int[] {1, 2, 3};


        int threshold = 2; // can be passed with external param e.g. 1, 2, 10 etc.
        int counter = 1;
        int x = 0;
        int interested = -1; // the array can contain -1 but we can ignore it by setting the interested with the first element
        for (int i = 0; i < nums.length; i++) {

            if (i != 0 && nums[i] == interested) {
                counter++;
            } else {
                interested = nums[i];
                counter = 1;
            }

            if (counter <= threshold) {
                nums[x] = nums[i];
                x++;
            }
        }

        return x == 0 ? nums.length : x;
    }

    public static void duplicateZeros() {
        int[] A = new int[]{0,2,3,0,4,5,0};

        int n = A.length, count = 0;

        for (int num : A) if (num == 0) count++;
        int i = n - 1;
        int write = n + count - 1;

        while (i >= 0 && write >= 0)  {

            if (A[i] != 0) { // Non-zero, just write it in
                if (write < n) A[write] = A[i];

            } else { // Zero found, write it in twice if we can
                if (write < n) A[write] = A[i];
                write--;
                if (write < n) A[write] = A[i];
            }

            i--;
            write--;
        }

        System.out.println('x');
    }

    public static void main(String[] args) {
        removeElement();
    }
}
