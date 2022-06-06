import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array of integers of size N, return an array of the same size
 * containing in each position the index of the previous smaller element on the
 * left (if there is not a previous smaller element use -1 as index). Consider 0
 * the first index in the array and N-1 the last index.
 * 
 * Example 1: input: [2,1,5,6,2,3] output: [-1,-1,1,2,1,4]
 * Example 2: input: [1,2,3,4] output: [-1,0,1,2]
 * Example 3: input: [4,3,2,1] output: [-1,-1,-1,-1]
 */
public class PreviousSmallerElement {

  /*
   * This problem has important implications around solving the histogram and
   * matrix area problems.
   */
  public int[] previousSmallerElement(int[] input) {
    int size = input.length;
    int[] result = new int[size];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < size; i++) {
      int curr = input[i];
      result[i] = -1;
      while (!stack.isEmpty()) {
        int prevIdx = stack.peek();
        int prev = input[prevIdx];
        if (prev < curr) {
          result[i] = prevIdx;
          break;
        } else {
          stack.pop();
        }
      }
      stack.push(i);
    }
    return result;
  }

  public static void main(String[] args) {
    PreviousSmallerElement pse = new PreviousSmallerElement();
    int[] input1 = new int[] { 1, 14, 12, 10, 4 };
    int[] input2 = new int[] { 9, 6, 10, 9, 5 };
    System.out.println(Arrays.toString(input1));
    System.out.println(Arrays.toString(pse.previousSmallerElement(input1)));
    System.out.println(Arrays.toString(input2));
    System.out.println(Arrays.toString(pse.previousSmallerElement(input2)));
  }
}
