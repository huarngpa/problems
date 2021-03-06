import java.util.*;

/**
 * Rotting Oranges
 * 
 * In a given grid, each cell can have one of three values:
 * 
 * the value 0 representing an empty cell; the value 1 representing a fresh
 * orange; the value 2 representing a rotten orange.
 * 
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten
 * orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange. If this is impossible, return -1 instead.
 */
public class RottingOrangesSolution {

  class Point {
    int i;
    int j;
    int time;

    Point(int i, int j, int time) {
      this.i = i;
      this.j = j;
      this.time = time;
    }

    public String toString() {
      return "(" + i + ", " + j + ", " + time + ")";
    }
  }

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    Queue<Point> queue = new LinkedList<>();
    // Load the queue with rotting oranges
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          queue.add(new Point(i, j, 0));
        }
      }
    }
    // BFS
    int time = 0;
    while (queue.peek() != null) {
      Point u = queue.remove();
      BFSHelper(grid, queue, u.i + 1, u.j, u.time); // down
      BFSHelper(grid, queue, u.i - 1, u.j, u.time); // up
      BFSHelper(grid, queue, u.i, u.j - 1, u.time); // left
      BFSHelper(grid, queue, u.i, u.j + 1, u.time); // right
      if (u.time > time) {
        time = u.time;
      }
      // System.out.println(queue);
    }
    // Check if we have a solution
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          return -1;
        }
      }
    }
    return time;
  }

  public void BFSHelper(int[][] grid, Queue<Point> queue, int i, int j, int prevTime) {
    int m = grid.length;
    int n = grid[0].length;
    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
      grid[i][j] = 2;
      queue.add(new Point(i, j, prevTime + 1));
    }
  }

  public static void main(String[] args) {

  }
}
