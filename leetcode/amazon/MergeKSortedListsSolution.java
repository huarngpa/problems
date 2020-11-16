import java.util.*;

public class MergeKSortedListsSolution {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null) {
      return null;
    }
    Queue<ListNode> heap = new PriorityQueue<ListNode>(32, (l, r) -> l.val - r.val);
    for (ListNode list : lists) {
      if (list != null) {
        heap.add(list);
      }
    }
    ListNode result = null;
    ListNode current = result;
    while (heap.peek() != null) {
      ListNode min = heap.remove();
      // Add the remaining list back onto the heap
      ListNode next = min.next;
      if (next != null) {
        heap.add(next);
      }
      // Process the min (adding it to the running result)
      min.next = null;
      if (result == null) {
        result = min;
        current = min;
      } else {
        current.next = min;
        current = current.next;
      }

    }
    return result;
  }

  public static void main(String[] args) {

  }
}
