import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer n, return all the strobogrammatic numbers that are of length
 * n. You may return the answer in any order.
 */
public class FindStrobogrammaticNumbers {

  public List<String> findStrobogrammatic(int n) {
    return findStrobogrammaticHelper(n, n);
  }

  // The key idea here is that you need to recurse the inner part
  // and handle the edge cases by organizing the problem correctly.
  private List<String> findStrobogrammaticHelper(int size, int originalSize) {
    if (size == 0) {
      return Collections.singletonList("");
    }
    if (size == 1) {
      return Arrays.asList("0", "1", "8");
    }
    List<String> inner = findStrobogrammaticHelper(size - 2, originalSize);
    List<String> results = new LinkedList<>();
    for (String val : inner) {
      if (size < originalSize) {
        results.add("0" + val + "0");
      }
      results.add("1" + val + "1");
      results.add("6" + val + "9");
      results.add("8" + val + "8");
      results.add("9" + val + "6");
    }

    return results;
  }
}
