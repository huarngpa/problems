import java.util.*;

public class IsValidTreeSolution {

  public boolean validTree(int n, int[][] edges) {
    if (edges.length == 0) {
      if (n > 1) {
        return false;
      }
      return true;
    }
    // Build a graph O(E)
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Map<Integer, Boolean> seen = new HashMap<>();
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      Set<Integer> edgesU = graph.getOrDefault(u, new HashSet<>());
      Set<Integer> edgesV = graph.getOrDefault(v, new HashSet<>());
      edgesU.add(v);
      edgesV.add(u);
      graph.put(u, edgesU);
      graph.put(v, edgesV);
      seen.putIfAbsent(u, false);
      seen.putIfAbsent(v, false);
    }
    // DFS O(V + E)
    boolean cycle = hasCycle(graph, edges[0][0], seen, null);
    if (cycle) {
      return false;
    }
    // Seen
    int seenCount = 0;
    for (Boolean b : seen.values()) {
      if (!b) {
        return false;
      }
      seenCount++;
    }
    if (seenCount < n) {
      return false;
    }
    return true;
  }

  public boolean hasCycle(Map<Integer, Set<Integer>> graph, int node, Map<Integer, Boolean> seen, Integer parent) {
    seen.put(node, true);
    Set<Integer> children = graph.getOrDefault(node, new HashSet<>());
    for (int child : children) {
      if (parent != null && child == parent) {
        continue;
      }
      if (!seen.get(child)) {
        boolean cycle = hasCycle(graph, child, seen, node);
        if (cycle) {
          return true;
        }
      } else {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {

  }
}
