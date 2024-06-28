package neetcode;

import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.ArrayDeque;

public class SameBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//    public static boolean isSameTree(TreeNode p, TreeNode q) {
//        Deque<TreeNode> pQueue = new ArrayDeque<>();
//        Deque<TreeNode> qQueue = new ArrayDeque<>();
//
//        pQueue.add(p);
//        qQueue.add(q);
//        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
//            TreeNode a = pQueue.remove();
//            TreeNode b = qQueue.remove();
//            if (!isSameNode(a, b)) {
//                return false;
//            } else {
//                if (a.left != null) {
//                    pQueue.add(a.left);
//                }
//
//                if (a.right != null) {
//                    pQueue.add(a.right);
//                }
//
//                if (b.left != null) {
//                    qQueue.add(b.left);
//                }
//
//                if (b.right != null) {
//                    qQueue.add(b.right);
//                }
//            }
//        }
//
//        return pQueue.isEmpty() && qQueue.isEmpty();
//    }
//
//    public static boolean isSameNode(TreeNode a, TreeNode b) {
//        return (a.val == b.val) && (a.left == b.left) && (a.right == b.right);
//    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(4));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        isSameTree(p, q);
    }
}
