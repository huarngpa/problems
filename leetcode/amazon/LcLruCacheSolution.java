import java.util.*;

/**
 * LRU Cache
 * 
 * Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache.
 */
public class LcLruCacheSolution {
  class LRUNode {
    int key;
    int value;

    LRUNode(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public String toString() {
      return "(" + key + ", " + value + ")";
    }
  }

  class LRUCache {
    int capacity;
    Map<Integer, LRUNode> map = new HashMap<>();
    List<LRUNode> cache = new LinkedList<>();

    public LRUCache(int capacity) {
      this.capacity = capacity;
    }

    public int get(int key) {
      if (map.containsKey(key)) {
        LRUNode node = map.get(key);
        cache.remove(node);
        cache.add(node);
        return node.value;
      }
      return -1;
    }

    public void put(int key, int value) {
      LRUNode node;
      if (map.containsKey(key)) {
        node = map.get(key);
        node.value = value;
        cache.remove(node);
      } else {
        node = new LRUNode(key, value);
        map.put(key, node);
      }
      cache.add(node);
      if (cache.size() > capacity) {
        LRUNode toRemove = cache.remove(0);
        map.remove(toRemove.key);
      }
    }
  }

  public static void main(String[] args) {

  }
}
