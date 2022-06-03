/**
 * https://leetcode.com/problems/expressive-words/
 */
public class ExpressiveWords {
  public int expressiveWords(String s, String[] words) {
    int count = 0;
    for (String word : words) {
      if (isExpressiveWord(s, word)) {
        count++;
      }
    }
    return count;
  }

  private boolean isExpressiveWord(String S, String T) {
    int m = S.length();
    int n = T.length();
    int i = 0;
    int j = 0;
    while (i < m && j < n) {
      if (S.charAt(i) == T.charAt(j)) {
        // 1. Count how many of the same T has
        // 2. Count how many of the same S has
        // 3. Compare and short-circuit as required
        char c = S.charAt(i);
        int k = i;
        int l = j;
        while (k + 1 < m && c == S.charAt(k + 1)) {
          k++;
        }
        while (l + 1 < n && c == T.charAt(l + 1)) {
          l++;
        }
        int sPart = k - i + 1;
        int tPart = l - j + 1;
        // The tricky part here was getting the expression right (according to the
        // specs).
        boolean isValidPart = sPart == tPart || ((sPart > tPart) && sPart >= 3);
        if (!isValidPart) {
          return false;
        }
        i = k + 1;
        j = l + 1;
      } else {
        i++;
      }
    }
    return i >= m && j >= n;
  }
}
