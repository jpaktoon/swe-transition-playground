package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class LeafSimilarTrees {


    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = new ArrayList<>();
        List<Integer> leaf2  = new ArrayList<>();
        System.out.println("leaf1: " + System.identityHashCode(leaf1));
        System.out.println("leaf2: " + System.identityHashCode(leaf2));
        postOrder(root1, leaf1); // pass by value, which is ref. to List object
        postOrder(root2, leaf2); // pass by value, which is ref. to List object
        return leaf1.equals(leaf2);
    }

    private static void postOrder(TreeNode root, List<Integer> leafs) {
        if (root == null) return;

        postOrder(root.left, leafs);
        postOrder(root.right, leafs);
        if (root.left == null && root.right == null) { // leaf node
            System.out.println("Inner recursion leaf: " + System.identityHashCode(leafs));
            leafs.add(root.val);
        }
    }

    public static TreeNode generateTree() {
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

        return four1;
    }
    public static void main(String[] args) {

//        TreeNode zero = null;
//        printAllRights(increasingBSTV2(zero));
//
//        TreeNode one = new TreeNode(1, null, null);
//        printAllRights(increasingBSTV2(one));
//
//        TreeNode two = new TreeNode(2, null, null);
//        two.left = one;
//        printAllRights(increasingBSTV2(two));
        TreeNode tree1 = generateTree();
        TreeNode tree2 = generateTree();

        System.out.println(leafSimilar(tree1, tree2));


        System.out.println(leafSimilar(new TreeNode(2, null, null), new TreeNode(1, null, null)));
    }
}
