package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    // Pre-order until leaf - add to the List if it meets the condition

    List<List<Integer>> allResults = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        preOrder(root, targetSum, new ArrayList<>());
        return allResults;
    }

    public void preOrder(TreeNode root, int targetSum, List<Integer> results) {
        if (root == null) return;
        results.add(root.val);
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                List<Integer> tempList = new ArrayList<>(results);
                allResults.add(tempList);
            }
            results.remove(results.size() - 1);
            return;
        }
        preOrder(root.left, targetSum - root.val, results);
        preOrder(root.right, targetSum - root.val, results);
        results.remove(results.size() - 1);
    }

    public static void main(String[] args) {
        // root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        TreeNode node11 = new TreeNode(11, new TreeNode(7), new TreeNode(2));
        TreeNode node4L = new TreeNode(4);
        node4L.left = node11;

        TreeNode node4R = new TreeNode(4, new TreeNode(5), new TreeNode(1));
        TreeNode node8 = new TreeNode(8, new TreeNode(13), node4R);

        TreeNode root = new TreeNode(5, node4L, node8);

        PathSumII ps2 = new PathSumII();
        List<List<Integer>> allResults = ps2.pathSum(root, 22);

        System.out.println(allResults);
    }
}
