public class BinaryTreeMaxSumSolution {

  public class TreeNode {
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

  public class Max {
    int max;

    Max() {
      max = Integer.MIN_VALUE;
    }
  }

  public int maxPathSum(TreeNode root) {
    Max max = new Max();
    maxPathSumHelper(root, max);
    return max.max;
  }

  public int maxPathSumHelper(TreeNode root, Max max) {
    if (root == null) {
      return 0;
    }
    int left = Math.max(0, maxPathSumHelper(root.left, max));
    int right = Math.max(0, maxPathSumHelper(root.right, max));
    max.max = Math.max(max.max, root.val + left + right);
    return Math.max(root.val + left, root.val + right);
  }

  public static void main(String[] args) {

  }
}
