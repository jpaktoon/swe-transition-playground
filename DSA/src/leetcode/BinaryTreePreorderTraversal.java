package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

    // space O(n), time O(n) ,which n is number of nodes
    // has a higher space complexity due to the recursive calls
    // and the creation of new lists for each recursive call.
    public static List<Integer> preorderTraversalV1(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        results.add(root.val);
        results.addAll(preorderTraversalV1(root.left));
        results.addAll(preorderTraversalV1(root.right));
        return results;
    }

    // using helper method preorderHelper.
    // It has a space complexity of O(h), where h is the height of the binary tree,
    // as the maximum depth of the recursive call stack is proportional to the height of the tree.
    // In general, the iterative solution with the helper method is considered more optimal
    // because it has a lower space complexity and avoids the overhead of creating new lists for each recursive call.
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.val); // Add the current node's value to the result list
        preorderHelper(node.left, result); // Traverse the left subtree
        preorderHelper(node.right, result); // Traverse the right subtree
    }

    public static void main(String[] args) {
        List<Integer> results;

        TreeNode zero = null;
        results = preorderTraversal(zero);
        System.out.println(results);

        TreeNode one = new TreeNode(1, null, null);
        results = preorderTraversal(one);
        System.out.println(results);

        TreeNode two = new TreeNode(2, null, null);
        one.left = two;
        results = preorderTraversal(one);
        System.out.println(results);

        /* Let us create following BST
              1
           /     \
          2      3
         /  \      \
       5    6       4 */

        TreeNode three = new TreeNode(3, null, null);
        TreeNode four = new TreeNode(4, null, null);
        TreeNode five = new TreeNode(5, null, null);
        TreeNode six = new TreeNode(6, null, null);

        one.right = three;
        two.left = five;
        two.right = six;
        three.right = four;
        results = preorderTraversal(one);
        System.out.println(results);
    }
}
