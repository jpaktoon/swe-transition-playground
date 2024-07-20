package leetcode;

import leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class binaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightList = new ArrayList<>();
        if (root==null) return rightList;

        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()){
            int levelSize =q.size();
            for(int i = 1 ; i <= levelSize ; i++) {
                TreeNode curNode = q.remove();
                if (i == levelSize) rightList.add(curNode.val);
                if (curNode.left!=null) q.add(curNode.left);
                if (curNode.right!=null) q.add(curNode.right);
            }
        }
        return rightList;
    }
}
