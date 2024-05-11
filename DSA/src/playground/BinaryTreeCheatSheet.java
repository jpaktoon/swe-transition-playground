package playground;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// Tree with many children.
public class BinaryTreeCheatSheet {
    public class TreeNode {
        String data;
        ArrayList<TreeNode> children;

        public TreeNode(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(TreeNode node) {
            this.children.add(node);
        }

        public String print(int level) {
            StringBuilder ret;
            ret = new StringBuilder(" ".repeat(level) + data + "\n");
            for (TreeNode node: this.children) {
                ret.append(node.print(level + 1));
            }
            return ret.toString();
        }
    }

    public static class BinaryTreeLL {
        public static BinaryNode root;
        public class BinaryNode {
            public String value;
            public BinaryNode left;
            public BinaryNode right;

            public BinaryNode(String value) {
                this.value = value;
            }
        }

        public BinaryTreeLL(){
            this.root = null;
        }

        // preorder traversal
        public static void preOrder(BinaryNode node) {
            if (node == null) return;
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }

        // inOrder traversal
        public static void inOrder(BinaryNode node) {
            if (node == null) return;
            inOrder(node.left);
            System.out.print(node.value + " ");
            inOrder(node.right);
        }

        // postOrder traversal
        public static void postOrder(BinaryNode node) {
            if (node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.value + " ");
        }

        public static void levelOrder(BinaryNode node) {
            Queue<BinaryNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) { // time O (n) / space O (n) for creating Queue
                BinaryNode visited = queue.remove();
                System.out.print(visited.value + " ");
                if (visited.left != null) queue.add(visited.left);
                if (visited.right != null) queue.add(visited.right);
            }
        }

        // insert to the leaking place
        public void insert(String value) {
            BinaryNode node = new BinaryNode(value);
            if (root == null) {
                this.root = node;
                return;
            }
            Queue<BinaryNode> bQueue = new LinkedList<>();
            bQueue.add(root);
            while (!bQueue.isEmpty()) {
                BinaryNode visited = bQueue.remove();
                if (visited.left == null) {
                    visited.left = node;
                    return;
                } else if (visited.right == null) {
                    visited.right = node;
                    return;
                } else {
                    bQueue.add(visited.left);
                    bQueue.add(visited.right);
                }
            }
        }

        // Get Deepest node
        public static BinaryNode getDeepestNode() {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.add(root);
            BinaryNode presentNode = null;
            while (!queue.isEmpty()) {
                presentNode = queue.remove();
                if (presentNode.left != null) {
                    queue.add(presentNode.left);
                }
                if (presentNode.right != null) {
                    queue.add(presentNode.right);
                }
            }
            return presentNode;
        }

        // Delete Deepest node
        public static void deleteDeepestNode() {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.add(root);
            BinaryNode previousNode, presentNode = null;
            while (!queue.isEmpty()) {
                previousNode = presentNode;
                presentNode = queue.remove();
                if (presentNode.left == null) {
                    previousNode.right = null;
                    return;
                } else if (presentNode.right == null) {
                    presentNode.left = null;
                    return;
                }
                queue.add(presentNode.left);
                queue.add(presentNode.right);

            }
        }

