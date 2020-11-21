class SerializeBinaryTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  // Encodes a tree to a single string.
  public static String serialize(TreeNode root) {
    StringBuilder serializer = new StringBuilder();
    serializeHelper(root, serializer);
    return serializer.toString();
  }

  public static void serializeHelper(TreeNode root, StringBuilder serializer) {
    String node;
    if (root == null) {
      node = "X";
    } else {
      node = Integer.toString(root.val);
    }
    if (serializer.length() > 0) {
      serializer.append("," + node);
    } else {
      serializer.append(node);
    }
    if (root == null) {
      return;
    }
    serializeHelper(root.left, serializer);
    serializeHelper(root.right, serializer);
  }

  public static class Index {
    int index;

    Index(int index) {
      this.index = index;
    }
  }

  // Decodes your encoded data to tree.
  public static TreeNode deserialize(String data) {
    Index index = new Index(0);
    String[] nodes = data.split(",");
    return deserializeHelper(nodes, index);
  }

  public static TreeNode deserializeHelper(String[] nodes, Index index) {
    if (nodes[index.index].equals("X")) {
      index.index++;
      return null;
    }
    TreeNode root = new TreeNode(Integer.valueOf(nodes[index.index]));
    index.index++;
    root.left = deserializeHelper(nodes, index);
    root.right = deserializeHelper(nodes, index);
    return root;
  }

  public static void main(String[] args) {

  }
}