package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    static class TreeWithLevel {
        TreeNode node;
        int level;

        public TreeWithLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        // node, level
        ArrayDeque<TreeWithLevel> nodes = new ArrayDeque<>();
        nodes.add(new TreeWithLevel(root, 1));
        HashMap<Integer, ArrayList<Integer>> nodeLevels = new HashMap<>();
        while (!nodes.isEmpty()) {
            TreeWithLevel current = nodes.remove();
            if (nodeLevels.containsKey(current.level)) {
                nodeLevels.get(current.level).add(current.node.val);
            } else {
                nodeLevels.put(current.level, new ArrayList<>(List.of(current.node.val)));
            }
            if (current.node.left != null) {
                nodes.add(new TreeWithLevel(current.node.left, current.level + 1));
            }
            if (current.node.right != null) {
                nodes.add(new TreeWithLevel(current.node.right, current.level + 1));
            }
        }

        return new ArrayList<>(nodeLevels.values());
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> valueList = new ArrayList<>();
        if (root==null) return valueList;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()){
            int levelSize =q.size();
            List<Integer> levelList = new ArrayList<>();
            for(int i = 0 ; i < levelSize ; i++) {
                TreeNode curNode = q.remove();
                levelList.add(curNode.val);
                if (curNode.left!=null) q.add(curNode.left);
                if (curNode.right!=null) q.add(curNode.right);
            }
            valueList.add(levelList);
        }
        return valueList;
    }
}
