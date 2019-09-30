import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Solution {
  public static int[] solution(int[] input) {
    int size = input.length;
    ArrayList<Integer> sorted = new ArrayList<>(Collections.nCopies(size, Integer.MAX_VALUE));
    int[] res = new int[size];

    for (int i = size - 1; i >= 0; i--) {
      int num = input[i];
      int smallerThan = Collections.binarySearch(sorted, num);
      // If item not present, returns "(-(insertion point) - 1)", so we solve for insertion point
      smallerThan = smallerThan < 0 ? -(smallerThan + 1) : smallerThan;
      res[i] = smallerThan;
      sorted.remove(size - 1);
      sorted.add(smallerThan, new Integer(num));
    }
    return res;
  }

  public static void main(String[] args) {
    int[] example = new int[]{3, 4, 9, 6, 1};

    System.out.println("example: " + Arrays.toString(example));
    System.out.println("solution: " + Arrays.toString(solution(example)));
  }
}