        // Delete Given node
        static void deleteNode(String value) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.add(root);
            while (!queue.isEmpty()) {
                BinaryNode presentNode = queue.remove();
                if (presentNode.value == value) {
                    presentNode.value = getDeepestNode().value;
                    deleteDeepestNode();
                    System.out.println("The node is deleted!");
                    return;
                } else {
                    if (presentNode.left != null) queue.add(presentNode.left);
                    if (presentNode.right != null) queue.add(presentNode.right);
                }
            }
            System.out.println("The node does not exist in this BT");
        }

    }

    public static class BinaryTreeArray {
        String[] arr;
        int lastUsedIndex;

        public BinaryTreeArray(int size) {
            arr = new String[size + 1]; // skip index 0
            lastUsedIndex = 0;
        }

        public void insert(String value){
            if (arr.length - 1 != lastUsedIndex) {
                arr[lastUsedIndex + 1] = value;
                lastUsedIndex++;
            }
        }

        public void preOrder(int index) { // root is index 1
            if (index > lastUsedIndex) return;
            System.out.print(arr[index] + " ");
            preOrder(2 * index);
            preOrder(2 * index + 1);
        }

        public void inOrder(int index) { // root is index 1
            if (index > lastUsedIndex) return;
            inOrder(2 * index);
            System.out.print(arr[index] + " ");
            inOrder(2 * index + 1);
        }

        public void postOrder(int index) { // root is index 1
            if (index > lastUsedIndex) return;
            postOrder(2 * index);
            postOrder(2 * index + 1);
            System.out.print(arr[index] + " ");
        }

        public void levelOrder() { // root is index 1
            // int index = 1;
//            Queue<Integer> queue = new LinkedList<>();
//            queue.add(Integer.valueOf(arr[index]));
//            while (!queue.isEmpty()) {
//                int value = queue.remove();
//                System.out.println(value);
//                if (index * 2 <= lastUsedIndex) {
//                    queue.add(Integer.valueOf(arr[index * 2]));
//                }
//                if (index * 2 + 1 <= lastUsedIndex) {
//                    queue.add(Integer.valueOf(arr[index * 2 + 1]));
//                }
//                index++;
//            }
            for (int i = 1; i <= lastUsedIndex; i++) {
                System.out.print(arr[i] +  " ");
            }
        }

        public int search(String value) {
            for (int i = 1; i<=lastUsedIndex; i++) {
                if (arr[i] == value) {
                    System.out.println(value+" exists at the location: " + i);
                    return i;
                }
            }
            System.out.println("The value does not exist in BT");
            return -1;
        }

        // Delete Method
        public void delete(String value) {
            int location = search(value);
            if (location == -1) {
                return;
            } else {
                arr[location] = arr[lastUsedIndex];
                lastUsedIndex--;
                System.out.println("The node successfully deleted");
            }
        }

        public int findLevel(int index) { // Only a complete tree
            // root index is 1
            // level 1 has 1 node, level 2 has 2 node, level 3 has 4 node and level 4 has 8 node
            // if we have 15 nodes - how many level we have, ans is 4 = ceil(log2 15) + 1
            // ceil(log2 node) = level - 1
            // ceil(log2 node) + 1 = level
            return (int) Math.ceil(Math.log(index) / Math.log(2)) + 1; // Cast and add 1 for root level
        }
    }

    public static void main(String[] args) {

        System.out.println("********** BinaryTreeLinkedList **********");
        BinaryTreeLL treeLL = new BinaryTreeLL();

        /* Let us create following BT
              50
           /     \
          30      20
         /  \    /  \
       40   70  60   80 */
        treeLL.insert("50");
        treeLL.insert("30");
        treeLL.insert("20");
        treeLL.insert("40");
        treeLL.insert("70");
        treeLL.insert("60");
        treeLL.insert("80");

        System.out.println("Original LL BT: ");
        System.out.print("inOrder: ");
        BinaryTreeLL.inOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.print("preOrder: ");
        BinaryTreeLL.preOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.print("postOrder: ");
        BinaryTreeLL.postOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.print("levelOrder: ");
        BinaryTreeLL.levelOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.println("\nDelete a Leaf Node: 60");
        BinaryTreeLL.deleteNode("60");
        System.out.print("Modified BT tree after deleting Leaf Node:\n");
        BinaryTreeLL.inOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.println("\nDelete Node with single child: 20");
        BinaryTreeLL.deleteNode("20");
        System.out.print("Modified BT tree after deleting single child Node:\n");
        BinaryTreeLL.inOrder(BinaryTreeLL.root);
        System.out.println();

        System.out.println("\nDelete Node with both child: 30");
        BinaryTreeLL.deleteNode("30");
        System.out.print("Modified BT tree after deleting both child Node:\n");
        BinaryTreeLL.inOrder(BinaryTreeLL.root);

        System.out.println("********** BinaryTreeArray **********");

        BinaryTreeArray treeArray = new BinaryTreeArray(7);

        /* Let us create following BT
              50
           /     \
          30      20
         /  \    /  \
       40   70  60   80 */
        treeArray.insert("50");
        treeArray.insert("30");
        treeArray.insert("20");
        treeArray.insert("40");
        treeArray.insert("70");
        treeArray.insert("60");
        treeArray.insert("80");

        System.out.println("Original Array BT: ");
        System.out.print("inOrder: ");
        treeArray.inOrder(1);
        System.out.println();

        System.out.print("preOrder: ");
        treeArray.preOrder(1);
        System.out.println();

        System.out.print("postOrder: ");
        treeArray.postOrder(1);
        System.out.println();

        System.out.print("levelOrder: ");
        treeArray.levelOrder();
        System.out.println();

        System.out.println("\nDelete a Leaf Node: 60");
        treeArray.delete("60");
        System.out.print("Modified BT tree after deleting Leaf Node:\n");
        treeArray.inOrder(1);
        System.out.println();

        System.out.println("\nDelete Node with single child: 20");
        treeArray.delete("20");
        System.out.print("Modified BT tree after deleting single child Node:\n");
        treeArray.inOrder(1);
        System.out.println();

        System.out.println("\nDelete Node with both child: 30");
        treeArray.delete("30");
        System.out.print("Modified BT tree after deleting both child Node:\n");
        treeArray.inOrder(1);
    }
}
