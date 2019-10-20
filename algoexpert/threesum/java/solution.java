import java.util.ArrayList;
import java.util.Arrays;

class Solution {
  public static int[][] solution(int[] array, int target) {
    Arrays.sort(array);
    int size = array.length;
    ArrayList<int[]> res = new ArrayList<>();

    for (int idx = 0; idx < size; idx++) {

      int left = idx + 1;
      int right = size - 1;

      while (left < right) {
        int currNum = array[idx];
        int leftVal = array[left];
        int rightVal = array[right];
        int computed = currNum + leftVal + rightVal;

        if (computed == target) {
          res.add(new int[]{currNum, leftVal, rightVal});
          left++;
          right--;
        } else if (computed < target) {
          left++;
        } else {
          right--;
        }
      }
    }

    int[][] capture = new int[res.size()][];
    return res.toArray(capture);
  }

  public static void main(String[] args) {
    int target = 0;
    int[] array = new int[]{12, 3, 1, 2, -6, 5, -8, 6};

    StringBuilder builder = new StringBuilder();
    builder.append("target = " + Integer.toString(target));
    builder.append("; array = " + Arrays.toString(array));
    System.out.println(builder.toString());

    builder = new StringBuilder();
    builder.append("solution = [");
    for (int[] inner : solution(array, target)) {
      builder.append(Arrays.toString(inner) + ",");
    }
    builder.append("]");
    System.out.println(builder.toString());
  }
}
