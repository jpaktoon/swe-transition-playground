package leetcode;

import com.sun.source.tree.Tree;
import leetcode.utils.TreeNode;

import java.util.Arrays;

public class ConvertSortedArrayToBinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        // [0] --> fix at 0
        // [] --> return null
        if (nums.length == 0 ) {
            return null;
        } else if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        return sortedArrayToBST(nums, 0, nums.length-1); // from, to inclusive
    }

    public static TreeNode sortedArrayToBST(int[] nums, int from, int to) {
        if (from > to ) {
            return null;
        } else if (from == to) {
            return new TreeNode(nums[from]);
        }

        // [-10, -3, 0, 5, 9] --> 5 / 2 = 2
        // [-10, -3, 0, 1, 5, 9] --> 6 / 2 = 3
        // [1, 2] --> 2 / 2 = 1

        // r1 [-10, -3, 0, 1, 5, 9], from = 0, to = 5, (5-0+1) / 2 = 3 -> root(1)
        // left -> [-10, -3, 0], from = 0, to = 2, (2-0+1) / 2 = 1 -> new root for left = root(-3)
        // right -> [5, 9], from = 4, to = 5, (5-4+1) / 2 = 1

        //int midIndex = ((from + to) / 2); // mid index -> should be root
        int midIndex = to + ((from - to) / 2); // https://stackoverflow.com/questions/6735259/calculating-mid-in-binary-search
        TreeNode root = new TreeNode(nums[midIndex]);
        root.left = sortedArrayToBST(nums, from, midIndex - 1);
        root.right = sortedArrayToBST(nums, midIndex + 1, to);
        return root;
    }

    public static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode balancedTree = sortedArrayToBST(new int[] {-10, -3, -2, 0, 1, 2, 5, 9 ,10, 11});

        inOrder(balancedTree);
    }
}
