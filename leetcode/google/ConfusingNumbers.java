import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/confusing-number-ii/
 */
public class ConfusingNumbers {

  private static Map<Integer, Integer> validDigits = new HashMap<>() {
    {
      put(0, 0);
      put(1, 1);
      put(6, 9);
      put(8, 8);
      put(9, 6);
    }
  };

  public int confusingNumberII(int n) {
    return confusingNumberHelper(n, 0);
  }

  private int confusingNumberHelper(int limit, int current) {
    int result = 0;
    if (current > limit) {
      return result;
    }
    if (isConfusingNumber(current)) {
      result++;
    }
    for (Integer validDigit : validDigits.keySet()) {
      int child = current * 10 + validDigit;
      if (child == 0) {
        continue;
      }
      result += confusingNumberHelper(limit, child);
    }
    return result;
  }

  // The key here is "different" number where each digit is valid
  private boolean isConfusingNumber(int n) {
    int original = n;
    int transformed = 0;
    while (original != 0) {
      Integer lastDigit = validDigits.get(original % 10);
      if (lastDigit == null) {
        continue;
      }
      transformed = transformed * 10 + validDigits.get(original % 10);
      original /= 10;
    }
    return transformed != n;
  }
}
