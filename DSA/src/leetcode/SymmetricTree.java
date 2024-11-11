package leetcode;

import com.sun.source.tree.Tree;
import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class SymmetricTree {

    ArrayDeque<Integer> queue = new ArrayDeque<>();
    Integer topRootValue = 0;
    Boolean shouldPop = false;
    Boolean isSymmetric = true;

    public boolean isSymmetric(TreeNode root) {
        topRootValue = root.val;
        inOrderTraversal(root);
        return isSymmetric;
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        if (shouldPop) {
            if (root.val != queue.pop()) {
                isSymmetric = false;
                return;
            }
        } else {
            if (root.val == topRootValue) {
                shouldPop = true;
            } else {
                queue.push(root.val);
            }
        }
        inOrderTraversal(root.right);
    }

}
