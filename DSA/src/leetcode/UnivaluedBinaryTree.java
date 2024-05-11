package leetcode;

import leetcode.utils.TreeNode;

// https://leetcode.com/problems/univalued-binary-tree

public class UnivaluedBinaryTree {

    static int counter = 0;

    public static boolean isUnivalTreeV1(TreeNode root) {
        counter++; // check how many recursive called.
        if (root == null) return true;
        boolean isEqual = true; // assume equal for leaf node
        if (root.left != null) isEqual = root.val == root.left.val;
        if (root.right != null) isEqual = isEqual && root.val == root.right.val;
        return isEqual && isUnivalTreeV1(root.left) && isUnivalTreeV1(root.right);
    }

    public static boolean isUnivalTreeV2(TreeNode root) {
        counter++; // check how many recursive called.
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        return isUnivalTreeV1(root.left) && isUnivalTreeV1(root.right);
    }

    public static boolean isUnivalTreeV3(TreeNode root) {
        if (root == null) return true;
        return compare(root, root.val);
    }

    public static boolean compare(TreeNode root, int val) {
        counter++; // check how many recursive called.
        if (root == null) return true;
//        if (root.val != val) return false;
//        else return compare(root.left, val) && compare(root.right, val);
        return root.val == val && compare(root.left, val) && compare(root.right, val);
    }

    public static void main(String[] args) {

        TreeNode zero = null;
        System.out.println(isUnivalTreeV3(zero));

        TreeNode one = new TreeNode(1, null, null);
        System.out.println(isUnivalTreeV3(one));

        TreeNode two = new TreeNode(2, null, null);
        one.left = two;
        System.out.println(isUnivalTreeV3(one));

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
        System.out.println(isUnivalTreeV3(one));

        /* Let us create following BST
              1 (1)
           /     \
          1 (2)     1 (3)
         /      \       \
       1 (4)   1 (5)      1 (6)
                          /
                    1 (7) */


        TreeNode one1 = new TreeNode(1, null, null);
        TreeNode one2 = new TreeNode(1, null, null);
        TreeNode one3 = new TreeNode(1, null, null);
        TreeNode one4 = new TreeNode(1, null, null);
        TreeNode one5 = new TreeNode(1, null, null);
        TreeNode one6 = new TreeNode(1, null, null);
        TreeNode one7 = new TreeNode(1, null, null);
        one1.left = one2;
        one1.right = one3;
        one2.left = one4;
        one2.right = one5;
        one3.right = one6;
        one6.left = one7;
        System.out.println(isUnivalTreeV3(one1));
        System.out.println(counter);

        counter = 0;
        System.out.println(isUnivalTreeV1(one1));
        System.out.println(counter);

        counter = 0;
        System.out.println(isUnivalTreeV2(one1));
        System.out.println(counter);
    }
}
