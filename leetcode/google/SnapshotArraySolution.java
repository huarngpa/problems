import java.util.HashMap;
import java.util.Map;

/**
 * Implement a SnapshotArray that supports pre-defined interfaces.
 */
public class SnapshotArraySolution {
  int snapshotId = -1;
  int length;
  Map<Integer, Integer> diff = new HashMap<>();
  Map<Integer, Map<Integer, Integer>> snapshots = new HashMap<>();

  /*
   * Note that what this question is really all about is how to balance memory vs
   * speed.
   * The point is that snapshots memory requirement can get quite large and we
   * need an appropriate data structure for this.
   * Then this question also teaches you how "copy" can be quite expensive and you
   * also learn the "diff" approach, traversing backwards until you reach a value.
   * Note the potential O(n) cost of retrieval. But it's a tradeoff for other
   * performance issues.
   */
  public SnapshotArray(int length) {
        this.length = length;
    }

  public void set(int index, int val) {
    diff.put(index, val);
  }

  public int snap() {
    snapshots.put(++snapshotId, diff);
    diff = new HashMap<>();
    return snapshotId;
  }

  public int get(int index, int snap_id) {
    for (int currentSnapId = snap_id; currentSnapId >= 0; currentSnapId--) {
      Integer val = snapshots.get(currentSnapId).get(index);
      if (val != null) {
        return val;
      }
    }
    return 0;
  }
}