/**
 * https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumBST {

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public int rangeSumBST(TreeNode root, int low, int high) {
    if (root == null) {
      return 0;
    }
    int rootVal = root.val;
    int rootRangeSum = isInRange(rootVal, low, high) ? rootVal : 0;
    int leftRangeSum = rootVal < low ? 0 : rangeSumBST(root.left, low, high);
    int rightRangeSum = rootVal > high ? 0 : rangeSumBST(root.right, low, high);
    return rootRangeSum + leftRangeSum + rightRangeSum;
  }

  private boolean isInRange(int val, int low, int high) {
    if (val < low || val > high) {
      return false;
    }
    return true;
  }
}
