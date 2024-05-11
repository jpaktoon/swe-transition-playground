package leetcode;

//https://leetcode.com/problems/rotate-array
public class RotateArray {
    // one arrays
    public void rotate1(int[] nums, int k) {
        k = k % nums.length;   // find reminder of k divided by nums.length because k is more than nums.length we don't need to iterate huge iterations.
        reverse(nums,0,nums.length-1);  // first of all reverse entire the array.
        reverse(nums,0,k-1);   // reverse the array from 0th index to k-1 index.
        reverse(nums,k,nums.length-1);   // reverse the array from kth index to araay.length-1 index.
    }
    void reverse(int[] nums,int i,int j){   // Create a reverse function to reverse array from ith position to jth position.
        while(i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
    void swap(int[] nums,int i,int j){   // Create a swap function to swap to array elements.
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // two arrays
    public void rotate2(int[] nums, int k) { // 1-2 ms, O(n + k), space O(k)
//        Input: nums = [1,2,3,4,5,6,7], k = 3
//        Output: [5,6,7,1,2,3,4]
        if ( k > nums.length)
            k = k % nums.length;
        int[] temp = new int[k]; // new arrays size = k i.e. 3
        int shiftOutIndex = k - 1; // index of the new array to start shift to i.e. 2
        int shiftInIndex = nums.length - 1; // index of the existing array to start shift to i.e. 6
        // start at the end of the existing array i.e. index 6
        for (int i = nums.length - 1; i >= 0; i--) { // until index 0, decrease by 1
            if (shiftOutIndex >= 0) { // 2 (t)
                temp[shiftOutIndex] = nums[i];
                // temp[2] = nums[6], i.e. int 7
                // temp[1] = nums[5]
                // temp[0] = nums[4]
                // false
                shiftOutIndex--; // become 1
            } else {
                // shiftInIndex = 6 , i = 3
                // 5, 2
                // 4, 1
                // 3, 0
                nums[shiftInIndex] = nums[i];
                shiftInIndex--;
            }
        }
        // set back temp to original arrays
        for (int i = 0; i < k; i++){
            if (i < nums.length) {
                nums[i] = temp[shiftOutIndex+i+1];
            }
        }
    }
}
