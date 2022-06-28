import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueSolution {
  public static void main(String[] args) {
    // Queue
    Queue<Integer> queue = new LinkedList<>();
    queue.add(3);
    queue.add(5);
    queue.add(7);
    System.out.println("Queue=" + queue);
    System.out.println("queue.peek()=" + queue.peek());
    System.out.println("queue.poll()=" + queue.poll());
    System.out.println("queue.poll()=" + queue.poll());
    System.out.println("Queue=" + queue);

    // Deque
    Deque<Integer> deque = new LinkedList<>();
    deque.addLast(3);
    deque.addLast(5);
    deque.addLast(7);
    System.out.println("Deque=" + deque);
    deque.addFirst(1);
    System.out.println("Deque=" + deque);
    System.out.println("deque.peekFirst()=" + deque.peekFirst());
    System.out.println("deque.peekLast()=" + deque.peekLast());
    System.out.println("deque.pollFirst()=" + deque.pollFirst());
    System.out.println("deque.pollLast()=" + deque.pollLast());

    // PriorityQueue
    PriorityQueue<Integer> pqueue = new PriorityQueue<>((l, r) -> Integer.compare(l, r) * -1);
    pqueue.add(2);
    pqueue.add(3);
    pqueue.add(1);
    pqueue.add(-10);
    System.out.println("PriorityQueue=" + pqueue);
    System.out.println("pqueue.peek()=" + pqueue.peek());
    System.out.println("pqueue.poll()=" + pqueue.poll());
    System.out.println("pqueue.poll()=" + pqueue.poll());
    System.out.println("PriorityQueue=" + pqueue);
  }
}
