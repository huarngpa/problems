import java.util.*;

/**
 * Most Common Word
 * 
 * Given a paragraph and a list of banned words, return the most frequent word
 * that is not in the list of banned words. It is guaranteed there is at least
 * one word that isn't banned, and that the answer is unique. Words in the list
 * of banned words are given in lowercase, and free of punctuation. Words in the
 * paragraph are not case sensitive. The answer is in lowercase.
 */
public class MostCommonWordSolution {

  public static String mostCommonWord(String paragraph, String[] banned) {

    // Step 1: Clean the paragraph
    String pattern = "[^\\s\\w]";
    String[] cleaned = paragraph.replaceAll(pattern, " ").toLowerCase().split(" ");

    // Step 2: Construct a set to lookup banned words
    Set<String> bannedSet = new HashSet<>();
    for (String word : banned) {
      bannedSet.add(word);
    }

    // Step 3: Use a Kadane like algorithm
    Map<String, Integer> map = new HashMap<>();
    String mostFrequent = cleaned[0];
    for (String word : cleaned) {
      if (word.equals("")) {
        continue;
      }
      if (!bannedSet.contains(word)) {
        int count = map.getOrDefault(word, 0);
        count++;
        map.put(word, count);
        if (count > map.getOrDefault(mostFrequent, 0)) {
          mostFrequent = word;
        }
      }
    }

    return mostFrequent;
  }

  public static void main(String[] args) {
    String input = "Bob hit a ball, the hit BALL flew far after it was hit.";
    String[] banned = new String[] { "hit" };
    String output = "ball";
    // Explanation:
    // "hit" occurs 3 times, but it is a banned word.
    // "ball" occurs twice (and no other word does), so it is the most frequent
    // non-banned word in the paragraph.
    // Note that words in the paragraph are not case sensitive,
    // that punctuation is ignored (even if adjacent to words, such as "ball,"),
    // and that "hit" isn't the answer even though it occurs more because it is
    // banned.

    System.out.println(mostCommonWord(input, banned));
  }
}
