import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeStringSolution {

  /*
   * The trick to this problem is that you need a special data structure (deque)
   * and recursion in order to elegantly solve it.
   */
  public String decodeString(String s) {
    Deque<Character> deque = new LinkedList<>();
    for (char c : s.toCharArray()) {
      deque.add(c);
    }
    return decodeStringHelper(deque);
  }

  private String decodeStringHelper(Deque<Character> deque) {
    StringBuilder sb = new StringBuilder();
    int num = 0;
    while (deque.size() > 0) {
      Character c = deque.poll();
      if (Character.isDigit(c)) {
        num = num * 10 + c - '0';
      } else if (c == '[') {
        String substring = decodeStringHelper(deque);
        for (int i = 0; i < num; i++) {
          sb.append(substring);
        }
        num = 0;
      } else if (c == ']') {
        break;
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }
}
