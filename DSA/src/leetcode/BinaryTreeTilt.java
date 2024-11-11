package leetcode;

import leetcode.utils.TreeNode;

public class BinaryTreeTilt {

//    public int findTilt(TreeNode root) {
//        if (root == null) return 0;
//
//        int leftValue = root.left != null ? sum(root.left) : 0;
//        int rightValue = root.right != null ? sum(root.right) : 0;
//
//        return Math.abs(leftValue - rightValue) + findTilt(root.left) + findTilt(root.right);
//    }
//
//    public int sum(TreeNode root) {
//        if (root == null) return 0;
//        return root.val + sum(root.left) + sum(root.right);
//    }

    private int totalTilt = 0;

    protected int valueSum(TreeNode node) {
        if (node == null)
            return 0;

        int leftSum = this.valueSum(node.left);
        int rightSum = this.valueSum(node.right);
        int tilt = Math.abs(leftSum - rightSum);
        this.totalTilt += tilt;

        // return the sum of values starting from this node.
        return node.val + leftSum + rightSum;
    }

    public int findTilt(TreeNode root) {
        this.totalTilt = 0;
        this.valueSum(root);
        return this.totalTilt;
    }

}
