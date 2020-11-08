import java.util.*;

/**
 * Top K Frequent Words
 * 
 * Given a non-empty list of words, return the k most frequent elements. Your
 * answer should be sorted by frequency from highest to lowest. If two words
 * have the same frequency, then the word with the lower alphabetical order
 * comes first.
 */
public class TopKFrequentWordsSolution {
  public static class WordCount {
    int count = 0;
    String word;

    WordCount(String word) {
      this.word = word;
    }

    public String key() {
      return Integer.toString(count) + word;
    }

    public String toString() {
      return key();
    }

    public int compare(WordCount o) {
      if (count == o.count) {
        return o.word.compareTo(word);
      }
      return count - o.count;
    }
  }

  public static class WordCountSorter implements Comparator<WordCount> {
    public int compare(WordCount l, WordCount r) {
      if (l.count == r.count) {
        return l.word.compareTo(r.word);
      }
      return r.count - l.count;
    }
  }

  public static void swap(List<WordCount> values, int i, int j) {
    WordCount temp = values.get(i);
    values.set(i, values.get(j));
    values.set(j, temp);
  }

  public static int partition(List<WordCount> values, int p, int r) {
    WordCount pivot = values.get(r);
    int i = p - 1;
    for (int j = p; j < r; j++) {
      if (values.get(j).compare(pivot) <= 0) {
        i++;
        swap(values, i, j);
      }
    }
    swap(values, i + 1, r);
    return i + 1;
  }

  public static void kthElement(List<WordCount> values, int p, int r, int k) {
    if (p >= r) {
      return;
    }
    int q = partition(values, p, r);
    if (q == k) {
      return;
    } else if (q < k) {
      kthElement(values, q + 1, r, k);
    } else {
      kthElement(values, p, q - 1, k);
    }
  }

  public static List<String> topKFrequent(String[] words, int k) {

    // Step 1: Convert into word counts object
    Map<String, WordCount> wordCounts = new HashMap<>();
    for (String word : words) {
      WordCount wordCount = wordCounts.getOrDefault(word, new WordCount(word));
      wordCount.count += 1;
      wordCounts.put(word, wordCount);
    }
    List<WordCount> values = new ArrayList<>(wordCounts.values());

    // Step 2: Quick-select to find kth most frequent
    int adjustedK = values.size() - k;
    kthElement(values, 0, values.size() - 1, adjustedK);
    // System.out.println(values);

    // Step 3: Transfer and sort based on desire output conditions
    List<WordCount> toSort = new ArrayList<>();
    for (int i = values.size() - 1; i >= adjustedK; i--) {
      toSort.add(values.get(i));
    }
    Collections.sort(toSort, new WordCountSorter());
    List<String> result = new ArrayList<>();
    for (WordCount wc : toSort) {
      result.add(wc.word);
    }
    return result;
  }

  public static void main(String[] args) {
    String[] input1 = new String[] { "i", "love", "leetcode", "i", "love", "coding" };
    List<String> expected1 = new ArrayList<>(Arrays.asList("i", "love"));
    // Explanation: "i" and "love" are the two most frequent words.
    // Note that "i" comes before "love" due to a lower alphabetical order.
    System.out.println(topKFrequent(input1, 2));

    String[] input2 = new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
    List<String> expected2 = new ArrayList<>(Arrays.asList("the", "is", "sunny", "day"));
    // Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    // with the number of occurrence being 4, 3, 2 and 1 respectively.
    System.out.println(topKFrequent(input2, 4));
  }
}
