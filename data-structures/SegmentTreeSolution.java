import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SegmentTreeSolution {

  /**
   * Range query and range updates data structure. What ultimately gets created is
   * a heap-like data-structure that allows us to query a particular range.
   */
  private static class SegmentTree {

    int size;
    List<Integer> tree;

    public SegmentTree(List<Integer> list) {
      size = list.size();
      tree = new ArrayList<>(4 * size);
      IntStream.range(0, 4 * size).forEach((ignored) -> tree.add(0));
      for (int i = 0; i < size; i++) {
        update(i, list.get(i));
      }
    }

    private void update(int current, int left, int right, int idx, int val) {
      if (left == right && left == idx) {
        tree.set(current, val);
      } else {
        int mid = (left + right) / 2;
        if (idx <= mid) {
          update(current * 2, left, mid, idx, val);
        } else {
          update(current * 2 + 1, mid + 1, right, idx, val);
        }
        tree.set(current, tree.get(current * 2) + tree.get(current * 2 + 1));
      }
    }

    public void update(int idx, int val) {
      update(1, 0, size - 1, idx, val);
    }

    private int query(int current, int left, int right, int queryLeft, int queryRight) {
      if (left > queryRight || right < queryLeft) {
        return 0;
      }
      if (queryLeft <= left && right <= queryRight) {
        return tree.get(current);
      }
      int mid = (left + right) / 2;
      return query(current * 2, left, mid, queryLeft, queryRight)
          + query(current * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    public int query(int left, int right) {
      return query(1, 0, size - 1, left, right);
    }

    @Override
    public String toString() {
      return String.format("SegmentTree(tree=%s)", tree);
    }
  }

  public static void main(String[] args) {
    List<Integer> original = Arrays.asList(1, 2, 3, 4, 5);
    SegmentTree segmentTree = new SegmentTree(original);
    System.out.println(String.format("original=%s", original));
    System.out.println(segmentTree);
    System.out.println(String.format("segmentTree.query(0, 4)=%s", segmentTree.query(0, 4)));
    System.out.println(String.format("segmentTree.query(1, 4)=%s", segmentTree.query(1, 4)));
    System.out.println(String.format("segmentTree.query(2, 3)=%s", segmentTree.query(2, 3)));
    System.out.println(String.format("segmentTree.query(2, 4)=%s", segmentTree.query(2, 4)));
  }
}
