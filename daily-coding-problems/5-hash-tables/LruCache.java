import java.util.HashMap;
import java.util.Map;

class LruCacheSolution {
  /**
   * Implement an LRU cache.
   * 
   * <p>
   * <ul>
   * <li>set(key, val) removes the least recently used item</li>
   * <li>get(key) gets value at key</li>
   * </ul>
   * </p>
   * 
   * @param args
   */
  public static void main(String[] args) {
    LruCache<String, Integer> cache = new LruCache<>();
    System.out.println(cache.toString());
    cache.set("a", 1);
    cache.set("b", 2);
    cache.set("c", 3);
    cache.set("d", 4);
    cache.set("e", 5);
    cache.set("c", 6);
    System.out.println(cache.toString());
    System.out.printf("get(e) = %s\n", cache.get("e"));
    System.out.println(cache.toString());
  }
}

class LruCache<K, V> {
  public int size = 0;
  public int capacity = 4;
  public Map<K, CacheNode<K, V>> map = new HashMap<>();
  public CacheList<K, V> list = new CacheList<>();

  LruCache() {
  }

  LruCache(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public String toString() {
    return "LruCache(map = " + map.toString() + ", list = " + list.toString() + ")";
  }

  public void set(K key, V val) {
    CacheNode<K, V> node = map.get(key);
    if (node == null) {
      if (size + 1 > capacity) {
        // Remove least recently used
        CacheNode<K, V> lru = list.dequeue();
        map.remove(lru.key);
      }
      node = new CacheNode<K, V>(key, val);
      map.put(key, node);
      list.enqueue(node);
      size += 1;
    } else {
      CacheNode<K, V> prev = node.prev;
      CacheNode<K, V> next = node.next;
      prev.next = next;
      next.prev = prev;
      node.val = val;
      node.next = null;
      node.prev = null;
      list.enqueue(node);
    }
  }

  public V get(K key) {
    CacheNode<K, V> node = map.get(key);
    if (node == null) {
      return null;
    }
    CacheNode<K, V> prev = node.prev;
    CacheNode<K, V> next = node.next;
    prev.next = next;
    next.prev = prev;
    node.next = null;
    node.prev = null;
    list.enqueue(node);
    return node.val;
  }
}

class CacheNode<K, V> {
  public K key;
  public V val;
  public CacheNode<K, V> prev = null;
  public CacheNode<K, V> next = null;

  CacheNode(K key, V val) {
    this.key = key;
    this.val = val;
  }

  @Override
  public String toString() {
    String keyString;
    String valString;
    if (key == null) {
      keyString = "null";
    } else {
      keyString = key.toString();
    }
    if (val == null) {
      valString = "null";
    } else {
      valString = val.toString();
    }
    return "CacheNode(key = " + keyString + ", val = " + valString + ")";
  }
}

class CacheList<K, V> {
  public CacheNode<K, V> head;
  public CacheNode<K, V> tail;
  public int length = 0;

  CacheList() {
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    CacheNode<K, V> curr = head;
    if (curr == null) {
      builder.append("CacheList(null)");
      return builder.toString();
    } else {
      builder.append("CacheList(" + curr.toString());
      curr = curr.next;
    }
    while (curr != null) {
      builder.append(", " + curr.toString());
      curr = curr.next;
    }
    builder.append(", null)");
    return builder.toString();
  }

  public void enqueue(CacheNode<K, V> node) {
    if (head == null && tail == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
    length += 1;
  }

  public CacheNode<K, V> dequeue() {
    if (head == null) {
      return null;
    }
    CacheNode<K, V> ret = head;
    head = head.next;
    ret.prev = null;
    ret.next = null;
    length -= 1;
    return ret;
  }
}