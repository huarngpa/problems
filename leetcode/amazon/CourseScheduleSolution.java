import java.util.*;

/**
 * Course Schedule II
 * 
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai,
 * bi] this means you must take the course bi before the course ai.
 * 
 * Given the total number of courses numCourses and a list of the prerequisite
 * pairs, return the ordering of courses you should take to finish all courses.
 * 
 * If there are many valid answers, return any of them. If it is impossible to
 * finish all courses, return an empty array.
 */
public class CourseScheduleSolution {
  public class Rank {
    int value = 0;

    Rank() {
    }
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    // Compose a graph
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int[] prereq : prerequisites) {
      int a = prereq[0];
      int b = prereq[1];
      Set<Integer> children = graph.computeIfAbsent(b, k -> new HashSet<>());
      children.add(a);
    }
    // DFS
    Map<Integer, Integer> ranks = new HashMap<>();
    Rank lastRank = new Rank();
    for (int node = 0; node < numCourses; node++) {
      if (!ranks.containsKey(node)) {
        try {
          dfs(graph, ranks, node, lastRank);
        } catch (Exception e) {
          return new int[] {};
        }
      }
    }
    // Sort
    List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(ranks.entrySet());
    Collections.sort(entryList, (l, r) -> r.getValue() - l.getValue());
    return entryList.stream().map(e -> e.getKey()).mapToInt(i -> i).toArray();
  }

  public void dfs(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> ranks, int node, Rank lastRank) {
    lastRank.value++;
    ranks.put(node, lastRank.value);
    for (int child : graph.getOrDefault(node, new HashSet<>())) {
      if (!ranks.containsKey(child)) {
        dfs(graph, ranks, child, lastRank);
      } else {
        // Cycle detection
        if (isCycle(graph, child, node)) {
          throw new RuntimeException();
        }
      }
    }
    // Must record here (topological sort)
    lastRank.value++;
    ranks.put(node, lastRank.value);
  }

  // Can you reach the target again?
  public boolean isCycle(Map<Integer, Set<Integer>> graph, int node, int target) {
    for (int child : graph.getOrDefault(node, new HashSet<>())) {
      if (child == target) {
        return true;
      }
      if (isCycle(graph, child, target)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {

  }
}
