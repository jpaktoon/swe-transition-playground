package playground;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeCheatSheet {

    public static class BinarySearchTree {
        public static class BinaryNode {
            public int value;
            public int height;
            public BinaryNode left;
            public BinaryNode right;
        }

        public BinaryNode root;

        public BinarySearchTree() {
            root = null;
        }

        public BinaryNode insert(BinaryNode currentNode, int value) {
            // base condition for the recursion - return the node to be inserted
            if (currentNode == null) {
                BinaryNode newNode = new BinaryNode();
                newNode.value = value;
                return newNode;
            } else if (value <= currentNode.value) {
                // insert to the left of currentNode
                currentNode.left = insert(currentNode.left, value);
                return currentNode;
            } else {
                // insert to the right of currentNode
                currentNode.right = insert(currentNode.right, value);
                return currentNode;
            }
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
        // Search
        public static BinaryNode search(BinaryNode node, int value) {
            // base case when not found
            if (node == null)
                return null;
            // base case when found
            else if (value == node.value)
                return node;
            else if (value < node.value)
                return search(node.left, value);
            else
                return search(node.right, value);
        }

        public static BinaryNode minimumNode(BinaryNode node){
            if (node.left == null) return node;
            else return minimumNode(node.left);
        }

        public static BinaryNode delete(BinaryNode node, int value) {
            if (node == null) {
                return null;
            }
            if (value < node.value) {
                node.left = delete(node.left, value);
            } else if (value > node.value)
                node.right = delete(node.right, value);
            else {
                if (node.right != null && node.left != null) { // has 2 children
                    BinaryNode minForRight = minimumNode(node.right);
                    node.value = minForRight.value;
                    node.right = delete(minForRight, minForRight.value);
                } else if (node.left != null) { // has left child
                    node = node.left;
                } else if (node.right != null) { // has right child
                    node = node.right;
                } else { // leaf node
                    node = null;
                }
            }

            return node;
        }
    }

    // Driver Code
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.root = tree.insert(tree.root, 50);
        tree.insert(tree.root, 30);
        tree.insert(tree.root, 20);
        tree.insert(tree.root, 40);
        tree.insert(tree.root, 70);
        tree.insert(tree.root, 60);
        //tree.insert(tree.root, 80);

        System.out.println("Original BST: ");
        System.out.print("inOrder: ");
        BinarySearchTree.inOrder(tree.root);
        System.out.println();

        System.out.print("preOrder: ");
        BinarySearchTree.preOrder(tree.root);
        System.out.println();

        System.out.print("postOrder: ");
        BinarySearchTree.postOrder(tree.root);
        System.out.println();

        System.out.print("levelOrder: ");
        BinarySearchTree.levelOrder(tree.root);
        System.out.println();

        System.out.println("\nDelete a Leaf Node: 20");
        tree.root = BinarySearchTree.delete(tree.root, 20);
        System.out.print("Modified BST tree after deleting Leaf Node:\n");
        BinarySearchTree.inOrder(tree.root);
        System.out.println();

        System.out.println("\nDelete Node with single child: 70");
        tree.root = BinarySearchTree.delete(tree.root, 70);
        System.out.print("Modified BST tree after deleting single child Node:\n");
        BinarySearchTree.inOrder(tree.root);
        System.out.println();

        System.out.println("\nDelete Node with both child: 50");
        tree.root = BinarySearchTree.delete(tree.root, 50);
        System.out.print("Modified BST tree after deleting both child Node:\n");
        BinarySearchTree.inOrder(tree.root);
    }
}
