/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathInMatrix {

  private static final int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

  // The trick to solving this problem is DFS, but also to use memoization to
  // store previously computed results.
  public int longestIncreasingPath(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] cache = new int[m][n];
    int max = 1;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        max = Math.max(max, dfs(matrix, i, j, m, n, cache));
      }
    }
    return max;
  }

  private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
    if (cache[i][j] > 0) {
      return cache[i][j];
    }
    int max = 1;
    for (int[] direction : directions) {
      int x = i + direction[0];
      int y = j + direction[1];
      if (x < 0 || x >= m || y < 0 || y >= n)
        continue;
      if (matrix[x][y] > matrix[i][j]) {
        max = Math.max(max, 1 + dfs(matrix, x, y, m, n, cache));
      }
    }
    cache[i][j] = max;
    return max;
  }
}
