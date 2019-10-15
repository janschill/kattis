package mst;

import java.util.Arrays;
import java.util.Comparator;
import java.io.IOException;

public class MST {
  public static void main(String[] args) throws NumberFormatException, IOException {
    Kattio kattio = new Kattio(System.in);

    while (true) {
      int numberOfVertices = kattio.getInt();
      int numberOfEdges = kattio.getInt();

      if (numberOfVertices == 0 && numberOfEdges == 0) {
        break;
      } else if (numberOfVertices == 0 || numberOfEdges == 0) {
        System.out.println("Impossible");
      } else {
        Graph graph = new Graph(numberOfVertices, numberOfEdges);
        for (int i = 0; i < numberOfEdges; i++) {
          graph.edges[i].start = kattio.getInt();
          graph.edges[i].destination = kattio.getInt();
          graph.edges[i].weight = kattio.getInt();
        }

        graph.findMinimumSpanningTree();
      }
    }
    kattio.close();
  }
}

class Graph {
  int numberOfVertices;
  int numberOfEdges;
  Edge edges[];

  class Subset {
    int parent;
    int rank;
  }

  public Graph(int numberOfVertices, int numberOfEdges) {
    this.numberOfVertices = numberOfVertices;
    this.numberOfEdges = numberOfEdges;
    edges = new Edge[numberOfEdges];
    for (int i = 0; i < numberOfEdges; i++) {
      edges[i] = new Edge();
    }
  }

  int find(Subset[] subsets, int i) {
    if (subsets[i].parent != i) {
      subsets[i].parent = find(subsets, subsets[i].parent);
    }
    return subsets[i].parent;
  }

  void union(Subset[] subsets, int u, int v) {
    int uRoot = find(subsets, u);
    int vRoot = find(subsets, v);

    if (subsets[uRoot].rank < subsets[vRoot].rank) {
      subsets[uRoot].parent = vRoot;
    } else if (subsets[uRoot].rank > subsets[vRoot].rank) {
      subsets[vRoot].parent = uRoot;
    } else {
      subsets[vRoot].parent = uRoot;
      subsets[uRoot].rank++;
    }
  }

  void findMinimumSpanningTree() {
    int maxWeight = 0;
    Edge[] result = new Edge[numberOfVertices];
    int e = 0;
    int i = 0;
    for (i = 0; i < numberOfVertices; ++i) {
      result[i] = new Edge();
    }
    Arrays.sort(edges);

    Subset[] subsets = new Subset[numberOfVertices];
    for (i = 0; i < numberOfVertices; ++i) {
      subsets[i] = new Subset();
    }

    for (int v = 0; v < numberOfVertices; ++v) {
      subsets[v].parent = v;
      subsets[v].rank = 0;
    }

    i = 0;

    while (e < numberOfVertices - 1) {
      Edge nextEdge = new Edge();
      nextEdge = edges[i++];
      int u = find(subsets, nextEdge.start);
      int v = find(subsets, nextEdge.destination);

      if (u != v) {
        maxWeight += nextEdge.weight;
        result[e++] = nextEdge;
        union(subsets, u, v);
      }
    }

    System.out.println(maxWeight);
    Edge[] resultCopy = Arrays.copyOfRange(result, 0, numberOfVertices - 1);
    Arrays.sort(resultCopy, new Comparator<Edge>() {
      public int compare(Edge e1, Edge e2) {
        if (e1.start > e2.start) {
          return +1;
        } else if (e1.start < e2.start) {
          return -1;
        }
        return 0;
      }
    });
    for (int r = 0; r < resultCopy.length; r++) {
      System.out.println(resultCopy[r].start + " " + resultCopy[r].destination);
    }
  }

  class Edge implements Comparable<Edge> {
    int start;
    int destination;
    int weight;

    public int compareTo(Edge compareEdge) {
      return this.weight - compareEdge.weight;
    }
  }
}