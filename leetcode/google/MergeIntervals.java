import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/submissions/
 */
public class MergeIntervals {

  /*
   * The key idea to solve is to sort and then defer adding to result.
   */
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (l, r) -> Integer.compare(l[0], r[0]));
    List<int[]> result = new ArrayList<>();
    int[] current = intervals[0];
    for (int[] next : intervals) {
      if (current[0] <= next[0] && next[0] <= current[1]) {
        current[1] = Math.max(next[1], current[1]);
      } else {
        result.add(current);
        current = next;
      }
    }
    result.add(current);
    return result.toArray(new int[result.size()][]);
  }
}
