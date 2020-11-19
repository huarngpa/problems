import java.util.*;

public class ConstructBinaryTreeSolution {

  public static class TreeNode {
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

  public static TreeNode buildTreeHelper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder,
      Map<Integer, Integer> inorderMap) {
    if (inStart > inEnd) {
      return null;
    }
    int node = preorder[preStart];
    TreeNode root = new TreeNode(node);
    int inIndex = inorderMap.get(node);
    root.left = buildTreeHelper(preStart + 1, inStart, inIndex - 1, preorder, inorder, inorderMap);
    root.right = buildTreeHelper(preStart + 1 + (inIndex - inStart), inIndex + 1, inEnd, preorder, inorder, inorderMap);
    return root;
  }

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    int n = inorder.length;
    Map<Integer, Integer> inorderMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
      inorderMap.put(inorder[i], i);
    }
    return buildTreeHelper(0, 0, n - 1, preorder, inorder, inorderMap);
  }

  public static void main(String[] args) {

  }
}
