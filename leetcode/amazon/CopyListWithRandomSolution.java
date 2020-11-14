import java.util.*;

/**
 * Copy List with Random Pointer
 * 
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 */
public class CopyListWithRandomSolution {

  public static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }

    public String toString() {
      return "[" + val + "," + random.toString() + "] ->";
    }
  }

  public static void copyNodeToMap(Map<Node, Node> map, Node node) {
    if (node != null) {
      map.putIfAbsent(node, new Node(node.val));
    }
  }

  public static Node copyRandomList(Node head) {
    Map<Node, Node> map = new HashMap<>();
    copyNodeToMap(map, head);
    Node newHead = map.getOrDefault(head, null);
    Node current = head;
    Node copyCurrent = newHead;
    while (current != null) {
      copyNodeToMap(map, current.random);
      copyNodeToMap(map, current.next);
      copyCurrent.next = map.getOrDefault(current.next, null);
      copyCurrent.random = map.getOrDefault(current.random, null);
      current = current.next;
      copyCurrent = copyCurrent.next;
    }
    System.out.println(map);
    return newHead;
  }

  public static void main(String[] args) {

  }
}
