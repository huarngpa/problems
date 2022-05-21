import java.util.HashMap;
import java.util.Map;

public class SubarraySumsEqualsK {
  // https://leetcode.com/problems/subarray-sum-equals-k/discuss/803317/Java-Solution-with-Detailed-Explanation
  public int subarraySum(int[] nums, int k) {
    // Creates sums so we can exploit accumlated sums
    int size = nums.length;
    int[] sums = new int[size + 1];
    sums[0] = 0;
    for (int i = 1; i < size + 1; i++) {
      sums[i] = sums[i - 1] + nums[i - 1];
    }
    // Compute the subarray sum
    int count = 0;
    for (int end = nums.length - 1; end >= 0; end--) {
      for (int start = end; start >= 0; start--) {
        // Use the accumulated sums to calculate subarray sums
        int subarraySum = sums[end + 1] - sums[start];
        if (subarraySum == k) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * An O(n) time solution, but you need to understand the previous solution
   * and exploit the relationships.
   */
  public int subarraySumOptimized(int[] nums, int k) {
    int count = 0;
    int runningSum = 0;
    // Stores the occurence of subarray sums we've seen thus far
    Map<Integer, Integer> occurrences = new HashMap<>();
    // Zero by default occurs once (use the current subarray sum)
    occurrences.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      runningSum += nums[i];
      // Use the relationship: sums[end] - sums[start] = k
      // You use the occurences seen because N subarrays seen
      // satisfy this condition.
      count += occurrences.getOrDefault(runningSum - k, 0);
      occurrences.put(runningSum, occurrences.getOrDefault(runningSum, 0) + 1);
    }
    return count;
  }
}
