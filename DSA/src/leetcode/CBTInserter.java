package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class CBTInserter {

    private final TreeNode root;
    private final Queue<TreeNode> queue = new LinkedList<>();

    public CBTInserter(TreeNode root) {
        this.root = root; // O (1)

        Queue<TreeNode> temp = new LinkedList<>();
        temp.add(this.root);
        while (!temp.isEmpty()) { // O (n)
            TreeNode current = temp.remove();
            // Add not full nodes to queue for reusing
            if (current.left == null || current.right == null) this.queue.add(current);
            // BFS
            if (current.left != null) temp.add(current.left);
            if (current.right != null) temp.add(current.right);
        }

        System.out.println(this.queue.stream().map( t -> t.val).toList());
    }

    public int insert(int val) { // O (1)
        TreeNode newNode = new TreeNode(val, null, null);
        TreeNode targetNode = queue.peek();
        assert targetNode != null;
        int value = targetNode.val;
        if (targetNode.left == null) {
            targetNode.left = newNode;
            queue.add(targetNode.left);
        } else if (targetNode.right == null) {
            targetNode.right = newNode;
            queue.remove(); // node is full so remove it
            queue.add(targetNode.right);
        }
        return value;
    }

    public TreeNode get_root() {
        return root;
    }

    public int insertLowPerf(int val) { // O (n)
        Deque<TreeNode> queue = new ArrayDeque<>();

        TreeNode newNode = new TreeNode(val, null, null);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.remove();
            if (currentNode.left == null) {
                currentNode.left = newNode;
                return currentNode.val;
            } else if (currentNode.right == null) {
                currentNode.right = newNode;
                return currentNode.val;
            } else {
                queue.add(currentNode.left);
                queue.add(currentNode.right);
            }
        }
        return root.val;
    }

}
