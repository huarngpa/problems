import java.util.*;

public class ReconstructItinerarySolution {

  public List<String> findItinerary(List<List<String>> tickets) {
    List<String> route = new ArrayList<>();
    Map<String, Queue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets) {
      graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
    }
    System.out.println(graph);
    visit(graph, "JFK", route);
    Collections.reverse(route);
    return route;
  }

  public void visit(Map<String, Queue<String>> graph, String node, List<String> route) {
    while (graph.containsKey(node) && !graph.get(node).isEmpty()) {
      String next = graph.get(node).poll();
      System.out.println(node + " " + next);
      visit(graph, next, route);
    }
    route.add(node);
  }

  public static void main(String[] args) {

  }
}
