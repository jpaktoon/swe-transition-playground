package leetcode;

import leetcode.utils.TreeNode;

public class MaximumDepthOfBinaryTree {
    static int mDepth = 0;

    public int maxDepth(TreeNode root) {
        mDepth = 0;
        maxDepth(root, mDepth);
        return mDepth;
    }

    public int maxDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;
    }

    public void maxDepth(TreeNode root, int depth) {
        if (root == null) {
            mDepth = Math.max(mDepth, depth);
            return;
        }

        depth += 1;
        maxDepth(root.left, depth);
        maxDepth(root.right, depth);
    }
}
