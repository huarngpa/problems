/**
 * https://leetcode.com/problems/longest-univalue-path/
 */
public class LongestUnivaluePath {

  // Cracking it is very similar to MaximumPathSum problem, just slight variation.
  public int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int longestPath = longestUnivaluePathDown(root.left, root.val) + longestUnivaluePathDown(root.right, root.val);
    return Math.max(longestPath, Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right)));
  }

  public int longestUnivaluePathDown(TreeNode root, int value) {
    if (root == null || root.val != value) {
      return 0;
    }
    return 1 + Math.max(longestUnivaluePathDown(root.left, value), longestUnivaluePathDown(root.right, value));
  }
}
