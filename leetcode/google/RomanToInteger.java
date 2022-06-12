/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class RomanToInteger {

  /*
   * This question can end up quite difficult, but the trick is to understand how
   * to convert tricky number (ie. 4, 9, 40, etc.)
   */
  public int romanToInt(String s) {
    int[] nums = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case 'M':
          nums[i] = 1000;
          break;
        case 'D':
          nums[i] = 500;
          break;
        case 'C':
          nums[i] = 100;
          break;
        case 'L':
          nums[i] = 50;
          break;
        case 'X':
          nums[i] = 10;
          break;
        case 'V':
          nums[i] = 5;
          break;
        case 'I':
          nums[i] = 1;
          break;
      }
      ;
    }
    int result = 0;
    for (int i = 0; i < s.length() - 1; i++) {
      if (nums[i] < nums[i + 1]) {
        result -= nums[i];
      } else {
        result += nums[i];
      }
    }
    result += nums[s.length() - 1];
    return result;
  }
}
