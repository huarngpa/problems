import java.util.Stack;

public class StackSolution {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(4);
    stack.push(2);
    stack.push(5);
    stack.push(11);
    System.out.println("Stack=" + stack);
    System.out.println("stack.peek()=" + stack.peek());
    System.out.println("stack.pop()=" + stack.pop());
    System.out.println("stack.pop()=" + stack.pop());
    System.out.println("stack.pop()=" + stack.pop());
    System.out.println("Stack=" + stack);
  }
}
