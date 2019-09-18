import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
  public static int[] accumulateProduct(int[] input) {
    int size = Array.getLength(input);
    int[] res = new int[size];
    for (int i = 0; i < size; i++) {
      int num = input[i];
      if (i != 0) {
        res[i] = res[i - 1] * num;
      } else {
        res[i] = num;
      }
    }
    return res;
  }

  public static int[] reverse(int[] input) {
    int size = Array.getLength(input);
    int[] res = Arrays.copyOf(input, size);
    for (int i = 0; i < size / 2; i++) {
      int opp = size - i - 1;
      res[i] = input[opp];
      res[opp] = input[i];
    }
    return res;
  }

  public static int[] solution(int[] input) {
    int size = Array.getLength(input);
    int[] prefixes = accumulateProduct(input);
    int[] suffixes = reverse(accumulateProduct(reverse(input)));

    int[] res = new int[size];
    for (int i = 0; i < size; i++) {
      int prev = i - 1;
      int next = i + 1;
      res[i] = 1;
      if (prev >= 0) {
        res[i] *= prefixes[prev];
      }
      if (next < size) {
        res[i] *= suffixes[next];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] example = new int[]{1, 2, 3, 4, 5};
    System.out.println("example: " + Arrays.toString(example));

    System.out.println("result: " + Arrays.toString(solution(example)));
  }
}
