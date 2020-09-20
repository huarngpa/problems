public class CountUnivalSolution {

  public static <T> boolean isUnival(TreeNode<T> root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      // Leaves are unival
      return true;
    }
    boolean isLeftSideUnival = isUnival(root.left);
    boolean isRightSideUnival = isUnival(root.right);
    return isLeftSideUnival && isRightSideUnival && (root.left == null || root.left.data == root.data)
        && (root.right == null || root.right.data == root.data);
  }

  public static <T> int countUnivalTrees(TreeNode<T> root) {
    if (root == null) {
      return 0;
    }
    int count = countUnivalTrees(root.left) + countUnivalTrees(root.right);
    if (isUnival(root)) {
      count += 1;
    }
    return count;
  }

  public static void main(String[] args) {
    // Nodes
    TreeNode<Integer> node1 = new TreeNode<>(Integer.valueOf(0));
    TreeNode<Integer> node2 = new TreeNode<>(Integer.valueOf(0));
    TreeNode<Integer> node3 = new TreeNode<>(Integer.valueOf(0));
    TreeNode<Integer> node4 = new TreeNode<>(Integer.valueOf(1));
    TreeNode<Integer> node5 = new TreeNode<>(Integer.valueOf(1));
    TreeNode<Integer> node6 = new TreeNode<>(Integer.valueOf(1));
    TreeNode<Integer> node7 = new TreeNode<>(Integer.valueOf(1));
    // Structure
    node1.right = node2;
    node2.right = node3;
    node1.left = node4;
    node2.left = node5;
    node5.left = node6;
    node5.right = node7;
    // Prints
    System.out.printf("tree=%s\n", node1.toString());
    System.out.printf("isUnival=%b\n", isUnival(node5));
    System.out.printf("countUnivalTrees=%d\n", countUnivalTrees(node1));
    System.out.printf("countUnivalTreesOptimized=%d\n", (new CountUnivalResult<Integer>(node1)).count);
  }
}

/**
 * Result object that returns the unival count of the tree rooted at root and
 * whether the tree itself is unival.
 */
class CountUnivalResult<T> {
  public int count = 0;
  public boolean isUnival = true;
  public TreeNode<T> root;

  CountUnivalResult(TreeNode<T> root) {
    this.root = root;
    if (root == null) {
      return;
    }
    CountUnivalResult<T> leftResult = new CountUnivalResult<>(root.left);
    CountUnivalResult<T> rightResult = new CountUnivalResult<>(root.right);
    this.count = leftResult.count + rightResult.count;
    if (leftResult.isUnival && rightResult.isUnival && (leftResult.root == null || leftResult.root.data == root.data)
        && (rightResult.root == null || rightResult.root.data == root.data)) {
      this.count += 1;
      this.isUnival = true;
      return;
    }
    this.isUnival = false;
  }
}

class TreeNode<T> {
  public T data;
  public TreeNode<T> left;
  public TreeNode<T> right;

  TreeNode() {
  }

  TreeNode(T data) {
    this.data = data;
  }

  TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    String dataStr;
    if (data == null) {
      dataStr = "null";
    } else {
      dataStr = data.toString();
    }
    String leftNode;
    if (left == null) {
      leftNode = "null";
    } else {
      leftNode = left.toString();
    }
    String rightNode;
    if (right == null) {
      rightNode = "null";
    } else {
      rightNode = right.toString();
    }
    return "TreeNode(data=" + dataStr + ", left=" + leftNode + ", right=" + rightNode + ")";
  }
}
