import java.io.Serializable;

class ReverseLinkedList {
  /**
   * Recursively reverses a singly linked list
   * 
   * @param head
   * @return The head of the reversed list
   */
  public static Node<Integer> recursiveReverse(Node<Integer> head) {

    if (head == null) {
      return null;
    }

    if (head.next == null) {
      return head;
    }

    Node<Integer> tail = head.next;

    Node<Integer> newHead = recursiveReverse(tail);

    tail.next = head;
    head.next = null;

    return newHead;
  }

  public static void main(String[] args) {
    Node<Integer> head = Node.makeNode(Integer.valueOf(1),
        Node.makeNode(Integer.valueOf(2), Node.makeNode(Integer.valueOf(3), null)));

    System.out.printf("Input=%s\n", head.toString());
    System.out.printf("Recursive reverse=%s\n", recursiveReverse(head).toString());
  }
}

/**
 * A cool example of how to push the limits on generics in Java
 * @param <T>
 */
class NodeTuple<T extends Node<?>> {
  public final T x;
  public final T y;

  public NodeTuple(T x, T y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    String resolvedX;
    String resolvedY;
    if (this.x == null) {
      resolvedX = "null";
    } else {
      resolvedX = this.x.toString();
    }
    if (this.y == null) {
      resolvedY = "null";
    } else {
      resolvedY = this.y.toString();
    }
    return "Tuple(" + resolvedX + ", " + resolvedY + ")";
  }
}

class Node<T extends Serializable> {
  public Node<T> next;
  public T value;

  public Node(T value, Node<T> next) {
    this.value = value;
    this.next = next;
  }

  public String toString() {
    String nextRep;
    if (next == null) {
      nextRep = "null";
    } else {
      nextRep = this.next.toString();
    }
    return "Node(" + this.value.toString() + ", " + nextRep + ")";
  }

  public static <T extends Serializable> Node<T> makeNode(T value, Node<T> next) {
    return new Node<>(value, next);
  }
}