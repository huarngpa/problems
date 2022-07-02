import java.util.ArrayList;
import java.util.List;

public class RemoveLeaves {

  private static class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public List<Integer> removeLeaves(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    removeLeavesHelper(root, result);
    return result;
  }

  private void removeLeavesHelper(TreeNode root, List<Integer> result) {
    if (root == null || isLeaf(root)) {
      return;
    }
    // Pre-order traversal
    result.add(root.val);
    removeLeavesHelper(root.left, result);
    removeLeavesHelper(root.right, result);
  }

  private boolean isLeaf(TreeNode root) {
    if (root.left == null && root.right == null) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(4);
    a.left = b;
    a.right = c;
    b.left = d;
    RemoveLeaves removeLeaves = new RemoveLeaves();
    System.out.println(removeLeaves.removeLeaves(a));
  }
}
