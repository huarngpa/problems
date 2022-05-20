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
}
