package mockwb;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class shortestleaf {
    public static class Node {
        String value;
        Edge[] children;

        public Node(String value, Edge[] children) {
            this.value = value;
            this.children = children;
        }

        public Node(String value) {
            this.value = value;
            this.children = new Edge[] {};
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    public static class Edge {
        int cost;
        Node next;

        public Edge(int cost, Node next) {
            this.cost = cost;
            this.next = next;
        }
    }

    static int lowestTotalCost = Integer.MAX_VALUE;
    static Set<Node> targetLeafNodes = new HashSet<>();

    // Main method to find the shortest leaves
    public static List<Node> findShortestLeaves(Node root) {
        if (root == null) return new ArrayList<>();
        if (root.children == null || root.children.length == 0) {
            return new ArrayList<>(List.of(root));
        }

        // Resetting state for each invocation to avoid static issues
        lowestTotalCost = Integer.MAX_VALUE;
        targetLeafNodes.clear();

        // Start the recursive search
        findShortestLeavesHelper(root, 0);
        return targetLeafNodes.stream().toList();
    }

    // Recursive helper method to find the shortest path leaf nodes
    public static void findShortestLeavesHelper(Node root, int totalCost) {
        if (root.children == null || root.children.length == 0) {
            if (totalCost < lowestTotalCost) {
                targetLeafNodes.clear(); // Clear the existing list
                targetLeafNodes.add(root); // Add the new leaf node
                lowestTotalCost = totalCost; // Update the minimum cost
            } else if (totalCost == lowestTotalCost) {
                targetLeafNodes.add(root); // Add leaf node to the list of equally short paths
            }
            return;
        }

        // Traverse all edges to explore children
        for (Edge edge : root.children) {
            int newCost = edge.cost + totalCost; // Calculate the new path cost
            findShortestLeavesHelper(edge.next, newCost);
        }
    }

    public static List<Node> findShortestLeavesNonStatic(Node root) {
        // Initialize state as local variables
        int[] lowestTotalCost = {Integer.MAX_VALUE};
        Set<Node> targetLeafNodes = new HashSet<>();

        // Recursive call to helper
        findShortestLeavesHelperNonStatic(root, 0, lowestTotalCost, targetLeafNodes);

        return new ArrayList<>(targetLeafNodes);
    }

    private static void findShortestLeavesHelperNonStatic(Node root, int totalCost, int[] lowestTotalCost, Set<Node> targetLeafNodes) {
        // Base case for leaf nodes
        if (root.children == null || root.children.length == 0) {
            if (totalCost < lowestTotalCost[0]) {
                targetLeafNodes.clear(); // Clear the existing list
                targetLeafNodes.add(root); // Add the new leaf node
                lowestTotalCost[0] = totalCost; // Update the minimum cost
            } else if (totalCost == lowestTotalCost[0]) {
                targetLeafNodes.add(root); // Add leaf node to the list of equally short paths
            }
            return;
        }

        // Traverse all edges to explore children
        for (Edge edge : root.children) {
            int newCost = edge.cost + totalCost; // Calculate the new path cost
            findShortestLeavesHelperNonStatic(edge.next, newCost, lowestTotalCost, targetLeafNodes);
        }
    }

    static HashMap<Node, Integer> nodeWithDist = new HashMap<>();  // Stores the shortest distance from root to each node

    // Clears the distances map and initiates Dijkstra's algorithm to find the shortest leaves
    public static List<Node> findShortestDijstra(Node root) {
        nodeWithDist.clear();  // Clear any previous data to avoid stale results
        return dijstra(root);  // Start Dijkstra from the root
    }

    // Dijkstra's algorithm implementation to find the shortest root-to-leaf paths
    public static List<Node> dijstra(Node root) {
        // Priority queue to always process the node with the smallest known distance
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(
                x -> nodeWithDist.getOrDefault(x, Integer.MAX_VALUE)));

        List<Node> shortestLeaves = new ArrayList<>();  // To store the leaf nodes with the shortest path
        int shortestCost = Integer.MAX_VALUE;  // Track the minimum cost to any leaf node

        nodeWithDist.put(root, 0);  // Root node has a distance of 0 from itself
        pq.add(root);  // Start processing from the root

        // While there are still nodes to process
        while (!pq.isEmpty()) {
            Node current = pq.poll();  // Get the node with the smallest known distance
            int pcost = nodeWithDist.getOrDefault(current, Integer.MAX_VALUE);  // Get the current node's cost

            // Iterate over all the edges (children) of the current node
            for (Edge e : current.children) {
                Node neighbor = e.next;  // Get the child node (neighbor)
                int ccost = nodeWithDist.getOrDefault(neighbor, Integer.MAX_VALUE);  // Get the current cost of the neighbor

                // If a shorter path to this neighbor is found, update it
                if (pcost + e.cost < ccost) {
                    int newCost = pcost + e.cost;  // Update the cost to reach the neighbor
                    nodeWithDist.put(neighbor, newCost);  // Store the new shortest cost for this neighbor
                    pq.add(neighbor);  // Add the neighbor to the priority queue for further processing

                    // Check if the neighbor is a leaf node (no children)
                    boolean isLeaf = (neighbor.children == null || neighbor.children.length == 0);
                    if (isLeaf) {
                        // If the new path cost is the smallest so far, update the result
                        if (newCost < shortestCost) {
                            shortestCost = newCost;  // Update the minimum cost
                            shortestLeaves.clear();  // Clear any previous leaves
                            shortestLeaves.add(neighbor);  // Add the new leaf
                        } else if (newCost == shortestCost) {
                            shortestLeaves.add(neighbor);  // Add the leaf with the same minimal cost
                        }
                    }
                }
            }
        }
        return shortestLeaves;  // Return the list of shortest path leaf nodes
    }

    public static class CostParent{
        Node parent;
        int cost;

        public CostParent(Node parent, int cost) {
            this.parent = parent;
            this.cost = cost;
        }
    }

    static HashMap<Node, List<Edge>> nodes = new HashMap<>();
    static HashMap<Node, CostParent> nodeCostParents = new HashMap<>();

    public static void buildGraphsForDellman(Node root) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            nodes.put(current, new ArrayList<>(List.of(current.children)));
            for (Edge edge : current.children) { // assuming no loop
                queue.add(edge.next);
            }
        }
    }

    public static void bellman(Node root) {
        nodeCostParents.clear();
        nodes.clear();

        nodeCostParents.put(root, new CostParent(null, 0));
        buildGraphsForDellman(root);
        int v = nodes.size();
        for (int i = 0; i < v; i ++) {
            for (Node node : nodes.keySet()) {
                List<Edge> edges = nodes.get(node);
                for (Edge edge : edges) {
                    // node is source, edge.cost = weight and edge.next is destination
                    if (!nodeCostParents.containsKey(node)) {
                        continue;
                    }
                    CostParent sourceWeight = nodeCostParents.getOrDefault(node, new CostParent(null, Integer.MAX_VALUE));
                    CostParent destWeight = nodeCostParents.getOrDefault(edge.next, new CostParent(node, Integer.MAX_VALUE));
                    if (edge.cost + sourceWeight.cost < destWeight.cost) {
                        int newCost = edge.cost + sourceWeight.cost;
                        nodeCostParents.put(edge.next, new CostParent(node, newCost));
                    }
                }
            }
        }

        for (Node node : nodeCostParents.keySet()) {
            int cost = nodeCostParents.get(node).cost;
            Node parent = nodeCostParents.get(node).parent;
            String parentName = parent == null ? "it is root" : parent.value;
            System.out.println("Node: " + node.value + " -> cost: " + cost + ", from parent: " + parentName);
        }
    }

    // Method to build the adjacency list and perform Topological Sort
    public static List<Node> topologicalSort(Node root) {
        Map<String, Integer> inDegree = new HashMap<>();
        List<Node> topoOrder = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Map<String, Node> nodeMap = new HashMap<>();

        // BFS to determine in-degrees and build a map of nodes
        queue.offer(root);
        nodeMap.put(root.value, root);
        inDegree.put(root.value, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Edge edge : current.children) {
                Node neighbor = edge.next;
                inDegree.put(neighbor.value, inDegree.getOrDefault(neighbor.value, 0) + 1);
                if (!nodeMap.containsKey(neighbor.value)) {
                    nodeMap.put(neighbor.value, neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        // Collect nodes with zero in-degree to start the topological sort
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(nodeMap.get(entry.getKey()));
            }
        }

        // Process nodes in topological order
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            topoOrder.add(current);

            for (Edge edge : current.children) {
                Node neighbor = edge.next;
                inDegree.put(neighbor.value, inDegree.get(neighbor.value) - 1);
                if (inDegree.get(neighbor.value) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return topoOrder;
    }

    // Method to find the leaf nodes with the minimum cost from the root
    public static List<Node> findLowestCostLeafNodesInDAG(Node root) {
        List<Node> topoOrder = topologicalSort(root);
        Map<String, Integer> cost = new HashMap<>();
        List<Node> leafNodes = new ArrayList<>();

        // Initialize the cost map
        for (Node node : topoOrder) {
            cost.put(node.value, Integer.MAX_VALUE);
        }
        cost.put(root.value, 0); // Cost to reach the root is 0

        // Process nodes in topological order to update costs
        for (Node node : topoOrder) {
            if (cost.get(node.value) != Integer.MAX_VALUE) { // Process only reachable nodes
                boolean isLeaf = true;
                for (Edge edge : node.children) {
                    isLeaf = false; // Node has children, so it's not a leaf
                    Node neighbor = edge.next;
                    int newCost = cost.get(node.value) + edge.cost;
                    if (newCost < cost.get(neighbor.value)) {
                        cost.put(neighbor.value, newCost);
                    }
                }
                // If it's a leaf node, add it to the list of leaf nodes
                if (isLeaf) {
                    leafNodes.add(node);
                }
            }
        }

        // Find the minimum cost among the leaf nodes
        int minCost = Integer.MAX_VALUE;
        for (Node leaf : leafNodes) {
            minCost = Math.min(minCost, cost.get(leaf.value));
        }

        // Collect leaf nodes with the minimum cost
        List<Node> minCostLeafNodes = new ArrayList<>();
        for (Node leaf : leafNodes) {
            if (cost.get(leaf.value) == minCost) {
                minCostLeafNodes.add(leaf);
            }
        }

        return minCostLeafNodes;
    }

    public static void main(String[] args) {
//                  A
//                /   \
//        b      2      3 c
//           /           \
//         B               C
//         / \
//     d  1   1 e
//       /      \
//      D <- 2 -  E
        // edEdge


        Node dNode = new Node("D");
        Edge edEdge = new Edge(2, dNode);
        Node eNode = new Node("E", new Edge[] {edEdge} );

        Edge dEdge = new Edge(1, dNode);
        Edge eEdge = new Edge(1, eNode);

        Node bNode = new Node("B", new Edge[] {dEdge, eEdge});
        Edge bEdge = new Edge(2, bNode);

        Node cNode = new Node("C");
        Edge cEdge = new Edge(3, cNode);

        Node aNode = new Node("A", new Edge[] {bEdge, cEdge});

        List<Node> shortestLeafA = findShortestLeavesNonStatic(aNode);
        List<Node> shortestLeafB = findShortestLeavesNonStatic(aNode);

        System.out.println(shortestLeafA);
        System.out.println(shortestLeafB);

        System.out.println(findLowestCostLeafNodesInDAG(aNode));

//        List<Node> shortestLeafV2 = findShortestDijstra(aNode);
//
//        System.out.println(shortestLeafV2);
//
//        bellman(aNode);
    }
}

