import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OncallRotations {

  private static class Rotation {
    String name;
    int start;
    int end;

    public Rotation(String name, int start, int end) {
      this.name = name;
      this.start = start;
      this.end = end;
    }
  }

  public static class ResultRow {
    int start;
    int end;
    Set<String> names;

    public ResultRow(int start, int end, Set<String> names) {
      this.start = start;
      this.end = end;
      this.names = names;
    }

    @Override
    public String toString() {
      return String.format("ResultRow(%d, %d, %s)", start, end, names);
    }
  }

  int size = 0;
  Map<Integer, Set<String>> table = new HashMap<>();

  public OncallRotations() {
  }

  public void add(Rotation rotation) {
    for (int i = rotation.start; i < rotation.end; i++) {
      table.computeIfAbsent(i, (ignored) -> new HashSet<>()).add(rotation.name);
    }
    size = Math.max(size, rotation.end);
  }

  public Set<String> getCurrentlyOncall(int time) {
    return table.getOrDefault(time, new HashSet<>());
  }

  public List<ResultRow> getOncallTable() {
    List<ResultRow> result = new ArrayList<>();
    int i = 0;
    int j = i;
    while (j <= size) {
      Set<String> previous = getCurrentlyOncall(i);
      Set<String> current = getCurrentlyOncall(j);
      if (current.size() != previous.size()) {
        if (previous.size() != 0) {
          result.add(new ResultRow(i, j, previous));
        }
        i = j;
      }
      j++;
    }
    return result;
  }

  @Override
  public String toString() {
    return String.format("OncallRotations(%s)", table);
  }

  public static void main(String[] args) {
    OncallRotations oncallRotations = new OncallRotations();
    oncallRotations.add(new Rotation("abby", 1, 10));
    oncallRotations.add(new Rotation("ben", 5, 7));
    oncallRotations.add(new Rotation("carla", 6, 12));
    oncallRotations.add(new Rotation("david", 15, 17));
    System.out.println(oncallRotations);
    System.out.println(oncallRotations.getCurrentlyOncall(9));
    System.out.println(oncallRotations.getOncallTable());
  }
}
