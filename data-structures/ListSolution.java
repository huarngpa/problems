import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListSolution {
  public static void main(String[] args) {
    // LinkedList
    List<Integer> linkedList = new LinkedList<>();
    linkedList.add(1);
    linkedList.add(2);
    linkedList.add(3);
    System.out.println("LinkedList=" + linkedList);
    linkedList.remove(1); // index remove
    System.out.println("LinkedList=" + linkedList);
    System.out.println("linkedList.get(1)=" + linkedList.get(1));
    System.out.println("linkedList.size()=" + linkedList.size());
    System.out.println("linkedList.isEmpty()=" + linkedList.isEmpty());

    // ArrayList
    List<Integer> arrayList = new ArrayList<>();
    arrayList.add(4);
    arrayList.add(3);
    arrayList.add(2);
    arrayList.add(1);
    System.out.println("ArrayList=" + arrayList);
    arrayList.set(0, 11);
    System.out.println("ArrayList=" + arrayList);
    System.out.println("arrayList.subList(1, 2)=" + arrayList.subList(1, 2));
    Collections.sort(arrayList, (l, r) -> Integer.compare(l, r));
    System.out.println("ArrayList=" + arrayList);
  }
}
