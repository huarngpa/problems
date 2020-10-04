package algorithms;

public class RodCutting {
  static int cutRod(int[] price, int n) {
    if (n <= 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      max = Math.max(max, price[i] + cutRod(price, n - i - 1));
    }
    return max;
  }

  static int cutRodIterative(int[] price, int n) {
    int[] result = new int[n + 1];
    result[0] = 0;
    for (int i = 1; i <= n; i++) {
      int max = Integer.MIN_VALUE;
      for (int j = 0; j < i; j++) {
        max = Math.max(max, price[j] + result[i - j - 1]);
      }
      result[i] = max;
    }
    return result[n];
  }

  public static void main(String[] args) {
    System.out.println("Rod cutting");
    int[] arr = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
    int size = arr.length;
    System.out.println(cutRod(arr, size));
  }
}
