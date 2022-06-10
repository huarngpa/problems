import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
public class MinRemovalForValid {

  /*
   * The trick to solving this question is understanding what we're building to
   * filter out invalid parens.
   */
  public String minRemoveToMakeValid(String s) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        stack.add(i);
      }
      if (c == ')') {
        if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
          stack.pop();
        } else {
          stack.add(i);
        }
      }
    }
    Set<Integer> invalid = new HashSet<>(stack);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (!invalid.contains(i)) {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }
}
