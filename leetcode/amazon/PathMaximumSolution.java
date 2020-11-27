import java.util.*;

/**
 * Path With Maximum Minimum Value
 * 
 * Given a matrix of integers A with R rows and C columns, find the maximum
 * score of a path starting at [0,0] and ending at [R-1,C-1].
 * 
 * The score of a path is the minimum value in that path. For example, the value
 * of the path 8 → 4 → 5 → 9 is 4.
 * 
 * A path moves some number of times from one visited cell to any neighbouring
 * unvisited cell in one of the 4 cardinal directions (north, east, west,
 * south).
 */
public class PathMaximumSolution {

  public class Coord {
    int row;
    int col;
    int val;

    Coord(int row, int col, int val) {
      this.row = row;
      this.col = col;
      this.val = val;
    }

    @Override
    public String toString() {
      return "(" + row + "," + col + "," + val + ")";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || obj.getClass() != this.getClass()) {
        return false;
      }
      Coord other = (Coord) obj;
      return this.row == other.row && this.col == other.col && this.val == other.val;
    }

    @Override
    public int hashCode() {
      return toString().hashCode();
    }
  }

  private void enqueueCoord(int row, int col, int[][] A, Queue<Coord> queue, Set<Coord> seen) {
    int m = A.length;
    int n = A[0].length;
    if (row >= 0 && row < m && col >= 0 && col < n) {
      Coord coord = new Coord(row, col, A[row][col]);
      if (!seen.contains(coord)) {
        queue.add(new Coord(row, col, A[row][col]));
      }
    }
  }

  public int maximumMinimumPath(int[][] A) {
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // up, down, left, right
    int m = A.length - 1;
    int n = A[0].length - 1;
    Coord end = new Coord(m, n, A[m][n]);
    // Score
    int score = A[0][0];
    // BFS
    Set<Coord> seen = new HashSet<>();
    Queue<Coord> queue = new PriorityQueue<>(256, (l, r) -> r.val - l.val);
    enqueueCoord(0, 0, A, queue, seen);
    while (!queue.isEmpty()) {
      Coord current = queue.remove();
      seen.add(current);
      score = Math.min(score, current.val);
      // Determine if we can terminate
      if (current.equals(end)) {
        return score;
      }
      int row = current.row;
      int col = current.col;
      for (int[] direction : directions) {
        int newRow = row + direction[0];
        int newCol = col + direction[1];
        enqueueCoord(newRow, newCol, A, queue, seen);
      }
    }
    return -1;
  }

  public static void main(String[] args) {

  }
}
