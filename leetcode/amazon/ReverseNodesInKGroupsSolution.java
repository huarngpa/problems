
/**
 * Reverse Nodes in k-Group
 * 
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes, in
 * the end, should remain as it is.
 * 
 * Follow up:
 * 
 * Could you solve the problem in O(1) extra memory space? You may not alter the
 * values in the list's nodes, only nodes itself may be changed.
 * 
 */
public class ReverseNodesInKGroupsSolution {

  public class ListNode {
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

  public ListNode reverseKGroup(ListNode head, int k) {
    // 1. Pointers
    ListNode result = null;
    ListNode prev = null;
    ListNode current = head;
    ListNode kth = findK(current, k);
    // 2. Reverse the groups
    while (current != null && kth != null) {
      if (prev != null) {
        prev.next = null;
      }
      ListNode next = kth.next;
      kth.next = null;
      // Reverse
      ListNode newHead = reverse(current);
      // Patch
      if (prev != null) {
        prev.next = newHead;
      }
      current.next = next;
      // Conditional for result
      if (result == null) {
        result = newHead;
      }
      // Continue
      prev = current;
      current = next;
      kth = findK(current, k);
    }
    return result;
  }

  public ListNode findK(ListNode head, int k) {
    ListNode current = head;
    int rem = k - 1;
    while (current != null && rem > 0) {
      current = current.next;
      rem--;
    }
    return current;
  }

  public ListNode reverse(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode tail = head.next;
    if (tail == null) {
      return head;
    }
    ListNode result = reverse(tail);
    tail.next = head;
    head.next = null;
    return result;
  }

  public static void main(String[] args) {

  }
}
