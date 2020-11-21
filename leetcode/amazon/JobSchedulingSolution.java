
/**
 * Minimum Difficulty of a Job Schedule
 * 
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To
 * work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * 
 * You have to finish at least one task every day. The difficulty of a job
 * schedule is the sum of difficulties of each day of the d days. The difficulty
 * of a day is the maximum difficulty of a job done in that day.
 * 
 * Given an array of integers jobDifficulty and an integer d. The difficulty of
 * the i-th job is jobDifficulty[i].
 * 
 * Return the minimum difficulty of a job schedule. If you cannot find a
 * schedule for the jobs return -1.
 */
public class JobSchedulingSolution {

  public static void printTable(int[][] dp) {
    for (int i = 0; i < dp.length; i++) {
      String s = "";
      for (int j = 0; j < dp[0].length; j++) {
        s += dp[i][j] + ", ";
      }
      System.out.println(s);
    }
  }

  public static int minDifficulty(int[] jobDifficulty, int d) {
    int n = jobDifficulty.length;
    // check if problem can be satisifed with inputs
    if (n < d)
      return -1;
    // setup problem space (d x n partially filled table)
    int[][] dp = new int[d][n];
    // base cases, one day is the max of the jobs
    dp[0][0] = jobDifficulty[0];
    for (int j = 1; j < n; j++) {
      dp[0][j] = Math.max(jobDifficulty[j], dp[0][j - 1]);
    }
    // d x n^2 solution
    for (int i = 1; i < d; i++) {
      for (int j = i; j < n; j++) {
        int localMax = jobDifficulty[j];
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = j; k >= i; k--) {
          // backtrack to find the min difficulty (days = i).
          // must have at least one value for each day, hence partial fill
          localMax = Math.max(localMax, jobDifficulty[k]);
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + localMax);
        }
      }
    }
    printTable(dp);
    return dp[d - 1][n - 1];
  }

  public static void main(String[] args) {
    int[] input1 = new int[] { 6, 5, 4, 3, 2, 1 };
    int d1 = 2;
    System.out.println(minDifficulty(input1, d1));

    int[] input2 = new int[] { 7, 1, 7, 1, 7, 1 };
    int d2 = 3;
    System.out.println(minDifficulty(input2, d2));
  }
}
