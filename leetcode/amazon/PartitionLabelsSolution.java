import java.util.*;

/**
 * Partition Labels
 * 
 * A string S of lowercase English letters is given. We want to partition this
 * string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 */
public class PartitionLabelsSolution {

  public static List<Integer> partitionLabels(String S) {
    Map<Character, Integer> maxMap = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      maxMap.put(S.charAt(i), i);
    }
    List<Integer> result = new ArrayList<>();
    int prev = 0;
    int max = 0;
    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      max = Math.max(max, maxMap.get(c));
      if (i == max) {
        result.add(max - prev + 1);
        prev = max + 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String S = "ababcbacadefegdehijhklij";
    List<Integer> expected = new ArrayList<>(Arrays.asList(9, 7, 8));
    System.out.println(partitionLabels(S));
  }
}
