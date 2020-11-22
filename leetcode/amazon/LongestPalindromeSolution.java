import java.util.*;

/**
 * Longest Palindromic Substring
 * 
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromeSolution {

  public String longestPalindrome(String s) {
    String longest = "";
    int n = s.length();
    boolean[][] m = new boolean[n][n];
    for (boolean[] row : m) {
      Arrays.fill(row, false);
    }
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < (n - k); i++) {
        int j = i + k;
        String proposed = s.substring(i, j + 1);
        int length = proposed.length();
        if (i == j) {
          m[i][j] = true;
        } else if (s.charAt(i) == s.charAt(j)) {
          if (length == 2) {
            m[i][j] = true;
          } else if (m[i + 1][j - 1]) {
            m[i][j] = true;
          }
        }
        if (m[i][j] && length > longest.length()) {
          longest = proposed;
        }
      }
    }
    return longest;
  }

  public static void main(String[] args) {

  }
}
