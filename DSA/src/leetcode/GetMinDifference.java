package leetcode;

import leetcode.utils.TreeNode;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class GetMinDifference {

    static int minDiff, prev = Integer.MAX_VALUE;

    public static int getMinimumDifference(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        prev = Integer.MAX_VALUE;
        getMin(root);
        return minDiff;
    }

    // In order
    public static void getMin(TreeNode root) {
        if (root == null) {
            return;
        }

        getMin(root.left);

        System.out.println("Node: " + root.val + ", prev: " + prev );
        minDiff = Math.min(minDiff, Math.abs(root.val - prev));
        prev = root.val;

        getMin(root.right);
    }

    public static void main(String[] args) {
        TreeNode node911 = new TreeNode(911);
        TreeNode node701 = new TreeNode(701, null, node911);
        TreeNode node227 = new TreeNode(227);
        TreeNode node104 = new TreeNode(104, null, node227);
        TreeNode root236 = new TreeNode(236, node104, node701);

        System.out.println(getMinimumDifference(root236));
    }
}
