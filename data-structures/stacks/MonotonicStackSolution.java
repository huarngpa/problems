package stacks;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MonotonicStackSolution {

  public static void main(String[] args) {

    List<Integer> list = Arrays.asList(3, 1, 6, 2, 5, 4);

    // Create a monotonic stack
    Stack<Integer> stack = new Stack<>();
    for (Integer num : list) {
      while (!stack.isEmpty() && num >= stack.peek()) {
        stack.pop();
      }
      stack.add(num);
      System.out.println(String.format("Num=%d Stack=%s", num, stack));
    }

    // A "monotonic" property is maintained and this has useful implications. For
    // instance, in the area of a histogram problem we need to know where the
    // previous smaller number/index exists. What other problems might a monotonic
    // stack come in handy? Also keep in mind that you're not limited to a stack, a
    // Deque can also be used as the underlying data structure
  }
}
