package playground;

import java.util.*;

public class GraphCycles {

    public boolean hasCycleBFS(int n, List<List<Integer>> adjList) {
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>(); // Pair of (node, parent)

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(new int[]{i, -1});

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int node = current[0];
                    int parent = current[1];

                    // Mark the node as visited when dequeued
                    visited[node] = true;

                    for (int neighbor : adjList.get(node)) {
                        if (!visited[neighbor]) {
                            queue.offer(new int[]{neighbor, node});
                        } else if (neighbor != parent) {
                            return true; // Cycle detected
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GraphCycles gc = new GraphCycles();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adjList.add(new ArrayList<>());
        }
        // O -> A, 1 -> B, 2 -> C, 3 -> D
        adjList.get(0).add(1);
        adjList.get(0).add(2);
        adjList.get(1).add(3);
        adjList.get(2).add(3);

        System.out.println(gc.hasCycleBFS(4, adjList));
    }
}
