import java.util.Arrays;

class Solution {
  public static int maxSubArray(int[] input) {
    // Uses Kadane's algorithm
    int maxSeen = 0;
    int maxHere = 0;

    for (int num : input) {
      maxHere += num;
      maxHere = maxHere > 0 ? maxHere : 0;
      maxSeen = maxHere > maxSeen ? maxHere : maxSeen;
    }
    return maxSeen;
  }

  public static int minSubArray(int[] input) {
    int minSeen = 0;
    int minHere = 0;

    for (int num : input) {
      minHere += num;
      minHere = minHere < 0 ? minHere : 0;
      minSeen = minHere < minSeen ? minHere : minSeen;
    }
    return minSeen;
  }

  public static int circularMaxSubAray(int[] input) {
    int min = minSubArray(input);
    int sum = 0;
    for (int num : input) {
      sum += num;
    }
    int circularMaxSubArray = sum - min;
    return Math.max(circularMaxSubArray, maxSubArray(input));
  }

  public static void main(String[] args) {
    int[] ex1 = new int[]{34, -50, 42, 14, -5, 86};
    int[] ex2 = new int[]{-5, -1, -8, -9};

    System.out.println("example1: " + Arrays.toString(ex1));
    System.out.println("example2: " + Arrays.toString(ex2));

    System.out.println("solution1: " + Integer.toString(maxSubArray(ex1)));
    System.out.println("solution2: " + Integer.toString(maxSubArray(ex2)));

    System.out.println("circular1: " + Integer.toString(circularMaxSubAray(ex1)));
    System.out.println("circular2: " + Integer.toString(circularMaxSubAray(ex2)));
  }
}
