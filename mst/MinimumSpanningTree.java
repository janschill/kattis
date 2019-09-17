package mst;

public class MinimumSpanningTree {
  public static void main(String[] args) {
    int V = 4;
    int[] disjointSet = new int[V];
    for (int i = 0; i < V; i++) {
      disjointSet[i] = -1;
    }
  }

  class Graph {
    int V;
    int E;
    Edge[] edges;

    public Graph(int v, int e) {
      V = v;
      E = e;
      edges = new Edge[e];
    }
  }

  class Edge {
    int source, destination, weight;
  }
}