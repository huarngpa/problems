/**
 * https://leetcode.com/problems/race-car/
 */
public class RaceCar {

  // This problem is all about understanding the problem and applying the
  // mathematical relationships using bottoms-up dynamic programming
  public int raceCar(int target) {
    // Represents the minimum number of instructions (ie. A, A, ... R, ...) to reach
    // ith position.
    int[] T = new int[target + 1];
    for (int i = 1; i <= target; i++) {
      T[i] = Integer.MAX_VALUE;
      /*
       * m = accelerate instructions
       * j = 2^m - 1, or position if you just accelerated
       */
      int m = 1, j = 1;
      /*
       * Scenario j < i:
       * ---
       * A reverse occurs, but we're not at the ith position. That means there will be
       * some number of A's and then another reverse and some additional number of
       * A's.
       */
      for (; j < i; j = (1 << ++m) - 1) {
        /*
         * q = accelerate instructions after the first reverse
         * p = 2^p - 1, position but headed in the opposite direction
         */
        for (int q = 0, p = 0; p < j; p = (1 << ++q) - 1) {
          /*
           * T[(i - (j - p))] = min # of instructions we've already computed, note that we
           * move forward by j and reverse by p and so we need the reminder i - (j - p)
           * 
           * m + 1 + q + 1 + T[(i - (j - p))] = total instructions required under this
           * scenario
           */
          T[i] = Math.min(T[i], m + 1 + q + 1 + T[(i - (j - p))]);
        }
      }
      /**
       * Remaining scenarios:
       * 
       * Scenario j == i:
       * ---
       * We simply accelerate all the way to the ith position. Hence, m.
       * 
       * Scenario j > i:
       * ---
       * We overshot ith position and need to reverse and accelerate in the opposite
       * direction. Hence, m + 1 + T[j - i].
       */
      T[i] = Math.min(T[i], m + (i == j ? 0 : 1 + T[j - i]));
    }
    return T[target];
  }
}
