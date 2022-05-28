/**
 * https://leetcode.com/problems/long-pressed-name/
 */
public class LongPressedName {

  // This problem can actually become a nightmare if you opt to try to
  // keep track of two-pointers for each string. The trick is understanding
  // the constraints (invariants) and realize that you need to backtrack.
  public boolean isLongPressedName(String name, String typed) {
    int n = name.length();
    int m = typed.length();
    int i = 0;
    int j = 0;
    while (j < m) {
      if (i < n && name.charAt(i) == typed.charAt(j)) {
        i++;
        j++;
        continue;
      }
      if (i > 0 && name.charAt(i - 1) == typed.charAt(j)) {
        j++;
        continue;
      } else {
        return false;
      }
    }
    return i == n && j == m;
  }
}
