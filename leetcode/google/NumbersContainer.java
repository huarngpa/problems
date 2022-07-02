import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class NumbersContainer {

  private static class Index implements Comparable<Index> {
    int index;

    public Index(int index) {
      this.index = index;
    }

    @Override
    public int compareTo(Index other) {
      return Integer.compare(index, other.index);
    }

    @Override
    public boolean equals(Object other) {
      if (other == null || !(other instanceof Index)) {
        return false;
      }
      return index == ((Index) other).index;
    }

    @Override
    public String toString() {
      return String.format("Index(%d)", index);
    }

    @Override
    public int hashCode() {
      return Integer.valueOf(index).hashCode();
    }
  }

  private static class MinStack<T extends Comparable<T>> {

    Stack<T> stack = new Stack<>();
    Stack<T> min = new Stack<>();

    public MinStack() {
    }

    public void push(T item) {
      stack.push(item);
      if (min.isEmpty() || min.peek().compareTo(item) > 0) {
        min.push(item);
      }
    }

    public T pop() {
      if (stack.isEmpty()) {
        return null;
      }
      T item = stack.pop();
      if (min.peek().equals(item)) {
        min.pop();
      }
      return item;
    }

    public T getMin() {
      if (min.isEmpty()) {
        return null;
      }
      return min.peek();
    }

    public void remove(T item) {
      stack.remove(item);
      min.remove(item);
    }

    @Override
    public String toString() {
      return String.format("Stack(stack=%s, min=%s)", stack, min);
    }
  }

  Map<Integer, MinStack<Index>> minStacks = new HashMap<>();
  Map<Index, Integer> map = new HashMap<>();

  public NumbersContainer() {
  }

  public void insertOrReplace(int index, int number) {
    Index idx = new Index(index);
    if (map.containsKey(idx)) {
      int num = map.get(idx);
      map.remove(idx);
      minStacks.get(num).remove(idx);
    }
    map.put(idx, number);
    minStacks.computeIfAbsent(number, (num) -> new MinStack<>()).push(idx);
  }

  public int findSmallestIndex(int number) {
    return Optional.ofNullable(minStacks.get(number))
        .map(MinStack::getMin)
        .map(idx -> idx.index)
        .orElse(-1);
  }

  public void deleteNumber(int number) {
    if (!minStacks.containsKey(number)) {
      return;
    }
    MinStack<Index> minStack = minStacks.get(number);
    minStacks.remove(number);
    while (!minStack.stack.isEmpty()) {
      Index index = minStack.stack.pop();
      map.remove(index);
    }
  }

  @Override
  public String toString() {
    return String.format("NumbersContainer(map=%s, minStacks=%s)", map, minStacks);
  }

  public static void main(String[] args) {
    NumbersContainer container = new NumbersContainer();
    container.insertOrReplace(0, 3);
    container.insertOrReplace(1, 4);
    container.insertOrReplace(2, 5);
    System.out.println(container);
    container.insertOrReplace(0, 5);
    System.out.println(container);
    container.deleteNumber(5);
    System.out.println(container);
  }
}
