import java.util.ArrayList;
import java.util.List;

public class HeapSolution {

  private static class Heap {

    private int size = 0;
    private List<Integer> store = new ArrayList<>();

    public Heap() {
    }

    @Override
    public String toString() {
      return "Heap=" + store.subList(0, size).toString() + " size=" + size;
    }

    private int parent(int index) {
      return index / 2;
    }

    private int left(int index) {
      return index * 2;
    }

    private int right(int index) {
      return index * 2 + 1;
    }

    private boolean isLeaf(int index) {
      return left(index) >= size && right(index) >= size;
    }

    private void swap(int i, int j) {
      int temp = store.get(i);
      store.set(i, store.get(j));
      store.set(j, temp);
    }

    private void bubbleUp(int index) {
      int i = index;
      while (store.get(parent(i)) > store.get(i)) {
        swap(parent(i), i);
        i = parent(i);
      }
    }

    private void bubbleDown() {
      int i = 0;
      while (!isLeaf(i)) {
        int j = i;
        if (left(i) < size && store.get(j) > store.get(left(i))) {
          j = left(i);
        }
        if (right(i) < size && store.get(j) > store.get(right(i))) {
          j = right(i);
        }
        if (j == i) {
          break;
        }
        swap(j, i);
        i = j;
      }
    }

    public void insert(int val) {
      int i = size++;
      store.add(val);
      bubbleUp(i);
    }

    public int removeFront() {
      if (size <= 0) {
        throw new RuntimeException("The heap is empty!!");
      }
      size--;
      int result = store.get(0);
      store.set(0, store.get(size));
      bubbleDown();
      return result;
    }
  }

  public static void main(String[] args) {
    Heap heap = new Heap();
    System.out.println(heap);
    heap.insert(2);
    heap.insert(5);
    heap.insert(-1);
    heap.insert(10);
    heap.insert(-2);
    System.out.println(heap);
    System.out.println("front=" + heap.removeFront());
    System.out.println("front=" + heap.removeFront());
    System.out.println("front=" + heap.removeFront());
    System.out.println(heap);
  }
}
