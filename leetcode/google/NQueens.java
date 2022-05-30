import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 */
public class NQueens {

  /**
   * Classic backtracking problem, the key to cracking this problem is to
   * understand the most efficient way to set up the problem and verify a valid
   * solution.
   */
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> results = new LinkedList<>();
    List<String> board = new LinkedList<>();
    int startingRow = 0;
    boolean[] cols = new boolean[n];
    /*
     * Top-left to bottom-right diagonal, note the relationship of: col - row.
     * ---
     * For example: (0, 1), (1, 2), (2, 3) would all be on the same diagonal
     * represented by col-row.
     * ---
     * But note that col - row could potentially be negative so we add n to prevent
     * going negative.
     */
    boolean[] diagonalTLtoBR = new boolean[2 * n];
    /*
     * Top-right to bottom-left diagonal, note the relationship of: col + row.
     * ---
     * For example: (1, 3), (2, 2), (3, 1) would all be on the same diagonal
     * represented by col+row.
     * ---
     * But since col + row can max out to 2n we need to allocate the appropriate
     * space.
     */
    boolean[] diagonalTRtoBL = new boolean[2 * n];
    nQueensHelper(results, board, startingRow, cols, diagonalTLtoBR, diagonalTRtoBL, n);
    return results;
  }

  private void nQueensHelper(List<List<String>> results, List<String> board, int row, boolean[] cols,
      boolean[] diagonalTLtoBR, boolean[] diagonalTRtoBL, int size) {
    // Our base case, we've found a solution once we reach n
    if (row == size) {
      results.add(new ArrayList<>(board));
      return;
    }
    for (int col = 0; col < size; col++) {
      int computedTLtoBR = col - row + size;
      int computedTRtoBL = col + row;
      if (!cols[col] && !diagonalTLtoBR[computedTLtoBR] && !diagonalTRtoBL[computedTRtoBL]) {
        // Add the row to the solution
        char[] result = new char[size];
        Arrays.fill(result, '.');
        result[col] = 'Q';
        board.add(new String(result));
        // Mark/track queen location
        cols[col] = true;
        diagonalTLtoBR[computedTLtoBR] = true;
        diagonalTRtoBL[computedTRtoBL] = true;
        // DFS/recurse to the next row
        nQueensHelper(results, board, row + 1, cols, diagonalTLtoBR, diagonalTRtoBL, size);
        // Backtrack
        board.remove(board.size() - 1);
        cols[col] = false;
        diagonalTLtoBR[computedTLtoBR] = false;
        diagonalTRtoBL[computedTRtoBL] = false;
      }
    }
  }
}
