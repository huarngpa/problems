import java.util.*;

/**
 * Two Sum
 * 
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 */
public class TwoSumSolution {

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int num = nums[i];
      int diff = target - num;
      if (map.containsKey(diff)) {
        return new int[] { i, map.get(diff) };
      }
      map.put(num, i);
    }
    return null;
  }

  public static void main(String[] args) {

  }
}
