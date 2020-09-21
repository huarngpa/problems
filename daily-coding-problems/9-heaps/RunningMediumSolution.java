import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class RunningMediumSolution {
  public static void main(String[] args) {
    List<Integer> inputs = new ArrayList<>();
    inputs.add(Integer.valueOf(2));
    inputs.add(Integer.valueOf(1));
    inputs.add(Integer.valueOf(5));
    inputs.add(Integer.valueOf(7));
    inputs.add(Integer.valueOf(2));
    inputs.add(Integer.valueOf(0));
    inputs.add(Integer.valueOf(5));
    RunningMedium runningMedium = new RunningMedium();
    for (Integer e : inputs) {
      runningMedium.add(e);
      System.out.printf("added=%s getMedium=%s runningMedium=%s\n", e.toString(), runningMedium.getMedium().toString(),
          runningMedium.toString());
    }
    System.out.println("x");
  }
}

class RunningMedium {
  public int size = 0;
  public PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  public PriorityQueue<Integer> minHeap = new PriorityQueue<>();

  RunningMedium() {
  }

  @Override
  public String toString() {
    return "RunningMedium(maxHeap=" + maxHeap.toString() + ", minHeap=" + minHeap.toString() + ")";
  }

  public void add(Integer e) {
    if (size == 0) {
      minHeap.add(e);
      size++;
      return;
    }
    Float medium = getMedium();
    if ((float) e < medium) {
      maxHeap.add(e);
    } else {
      minHeap.add(e);
    }
    size++;
    rebalance();
  }

  public void rebalance() {
    int difference = maxHeap.size() - minHeap.size();
    if (difference <= -2) {
      Integer e = minHeap.poll();
      maxHeap.add(e);
      return;
    }
    if (difference >= 2) {
      Integer e = maxHeap.poll();
      minHeap.add(e);
      return;
    }
  }

  public Float getMedium() {
    if (size == 0) {
      return null;
    }
    if (maxHeap.size() > minHeap.size()) {
      return (float) maxHeap.peek();
    }
    if (maxHeap.size() < minHeap.size()) {
      return (float) minHeap.peek();
    }
    Integer fromMaxHeap = maxHeap.peek();
    Integer fromMinHeap = minHeap.peek();
    return ((float) (fromMaxHeap + fromMinHeap)) / 2;
  }
}