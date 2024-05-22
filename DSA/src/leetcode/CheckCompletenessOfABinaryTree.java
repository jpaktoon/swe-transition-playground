package leetcode;

import leetcode.utils.TreeNode;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {

    // Time O (n), space O (n)
    // Result : 1 ms, 42 MB
    public boolean isCompleteTree(TreeNode root) {
        boolean end = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode currNode = queue.poll();
            if(currNode == null){
                end = true;
            }else{
                // this check after end, if there still be any non null node
                // which shouldn't be anymore for a complete tree.
                if(end) return false;
                // add all left and right, no matter it is null or not
                queue.offer(currNode.left);
                queue.offer(currNode.right);
            }
        }
        return true;
    }

    // Time O (n), space O (n) / actually 2n but remove constant
    // This solution need more time and space than the V1
    // Result : 3 ms, 42.6-42.7 MB
    public static boolean isCompleteTreeV2(TreeNode root) {
        // start from index 1 then do DFS
        // Left node index = parent index * 2
        // Right node index = parent index * 2 + 1
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> counter = new ArrayList<>();
        queue.add(root);
        counter.add(1);
        int index = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            // quick check
            if (node.right != null && node.left == null) {
                return false;
            }
            // BFS
            if (node.left != null) {
                queue.add(node.left);
                counter.add(counter.get(index) * 2);
            }
            if (node.right != null) {
                queue.add(node.right);
                counter.add(counter.get(index) * 2 + 1);
            }
            index++;
            // quick completeness check
            if (index < counter.size() && counter.get(index) - 1 != counter.get(index - 1))
            {
                return false;
            }
        }
        System.out.println(counter);
        return true;
    }
}
