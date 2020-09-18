import java.util.Stack;

class MaxStackSolution {
  /**
   * Implement a stack with the following methods:
   * <p>
   * <ul>
   * <li>push(val) pushes val onto a stack</li>
   * <li>pop returns the topmost element of the stack</li>
   * <li>max returns the maximum of the stack</li>
   * </ul>
   * </p>
   * 
   * All operations should run in constant time
   * 
   * @param args
   */
  public static void main(String[] args) {
    MaxStack<Integer> stack = new MaxStack<>();
    stack.push(Integer.valueOf(5));
    stack.push(Integer.valueOf(2));
    stack.push(Integer.valueOf(8));
    stack.push(Integer.valueOf(1));
    System.out.printf("%s\n", stack.toString());
    stack.pop();
    stack.pop();
    System.out.printf("%s\n", stack.toString());
  }
}

class MaxStack<T extends Comparable<T>> {
  public Stack<T> stack = new Stack<>();
  public Stack<T> maxStack = new Stack<>();

  MaxStack() {
  }

  public String toString() {
    String max;
    if (maxStack.isEmpty()) {
      max = "null";
    } else {
      max = maxStack.peek().toString();
    }
    return "MaxStack(stack=" + stack.toString() + ", max=" + max + ")";
  }

  public void push(T val) {
    stack.push(val);
    if (maxStack.empty() || val.compareTo(maxStack.peek()) > 0) {
      maxStack.push(val);
    }
  }

  public T pop() {
    T fromStack = stack.pop();
    if (maxStack.peek().equals(fromStack)) {
      maxStack.pop();
    }
    return fromStack;
  }

  public T max() {
    return maxStack.peek();
  }
}