/**
 * Given a binary tree, find the maximum path sum. The path may start and end at
 * any node in the tree.
 */
public class MaximumPathSumSolution {

  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   * int val;
   * TreeNode left;
   * TreeNode right;
   * TreeNode() {}
   * TreeNode(int val) { this.val = val; }
   * TreeNode(int val, TreeNode left, TreeNode right) {
   * this.val = val;
   * this.left = left;
   * this.right = right;
   * }
   * }
   */

  public int maxPathSum(TreeNode root) {
    /*
     * The trick to understanding this problem is to understand the constraints of
     * the problem.
     * A path must not traverse a node twice which means it goes up the tree and
     * down the tree.
     * So what this means is that you can reduce the problem down to finding maximum
     * path down from each side of a binary tree rooted at some node.
     * 
     * The problem you may run into is that you ought to use a guardian, because
     * zero will break negative number edge cases.
     */
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    int currentNodeMaxPath = maxPathDown(root.left) + root.val + maxPathDown(root.right);
    return Math.max(maxPathSum(root.left), Math.max(maxPathSum(root.right), currentNodeMaxPath));
  }

  private int maxPathDown(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftSum = root.val + maxPathDown(root.left);
    int rightSum = root.val + maxPathDown(root.right);
    return Math.max(0, Math.max(root.val, Math.max(leftSum, rightSum)));
  }
}