public class DoublyLinkedListSolution {

  static class DoublyLinkedList {
    public Node head;
    public Node tail;

    private boolean isEmpty() {
      if (head == null && tail == null) {
        return true;
      }
      return false;
    }

    public void setHead(Node node) {
      // Write your code here.
      remove(node);
      if (isEmpty()) {
        // head is null
        head = node;
        tail = node;
      } else {
        // head is not null
        node.next = head;
        head.prev = node;
        head = node;
      }
    }

    public void setTail(Node node) {
      // Write your code here.
      remove(node);
      if (isEmpty()) {
        // tail is null
        head = node;
        tail = node;
      } else {
        // tail is not null
        node.prev = tail;
        tail.next = node;
        tail = node;
      }
    }

    public void insertBefore(Node node, Node nodeToInsert) {
      // Write your code here.
      remove(nodeToInsert);
      if (head == node) {
        // node is head
        setHead(nodeToInsert);
      } else {
        // node is anything else
        Node prev = node.prev;
        prev.next = nodeToInsert;
        node.prev = nodeToInsert;
        nodeToInsert.prev = prev;
        nodeToInsert.next = node;
      }
    }

    public void insertAfter(Node node, Node nodeToInsert) {
      // Write your code here.
      remove(nodeToInsert);
      if (tail == node) {
        // node is tail
        setTail(nodeToInsert);
      } else {
        // node is anything else
        Node next = node.next;
        next.prev = nodeToInsert;
        node.next = nodeToInsert;
        nodeToInsert.prev = node;
        nodeToInsert.next = next;
      }
    }

    public void insertAtPosition(int position, Node nodeToInsert) {
      // Write your code here.
      if (isEmpty()) {
        setHead(nodeToInsert);
        return;
      }
      int i = 1;
      Node node = head;
      while (i < position && node != null) {
        node = node.next;
        i++;
      }
      if (node == null) {
        setTail(nodeToInsert);
      } else {
        insertBefore(node, nodeToInsert);
      }
    }

    public void removeNodesWithValue(int value) {
      // Write your code here.
      Node current = head;
      while (current != null) {
        Node next = current.next;
        if (current.value == value) {
          remove(current);
        }
        current = next;
      }
    }

    public void remove(Node node) {
      // Write your code here.
      if (node == null) {
        return;
      }
      if (node == head) {
        head = head.next;
      }
      if (node == tail) {
        tail = tail.prev;
      }
      Node prev = node.prev;
      Node next = node.next;
      node.prev = null;
      node.next = null;
      if (prev != null)
        prev.next = next;
      if (next != null)
        next.prev = prev;
    }

    public boolean containsNodeWithValue(int value) {
      // Write your code here.
      Node current = head;
      while (current != null) {
        if (current.value == value) {
          return true;
        }
        current = current.next;
      }
      return false;
    }
  }

  // Do not edit the class below.
  static class Node {
    public int value;
    public Node prev;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    System.out.println("hello");
  }
}
