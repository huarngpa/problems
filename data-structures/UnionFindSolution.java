import java.util.HashMap;
import java.util.Map;

/**
 * https://algo.monster/problems/dsu_intro
 */
public class UnionFindSolution {

  private static class UnionFind<T> {

    private Map<T, T> ids = new HashMap<>();

    public T find(T element) {
      T id = ids.getOrDefault(element, element);
      if (!element.equals(id)) {
        id = find(id);
        // Optimization, tree compression
        ids.put(element, id);
      }
      return id;
    }

    public void union(T x, T y) {
      ids.put(find(x), find(y));
    }

    // Even better union (by rank) optimization. O(alpha(n)), which ends up being
    // O(4) or O(1).
    private Map<T, Integer> ranks = new HashMap<>();

    public void unionByRank(T x, T y) {
      if (!ranks.containsKey(find(x))) {
        ranks.put(find(x), 0);
      }
      if (!ranks.containsKey(find(y))) {
        ranks.put(find(y), 0);
      }
      if (ranks.get(find(x)) < ranks.get(find(y))) {
        ids.put(find(x), find(y));
      } else {
        ids.put(find(y), find(x));
        // If rank is the same we update x's rank and increment it by 1.
        // We make y's parent equal to x's parent, so x has increased depth.
        if (ranks.get(find(x)) == ranks.get(find(y))) {
          ranks.put(find(x), ranks.get(find(x)) + 1);
        }
      }
    }
  }

  public static void main(String[] args) {
    UnionFind<Integer> unionFind = new UnionFind<>();
    unionFind.union(1, 2);
    System.out.println(String.format("unionFind.find(2)=%s", unionFind.find(2)));
    System.out.println(String.format("unionFind.find(1)=%s", unionFind.find(1)));
    System.out.println(String.format("unionFind.find(3)=%s", unionFind.find(3)));
  }
}
