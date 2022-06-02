import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-window-subsequence/
 */
public class MinimumWindowSubsequence {

  /*
   * The trick to cracking this problem is learning how to setup the problem to
   * compute the subsequence. Hint: draw out what the algorithm is doing.
   */
  public String minWindow(String S, String T) {
    int m = S.length();
    int n = T.length();
    // dp[i][j] = x; where T[0..j] is a subsequence of S[x..i].
    // Thus, our goal is to evaluate dp[i][n] and find the min subsequence.
    int[][] dp = new int[m][n];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    // Find initial matches where the T[0] == S[i].
    for (int i = 0; i < m; i++) {
      if (S.charAt(i) == T.charAt(0)) {
        dp[i][0] = i;
      }
    }
    // Create a "mask" that finds valid subsequences.
    for (int j = 1; j < n; j++) {
      int x = -1;
      for (int i = 0; i < m; i++) {
        if (S.charAt(i) == T.charAt(j)) {
          dp[i][j] = x;
        }
        x = Math.max(x, dp[i][j - 1]);
      }
    }
    // One more pass to compute the results.
    String result = "";
    for (int i = 0; i < m; i++) {
      int x = dp[i][n - 1];
      boolean canEval = x != -1;
      if (canEval) {
        String subseq = S.substring(x, i + 1);
        if (result.equals("") || subseq.length() < result.length()) {
          result = subseq;
        }
      }
    }
    return result;
  }
}
