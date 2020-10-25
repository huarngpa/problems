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
      for (int i = array.size() - 1; i >= 0; i--) {
        siftUp(i, array);
      }
      return array;
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
      int min;
      int l = left(currentIdx);
      int r = right(currentIdx);
      if (l < endIdx && heap.get(l) < heap.get(currentIdx)) {
        min = l;
      } else {
        min = currentIdx;
      }
      if (r < endIdx && heap.get(r) < heap.get(currentIdx)) {
        min = r;
      }
      if (min != currentIdx) {
        swap(min, currentIdx, heap);
        siftDown(min, endIdx, heap);
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
    heap.remove();
    System.out.println(heap);
  }
}