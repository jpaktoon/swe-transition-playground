package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

    static class TreeWithLevel {
        TreeNode node;
        int level;

        public TreeWithLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return List.of(0d);
        // node, level
        ArrayDeque<TreeWithLevel> nodes = new ArrayDeque<>();
        nodes.add(new TreeWithLevel(root, 1));
        HashMap<Integer, ArrayList<Integer>> avgLevels = new HashMap<>();
        while (!nodes.isEmpty()) {
            TreeWithLevel current = nodes.remove();
            if (avgLevels.containsKey(current.level)) {
                avgLevels.get(current.level).add(current.node.val);
            } else {
                avgLevels.put(current.level, new ArrayList<>(List.of(current.node.val)));
            }
            if (current.node.left != null) {
                nodes.add(new TreeWithLevel(current.node.left, current.level + 1));
            }
            if (current.node.right != null) {
                nodes.add(new TreeWithLevel(current.node.right, current.level + 1));
            }
        }

        List<Double> averages = new ArrayList<>();
        for (List<Integer> sublist : avgLevels.values()) {
            double sum = 0;
            for (int num : sublist) {
                sum += num;
            }
            double average = sum / sublist.size();
            averages.add(average);
        }
        return averages;
    }

    public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> avgList = new ArrayList<>();
        if (root==null) return avgList;
        if (root!=null &&(root.left==null&&root.right==null)) {
            avgList.add((double)root.val);
            return avgList;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int levelSize =q.size();
            double sum=0;
            for(int i=0;i<levelSize;i++){
                TreeNode curNode =q.poll();
                sum+=curNode.val;

                if (curNode.left!=null) q.add(curNode.left);
                if (curNode.right!=null) q.add(curNode.right);
            }
            double avg=sum/levelSize;
            avgList.add(avg);
        }
        return avgList;
    }
}
