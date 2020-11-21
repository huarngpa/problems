
/**
 * Serialize and Deserialize Binary Tree
 * 
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 */
class SerializeBinaryTreeSolution {

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