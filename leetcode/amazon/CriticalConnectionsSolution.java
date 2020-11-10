import java.util.*;

/**
 * Critical Connections in a Network
 * 
 * There are n servers numbered from 0 to n-1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [a, b]
 * represents a connection between servers a and b. Any server can reach any
 * other server directly or indirectly through the network.
 * 
 * A critical connection is a connection that, if removed, will make some server
 * unable to reach some other server.
 * 
 * Return all critical connections in the network in any order.
 */
public class CriticalConnectionsSolution {

  public static class Ranks {
    int lastRank = 0;
    Map<Integer, Integer> ranks; // node -> rank

    Ranks() {
      ranks = new HashMap<>();
    }

    public String toString() {
      return ranks.toString();
    }

    public int incrementRank() {
      lastRank++;
      return lastRank;
    }

    public boolean containsNode(int node) {
      return ranks.containsKey(node);
    }

    public int getRank(int node) {
      return ranks.get(node);
    }

    public void putRank(int node, int rank) {
      ranks.put(node, rank);
      return;
    }
  }

  public static void dfs(Map<Integer, Set<Integer>> graph, Ranks ranks, Map<Integer, Integer> low, Integer node,
      Integer parent, List<List<Integer>> result) {
    int prevRank = ranks.lastRank;
    int rank = ranks.incrementRank(); // side-effect
    ranks.putRank(node, rank);
    low.put(node, rank);
    Set<Integer> children = graph.getOrDefault(node, new HashSet<>());
    for (int child : children) {
      if (parent != null && child == parent) {
        continue;
      }
      if (!ranks.containsNode(child)) {
        dfs(graph, ranks, low, child, node, result);
        low.put(node, Math.min(low.get(node), low.get(child))); // key-idea, how to find bridges
        if (ranks.getRank(node) < low.get(child)) {
          List<Integer> edge = new ArrayList<>(Arrays.asList(node, child));
          result.add(edge);
        }
      } else {
        low.put(node, Math.min(low.get(node), low.get(child))); // key-idea for solving bridges
      }
    }
    return;
  }

  public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    // construct a graph we can work with
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (List<Integer> connection : connections) {
      Integer l = connection.get(0);
      Integer r = connection.get(1);
      Set<Integer> edgesL = graph.getOrDefault(l, new HashSet<>());
      Set<Integer> edgesR = graph.getOrDefault(r, new HashSet<>());
      edgesL.add(r);
      edgesR.add(l);
      graph.put(l, edgesL);
      graph.put(r, edgesR);
    }
    // ranks to keep track of "time" visited
    Ranks ranks = new Ranks();
    // keep track of "low" to find bridges
    Map<Integer, Integer> low = new HashMap<>();
    // dfs
    List<List<Integer>> result = new ArrayList<>();
    dfs(graph, ranks, low, connections.get(0).get(0), null, result);

    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> connections = new ArrayList<>();
    connections.add(new ArrayList<Integer>(Arrays.asList(0, 1)));
    connections.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
    connections.add(new ArrayList<Integer>(Arrays.asList(2, 0)));
    connections.add(new ArrayList<Integer>(Arrays.asList(1, 3)));

    System.out.println(criticalConnections(4, connections));
  }
}
