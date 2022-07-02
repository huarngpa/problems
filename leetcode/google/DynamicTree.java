import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DynamicTree<T> {

  private static class TreeNode<T> {
    TreeNode<T> parent;
    T item;

    @Override
    public String toString() {
      return String.format("TreeNode(item=%s)",
          Optional.ofNullable(item).map(Object::toString).orElse(""));
    }
  }

  int capacity;
  TreeNode<T> root = new TreeNode<>();
  List<TreeNode<T>> list = new ArrayList<>();
  Random random = new Random();

  public DynamicTree(int capacity) {
    this.capacity = capacity;
  }

  public int size() {
    return list.size();
  }

  public TreeNode<T> getRoot() {
    return root;
  }

  public TreeNode<T> createNode(TreeNode<T> parent, T item) {
    if (list.size() + 1 > capacity) {
      throw new RuntimeException("The dynamic tree is at capacity!");
    }
    TreeNode<T> node = new TreeNode<>();
    node.item = item;
    node.parent = parent;
    list.add(node);
    return node;
  }

  public TreeNode<T> getRandomNode() {
    return list.get(random.nextInt(list.size()));
  }

  @Override
  public String toString() {
    return String.format("DynamicTree(list=%s)", list);
  }

  public static void main(String[] args) {
    DynamicTree<Integer> tree = new DynamicTree<>(10);
    TreeNode<Integer> a = tree.createNode(tree.getRoot(), Integer.valueOf(10));
    TreeNode<Integer> b = tree.createNode(a, Integer.valueOf(20));
    TreeNode<Integer> c = tree.createNode(a, Integer.valueOf(30));
    System.out.println(tree);
    System.out.println(tree.getRandomNode());
    System.out.println(tree.getRandomNode());
    System.out.println(tree.getRandomNode());
    System.out.println(tree.getRandomNode());
    System.out.println(tree.getRandomNode());
    System.out.println(tree.getRandomNode());
  }
}
