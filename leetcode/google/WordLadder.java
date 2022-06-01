import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);
    // No solution case
    if (!wordSet.contains(endWord)) {
      return 0;
    }
    // Run BFS, the understanding here is that BFS will find the shortest-path!
    Set<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.add(beginWord);
    visited.add(beginWord);
    int transformations = 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String word = queue.poll();
        if (word.equals(endWord)) {
          return transformations;
        }
        addValidTransformations(word, queue, wordSet, visited);
      }
      transformations++;
    }
    return 0;
  }

  private void addValidTransformations(String word, Queue<String> queue, Set<String> wordSet, Set<String> visited) {
    for (int j = 0; j < word.length(); j++) {
      for (char k = 'a'; k <= 'z'; k++) {
        char[] array = word.toCharArray();
        array[j] = k;
        String next = new String(array);
        if (wordSet.contains(next) && !visited.contains(next)) {
          queue.add(next);
          visited.add(next);
        }
      }
    }
  }
}
