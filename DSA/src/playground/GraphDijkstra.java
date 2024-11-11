package playground;

import java.util.*;

class GraphDijkstra {
    private int vertices;
    private List<List<Node>> adjList;

    // Constructor
    public GraphDijkstra(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add edges to the graph
    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Node(v, weight));
        adjList.get(v).add(new Node(u, weight));  // For undirected graph
    }

    // Dijkstra's algorithm to find the shortest path
    public void dijkstra(int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(n -> n.cost));

        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.node;

            // Traverse neighbors of u
            for (Node neighbor : adjList.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.cost;

                // Relaxation step
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // Print shortest distances
        printShortestPaths(dist, source);
    }

    private void printShortestPaths(int[] dist, int source) {
        System.out.println("Shortest paths from node " + source + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("To node " + i + " is " + dist[i]);
        }
    }

    // Node class to store the vertex and cost
    static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        GraphDijkstra graph = new GraphDijkstra(6);

        // Add edges to the graph
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 1);

        // Perform Dijkstra's algorithm
        graph.dijkstra(0);
    }
}

