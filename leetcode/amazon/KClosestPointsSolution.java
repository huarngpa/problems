import java.util.*;

/**
 * K Closest Points to Origin
 * 
 * We have a list of points on the plane. Find the K closest points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in.)
 */
public class KClosestPointsSolution {

  public class Point {
    int[] point;
    double dist;

    Point(int[] point, double dist) {
      this.point = point;
      this.dist = dist;
    }

    public String toString() {
      return Double.toString(dist);
    }
  }

  public void swap(List<Point> points, int i, int j) {
    Point temp = points.get(i);
    points.set(i, points.get(j));
    points.set(j, temp);
  }

  public int partition(List<Point> points, int p, int r) {
    Point pivot = points.get(r);
    int i = p - 1;
    for (int j = p; j < r; j++) {
      if (points.get(j).dist <= pivot.dist) {
        i++;
        swap(points, i, j);
      }
    }
    swap(points, i + 1, r);
    return i + 1;
  }

  public void kClosestHelper(List<Point> points, int p, int r, int k) {
    if (p >= r) {
      return;
    }
    int q = partition(points, p, r);
    if (q == k) {
      return;
    }
    if (q < k) {
      kClosestHelper(points, q + 1, r, k);
    } else {
      kClosestHelper(points, p, q - 1, k);
    }
  }

  public int[][] kClosest(int[][] points, int K) {
    List<Point> list = new ArrayList<>();
    for (int[] point : points) {
      double dist = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
      list.add(new Point(point, dist));
    }
    kClosestHelper(list, 0, list.size() - 1, K - 1);
    System.out.println(list);
    int[][] result = new int[K][];
    for (int i = 0; i < K; i++) {
      result[i] = list.get(i).point;
    }
    return result;
  }

  public static void main(String[] args) {

  }
}
