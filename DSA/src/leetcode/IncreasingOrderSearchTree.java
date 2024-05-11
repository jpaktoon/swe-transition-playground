package leetcode;

import leetcode.utils.TreeNode;

public class IncreasingOrderSearchTree {
    static TreeNode head = null;
    public static TreeNode increasingBSTV1(TreeNode root) {
        head = null;
        if (root == null) return null;
        reverseInorder(root);
        return head;
    }

    public static void reverseInorder(TreeNode root) {
        if (root == null) return;
        reverseInorder(root.right); // O (n)
        // LinkedList prepend O (1)
        TreeNode tempNode = head;
        head = new TreeNode(root.val, null, null);
        head.right = tempNode;
        // OR head = new TreeNode(root.val, null, head);
        reverseInorder(root.left); // O (n)
    }

    static TreeNode prev = null; // a pointer
    public static TreeNode increasingBSTV2(TreeNode root) {
        TreeNode dummy = new TreeNode();
        prev = dummy;
        inorderTraversal(root);
        return dummy.right;
    }

    private static void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left);

        // Update the links
        prev.right = node;
        node.left = null;
        prev = node;

        inorderTraversal(node.right);
    }

    public static void printAllRights(TreeNode root) {
        if (root == null) return;
        while (root.right != null) {
            System.out.print(root.val + " -> ");
            root = root.right;
        }
        System.out.println(root.val);
    }

    public static void main(String[] args) {

        TreeNode zero = null;
        printAllRights(increasingBSTV2(zero));

        TreeNode one = new TreeNode(1, null, null);
        printAllRights(increasingBSTV2(one));

        TreeNode two = new TreeNode(2, null, null);
        two.left = one;
        printAllRights(increasingBSTV2(two));

        /* Let us create following BST
              4
           /     \
          2      5
         /  \      \
       1    3       7
                  /   \
                 6     8*/

        TreeNode one1 = new TreeNode(1, null, null);
        TreeNode two1 = new TreeNode(2, null, null);
        TreeNode three1 = new TreeNode(3, null, null);
        TreeNode four1 = new TreeNode(4, null, null);
        TreeNode five1 = new TreeNode(5, null, null);
        TreeNode six1 = new TreeNode(6, null, null);
        TreeNode seven1 = new TreeNode(7, null, null);
        TreeNode eight1 = new TreeNode(8, null, null);

        four1.left = two1;
        four1.right = five1;

        two1.left = one1;
        two1.right = three1;

        five1.right = seven1;

        seven1.left = six1;
        seven1.right = eight1;

        printAllRights(increasingBSTV2(four1));
    }
}
