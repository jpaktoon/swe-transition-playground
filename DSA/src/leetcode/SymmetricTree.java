package leetcode;

import com.sun.source.tree.Tree;
import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        ArrayDeque<TreeNode> lefts = new ArrayDeque<>();
        ArrayDeque<TreeNode> rights = new ArrayDeque<>();

        if (root != null) {
            lefts.push(root.left); // to tail
            rights.push(root.right); // to tail
        }

        while (!lefts.isEmpty() && !rights.isEmpty()) {

            if (lefts.size() != rights.size()) return false;

            TreeNode left = lefts.pop(); // from tail
            TreeNode right = rights.poll(); // from head
            if ((left == null && right != null) ||
                    (right == null && left != null) ||
                    (left != null && right != null && left.val != right.val)) {
                return false;
            }
            if (left != null) {
                lefts.push(left.left);
                rights.push(left.right);
            }
            if (right != null) {
                lefts.push(right.left);
                rights.push(right.right);
            }
        }
        return true;
    }
}
