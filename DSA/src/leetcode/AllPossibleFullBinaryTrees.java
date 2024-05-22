package leetcode;

import com.sun.source.tree.Tree;
import leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPossibleFullBinaryTrees {

    // cache with preset tree for 1 and 3 nodes
    HashMap<Integer, List<TreeNode>> cache = new HashMap<>(Map.ofEntries(
            Map.entry(1, List.of(new TreeNode(0))),
            Map.entry(3, List.of(new TreeNode(0, new TreeNode(0), new TreeNode(0))))));

    // Time O (2^n/2), Space O (n * 2^n/2)
    // Time: Solving the recurrence relation used in our solution
    // with dynamic programming shows that T(n) = T(1) * T(n - 2) + T(3) * T(n - 4) + ... + T(n - 2) * T(1),
    // where n is odd number bounded by (2^n/2)
    // Space : We have declared a memoization list array. Every element of the array has size 2^n/2
    // For n elements it will be n * 2^n/2 in total
    public List<TreeNode> allPossibleFBT(int n) {
        // Assume all n is odd number
        if (cache.containsKey(n)) return cache.get(n);

        List<TreeNode> results = new ArrayList<>();
        int j = n - 1;
        // generate all possible lefts and rights
        // find list of left and right nodes using odd n.
        // for n = 7 and i=2 => i-1 = 1 and n-i = 5
        // so, we are finding the list for every odd pair like (1, 5), (3, 3), (5, 1)
        for (int i = 2; i < n; i = i + 2) {
            // generate all possible lefts
            List<TreeNode> lefts = allPossibleFBT(n - i);
            cache.put(n - i, lefts);
            // generate all possible rights
            List<TreeNode> rights = allPossibleFBT(n - j);
            cache.put(n - j, rights);
            j = j - 2;
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    // create tree
                    TreeNode root = new TreeNode(0, left, right);
                    results.add(root);
                }
            }
        }
        return results;
    }
}
