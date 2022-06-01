import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

  private static class Tracking {

    Map<Character, Integer> tracker = new HashMap<>();
    boolean hasSubString = false;

    public Tracking(String t) {
      for (char c : t.toCharArray()) {
        tracker.put(c, tracker.getOrDefault(c, 0) + 1);
      }
    }

    public void add(char c) {
      if (tracker.containsKey(c)) {
        tracker.put(c, tracker.get(c) - 1);
        updateHasSubString();
      }
    }

    public void remove(char c) {
      if (tracker.containsKey(c)) {
        tracker.put(c, tracker.get(c) + 1);
        updateHasSubString();
      }
    }

    private void updateHasSubString() {
      hasSubString = tracker.values().stream().allMatch(v -> v <= 0);
    }
  }

  /*
   * The trick to cracking this problem is to understand how to do
   * "sliding window". Then it is simply implementation details to solve the
   * requirements.
   */
  public String minWindow(String s, String t) {
    int size = s.length();
    Tracking tracking = new Tracking(t);
    String substring = "";
    int i = 0;
    int j = 0;
    while (j < size) {
      tracking.add(s.charAt(j));
      while (i <= j && tracking.hasSubString) {
        String proposed = s.substring(i, j + 1);
        if (substring.equals("") || proposed.length() < substring.length()) {
          substring = proposed;
        }
        tracking.remove(s.charAt(i));
        i++;
      }
      j++;
    }
    return substring;
  }
}
