/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 */
public class MinimumDominoRotations {
  /**
   * The trick to this problem is realizing that you can use an array to store
   * occurrences.
   * Then understanding that same accounting plays a role and how to evaluate the
   * optimal solution.
   */
  public int minDominoRotations(int[] tops, int[] bottoms) {
    // Each i position represents occurrence at i die-side
    int[] topOccurrences = new int[7];
    int[] bottomOccurrences = new int[7];
    int[] sameOccurrences = new int[7];
    int size = tops.length;
    for (int i = 0; i < size; i++) {
      int top = tops[i];
      int bottom = bottoms[i];
      topOccurrences[top]++;
      bottomOccurrences[bottom]++;
      if (top == bottom) {
        sameOccurrences[top]++;
      }
    }
    // Walk the die-sides and find a valid solution
    int transforms = Integer.MAX_VALUE;
    for (int i = 0; i < 7; i++) {
      if (topOccurrences[i] + bottomOccurrences[i] - sameOccurrences[i] == size) {
        transforms = Math.min(transforms,
            Math.min(topOccurrences[i] - sameOccurrences[i], bottomOccurrences[i] - sameOccurrences[i]));
      }
    }
    if (transforms == Integer.MAX_VALUE) {
      return -1;
    }
    return transforms;
  }
}
