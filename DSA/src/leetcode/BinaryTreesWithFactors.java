package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BinaryTreesWithFactors {
    public int numFactoredBinaryTrees(int[] arr) {
        HashMap<Integer, Long> cache = new HashMap<>();
        Arrays.sort(arr); // Quick sort O (n)
        // Now it is a sorted array
        int mod = 1000000007;
        long allPos = 0;
        for (int num : arr) { // O (n)
            cache.put(num, 1L); // add one node tree for itself
            // we only need to loop until sqrt of num
            int limit = (int)Math.sqrt(num);
            for (int left : cache.keySet()) { // O (m)
                // num / left is the right
                int right = num / left;
                if (left > limit) {
                    continue;
                }
                if (num % left == 0 && cache.containsKey(right)) {
                    // if left and right is the same so it will be the same patterns
                    // else it will be double
                    int factor = left == right ? 1 : 2;
                    long pos = (cache.get(num) + cache.get(left) * cache.get(right) * factor) % mod;
                    cache.put(num, pos);
                }
            }
            allPos = (allPos + cache.get(num)) % mod;
        }
        return (int) allPos;
    }
}
