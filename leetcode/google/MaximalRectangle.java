import java.util.Stack;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 */
public class MaximalRectangle {

  // The trick to cracking this problem is found in another problem,
  // maximum rectangle in a histogram. You scan the matrix row by row
  // creating a histogram that the solution to another problem can help
  // you solve.
  public int maximalRectangle(char[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[] scanned = new int[cols];
    int area = 0;
    for (int i = 0; i < rows; i++) {
      // Scan the current row and build a "histogram"
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == '1') {
          scanned[j]++;
        } else {
          scanned[j] = 0;
        }
      }
      // Compute the maximal area by running largest rect on histogram
      area = Math.max(area, largestRectangleArea(scanned));
    }
    return area;
  }

  private int largestRectangleArea(int[] heights) {
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
