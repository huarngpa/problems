import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleHistogram {
  // The trick to cracking this problem is creating a back-tracking mechanism
  // using a stack and manipulating pointers in a for-loop.
  public int largestRectangleArea(int[] heights) {
    Stack<Integer> positions = new Stack<>();
    int area = 0;
    for (int i = 0; i <= heights.length; i++) {
      int height = i == heights.length ? 0 : heights[i];
      if (positions.isEmpty()) {
        positions.push(i);
      } else if (height >= heights[positions.peek()]) {
        positions.push(i);
      } else {
        // When you find the new low, you backtrack and compute area.
        // Note that the stack's invariant is that values are decreasing.
        int currentHeight = heights[positions.pop()];
        int rightBoundary = i - 1;
        int leftBoundary = positions.isEmpty() ? -1 : positions.peek();
        int proposedArea = currentHeight * (rightBoundary - leftBoundary);
        area = Math.max(area, proposedArea);
        i--;
      }
    }
    return area;
  }
}
