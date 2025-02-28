import java.util.*;

public class AdjacencyList {
    static class Graph {
        int vertices;
        List<Integer>[] adjList;

        // Constructor: Initialize adjacency list
        @SuppressWarnings("unchecked") // Suppress the generic array warning
        public Graph(int n) {
            this.vertices = n;
            adjList = new ArrayList[n]; // Create array of ArrayLists (Unchecked Warning)
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<>(); // Correctly initialize each list
            }
        }

        // Add a directed edge from u to v
        public void addEdge(int u, int v) {
            adjList[u - 1].add(v); // Convert to 0-based index
        }

        // Print the adjacency list
        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                System.out.print((i + 1) + " → ");
                for (int neighbor : adjList[i]) {
                    System.out.print(neighbor + " → ");
                }
                System.out.println("X");
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5); // 5 vertices

        // Add edges
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);

        // Print adjacency list representation
        System.out.println("Adjacency List Representation:");
        graph.printGraph();
    }
}