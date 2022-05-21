/**
 * Given a matrix and a target, return the number of non-empty submatrices that
 * sum to target.
 * 
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 */
public class SubmatrixSumsEqualsK {
  public int numSubmatrixSumTarget(int[][] matrix, int target) {
    // Create our prefix sums matrix
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] sums = new int[rows][cols + 1];
    for (int i = 0; i < rows; i++) {
      for (int j = 1; j < cols + 1; j++) {
        sums[i][j] = sums[i][j - 1] + matrix[i][j - 1];
      }
    }
    // The trick to understanding this problem is that you can quickly
    // find submatrix sums by applying the subarray sums idea:
    // sums[end] - sum[start]
    // We can apply this to every row!
    int count = 0;
    for (int start = 0; start < cols; start++) {
      for (int end = start + 1; end < cols + 1; end++) {
        // Transpose the problem and solve using subarray sums
        int[] subarray = new int[rows];
        for (int row = 0; row < rows; row++) {
          subarray[row] = sums[row][end] - sums[row][start];
        }
        count += subarraySum(subarray, target);
      }
    }
    return count;
  }

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
