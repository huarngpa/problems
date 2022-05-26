/**
 * https://leetcode.com/problems/add-two-numbers/submissions/
 */
public class AddTwoNumbers {

  private static class ListNode {
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

  // Be careful about the condition for when to loop.
  // Note you can do this with ORs as well.
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode top = l1;
    ListNode bottom = l2;
    ListNode head = null;
    ListNode current = null;
    boolean hasCarry = false;
    while (!(top == null && bottom == null && !hasCarry)) {
      int topVal = top != null ? top.val : 0;
      int bottomVal = bottom != null ? bottom.val : 0;
      int carryVal = hasCarry ? 1 : 0;
      int total = topVal + bottomVal + carryVal;
      ListNode next = new ListNode(total % 10);
      if (current == null) {
        head = next;
        current = head;
      } else {
        current.next = next;
        current = current.next;
      }
      top = top != null ? top.next : null;
      bottom = bottom != null ? bottom.next : null;
      hasCarry = total >= 10 ? true : false;
    }
    return head;
  }
}
