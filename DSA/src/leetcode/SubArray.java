package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubArray {

    public static List<List<Integer>> subSet(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();

        subsets.add(new ArrayList<>()); // add empty set, it is subset for all sets

        for (int num : nums) {
            int count = subsets.size();
            for (int i = 0; i <= count - 1; i++) {
                List<Integer> newSubSet = new LinkedList<>(subsets.get(i));
                newSubSet.add(num);
                subsets.add(newSubSet);
            }
        }
        return subsets;
    }

    public static List<List<Integer>> adjacentSubSet(int[] nums){
        List<List<Integer>> subsets = new ArrayList<>();
        int start = 0;
        for (int num : nums) {
            int end = subsets.size();
            for (int i = start; i <= end - 1; i++) {
                List<Integer> newSubSet = new LinkedList<>(subsets.get(i));
                newSubSet.add(num);
                subsets.add(newSubSet);
            }
            subsets.add(List.of(num)); // add one num to subsets
            start = end;
        }
        subsets.add(new ArrayList<>()); // add empty set
        return subsets;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4};
        System.out.println(subSet(nums));
        System.out.println(adjacentSubSet(nums));
    }
}
