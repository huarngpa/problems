import java.util.*;
import java.util.stream.*;

/**
 * 3Sum
 * 
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 */
public class ThreeSumSolution {

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    Set<String> set = new HashSet<>();
    int n = nums.length;
    for (int i = 0; i < n - 2; i++) {
      // Run a two-sum here
      int first = nums[i];
      if (first > 0) {
        break;
      }
      Map<Integer, Integer> map = new HashMap<>();
      for (int j = i + 1; j < n; j++) {
        int second = nums[j];
        int third = 0 - first - second;
        if (map.containsKey(third)) {
          set.add("" + first + "," + second + "," + third);
        }
        map.put(second, j);
      }
    }
    System.out.println(set);
    // Results processing
    List<List<Integer>> results = new ArrayList<>();
    for (String solution : set) {
      List<Integer> converted = Arrays.asList(solution.split(",")).stream().map(Integer::valueOf)
          .collect(Collectors.toList());
      results.add(converted);
    }
    return results;
  }

  public static void main(String[] args) {

  }
}
