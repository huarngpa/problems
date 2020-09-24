public class FindFloorCeilingSolution {

  public static FloorCeilingResult findFloorCeiling(BST<Integer> bst, Integer target) {
    FloorCeilingResult result = new FloorCeilingResult();
    result.calculateFloorCeiling(bst.root, target);
    return result;
  }

  /**
   * Floors are the highest element in the tree less than or equal to an integer.
   * The ceiling is the lowest
   * 
   * @param args
   */
  public static void main(String[] args) {
    BST<Integer> bst = new BST<>();
    bst.add(new TreeNode<Integer>(Integer.valueOf(7)));
    bst.add(new TreeNode<Integer>(Integer.valueOf(5)));
    bst.add(new TreeNode<Integer>(Integer.valueOf(10)));
    bst.add(new TreeNode<Integer>(Integer.valueOf(-1)));
    bst.add(new TreeNode<Integer>(Integer.valueOf(6)));
    bst.add(new TreeNode<Integer>(Integer.valueOf(25)));
    System.out.println(bst.toString());
    System.out.println(findFloorCeiling(bst, Integer.valueOf(10)));
  }

  static class FloorCeilingResult {
    public Integer target;
    public Integer floor;
    public Integer ceiling;

    FloorCeilingResult() {
    }

    @Override
    public String toString() {
      String floorStr;
      String ceilingStr;
      if (floor == null) {
        floorStr = "null";
      } else {
        floorStr = floor.toString();
      }
      if (ceiling == null) {
        ceilingStr = "null";
      } else {
        ceilingStr = ceiling.toString();
      }
      return "(floor=" + floorStr + ", ceiling=" + ceilingStr + ")";
    }

    public void calculateFloorCeiling(TreeNode<Integer> root, Integer target) {
      if (root == null) {
        return;
      }
      if (root.data < target) {
        if (floor == null) {
          floor = root.data;
        } else if (root.data > floor) {
          floor = root.data;
        }
        calculateFloorCeiling(root.left, target);
      } else {
        if (ceiling == null) {
          ceiling = root.data;
        } else if (root.data < ceiling) {
          ceiling = root.data;
        }
        calculateFloorCeiling(root.right, target);
      }
    }
  }
}

class BST<T extends Comparable<T>> {
  public TreeNode<T> root;

  BST() {
  }

  @Override
  public String toString() {
    if (root == null) {
      return "null";
    }
    return root.toString();
  }

  private void addHelper(TreeNode<T> currRoot, TreeNode<T> node) {
    if (currRoot == null || node == null) {
      return;
    }
    if (node.data.compareTo(currRoot.data) < 0) {
      if (currRoot.left == null) {
        currRoot.left = node;
        return;
      } else {
        addHelper(currRoot.left, node);
      }
    } else {
      if (currRoot.right == null) {
        currRoot.right = node;
      } else {
        addHelper(currRoot.right, node);
      }
    }
  }

  public void add(TreeNode<T> node) {
    if (this.root == null) {
      this.root = node;
    } else {
      addHelper(root, node);
    }
  }
}
