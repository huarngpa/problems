import java.util.ArrayList;
import java.util.List;

class MinHeapSolution {

  static class MinHeap {
    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> array) {
      heap = buildHeap(array);
    }

    @Override
    public String toString() {
      return heap.toString();
    }

    public List<Integer> buildHeap(List<Integer> array) {
      for (int e : array) {
        insert(e);
      }
      return heap;
    }

    private int parent(int i) {
      return (i - 1) / 2;
    }

    private int left(int i) {
      return 2 * i + 1;
    }

    private int right(int i) {
      return 2 * i + 2;
    }

    private void swap(int i, int j, List<Integer> heap) {
      Integer temp = heap.get(i);
      heap.set(i, heap.get(j));
      heap.set(j, temp);
    }

    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
      if (currentIdx >= endIdx) {
        return;
      }
      int minIdx = currentIdx;
      int leftIdx = left(currentIdx);
      int rightIdx = right(currentIdx);
      if (leftIdx < endIdx && heap.get(leftIdx) < heap.get(minIdx)) {
        minIdx = leftIdx;
      }
      if (rightIdx < endIdx && heap.get(rightIdx) < heap.get(minIdx)) {
        minIdx = rightIdx;
      }
      if (minIdx != currentIdx) {
        swap(minIdx, currentIdx, heap);
        siftDown(minIdx, endIdx, heap);
      }
    }

    public void siftUp(int currentIdx, List<Integer> heap) {
      int p = parent(currentIdx);
      if (p >= 0 && heap.get(currentIdx) < heap.get(p)) {
        swap(currentIdx, p, heap);
        siftUp(p, heap);
      }
    }

    public int peek() {
      return heap.get(0);
    }

    public int remove() {
      int result = heap.get(0);
      heap.set(0, heap.get(heap.size() - 1));
      heap.remove(heap.size() - 1);
      siftDown(0, heap.size(), heap);
      return result;
    }

    public void insert(int value) {
      heap.add(value);
      siftUp(heap.size() - 1, heap);
    }

    public boolean hasHeapProperties() {
      int size = heap.size();
      for (int i = 0; i < size; i++) {
        int l = left(i);
        int r = right(i);
        if (l < size && heap.get(l) < heap.get(i)) {
          return false;
        }
        if (r < size && heap.get(r) < heap.get(i)) {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {
    List<Integer> array = new ArrayList<>();
    Integer[] transfer = new Integer[] { 48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41 };
    for (Integer e : transfer) {
      array.add(e);
    }
    System.out.println(array);
    MinHeap heap = new MinHeap(array);
    System.out.println(heap);
    System.out.println("Has heap properties: " + heap.hasHeapProperties());
    heap.remove();
    System.out.println(heap);
    System.out.println("Has heap properties: " + heap.hasHeapProperties());
  }
}