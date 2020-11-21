import java.util.*;

/**
 * Reconstruct Itinerary
 * 
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note:
 * 
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example, the
 * itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. All
 * airports are represented by three capital letters (IATA code). You may assume
 * all tickets form at least one valid itinerary. One must use all the tickets
 * once and only once.
 * 
 */
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
