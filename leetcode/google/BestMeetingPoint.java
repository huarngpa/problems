import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BestMeetingPoint {
  private static class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * The key idea here is that this is not really an "algorithm" problem.
   * What we actually need to understand is how to calculate the midpoint
   * correctly.
   * Turns out that the best midpoint is the medium. A great proof here:
   * https://math.stackexchange.com/questions/113270/the-median-minimizes-the-sum-of-absolute-deviations-the-ell-1-norm
   */
  public int minTotalDistance(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    // Find all the points
    List<Pair> points = new LinkedList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] > 0) {
          points.add(new Pair(i, j));
        }
      }
    }
    // Calculate a midpoint based on it being the medium
    List<Integer> xs = points.stream().map(val -> val.x).collect(Collectors.toList());
    List<Integer> ys = points.stream().map(val -> val.y).collect(Collectors.toList());
    Collections.sort(xs);
    Collections.sort(ys);
    int adjustedSize = points.size() % 2 == 0 ? points.size() - 1 : points.size();
    int medianPosition = adjustedSize / 2;
    int midRow = xs.get(medianPosition);
    int midCol = ys.get(medianPosition);
    // Calculate distances
    int distance = 0;
    for (Pair point : points) {
      distance += Math.abs(point.x - midRow) + Math.abs(point.y - midCol);
    }
    return distance;
  }
}
