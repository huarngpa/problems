/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {

  /*
   * Note that you could also do this with two-pass DP... How?
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] result = new int[nums.length - k + 1];
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(nums.length, (left, right) -> {
      return Integer.compare(nums[left], nums[right]) * -1;
    });
    // Pre-process the start
    for (int i = 0; i < k - 1; i++) {
      pq.add(i);
    }
    for (int end = k - 1; end < nums.length; end++) {
      int start = end - k + 1;
      pq.add(end);
      while (!(pq.peek() >= start && pq.peek() <= end)) {
        pq.poll();
      }
      result[start] = nums[pq.peek()];
    }
    return result;
  }
}
