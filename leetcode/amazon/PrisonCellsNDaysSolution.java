import java.util.*;

/**
 * Prison Cells After N Days
 * 
 * There are 8 prison cells in a row, and each cell is either occupied or
 * vacant.
 * 
 * Each day, whether the cell is occupied or vacant changes according to the
 * following rules: If a cell has two adjacent neighbors that are both occupied
 * or both vacant, then the cell becomes occupied. Otherwise, it becomes vacant.
 * 
 * (Note that because the prison is a row, the first and the last cells in the
 * row can't have two adjacent neighbors.)
 * 
 * We describe the current state of the prison in the following way: cells[i] ==
 * 1 if the i-th cell is occupied, else cells[i] == 0.
 * 
 * Given the initial state of the prison, return the state of the prison after N
 * days (and N such changes described above.)
 */
public class PrisonCellsNDaysSolution {

  public static int[] prisonAfterNDays(int[] cells, int N) {
    Set<String> keys = new HashSet<>();
    int len = 0;
    boolean cycle = false;
    for (int i = 1; i <= N; i++) {
      int[] nextCells = nextDay(cells);
      String key = Arrays.toString(nextCells);
      // System.out.println(key);
      if (keys.contains(key)) {
        cycle = true;
        break;
      }
      len++;
      keys.add(key);
      cells = nextCells;
    }
    if (cycle) {
      N = N % len;
      return prisonAfterNDays(cells, N);
    }
    return cells;
  }

  public static int[] nextDay(int[] cells) {
    int[] nextCells = new int[cells.length];
    for (int i = 1; i < cells.length - 1; i++) {
      int left = cells[i - 1];
      int right = cells[i + 1];
      if (left == right) {
        nextCells[i] = 1;
      } else {
        nextCells[i] = 0;
      }
    }
    nextCells[0] = nextCells[cells.length - 1] = 0;
    return nextCells;
  }

  public static void main(String[] args) {
    int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
    System.out.println(Arrays.toString(prisonAfterNDays(cells, 100000000)));
  }
}
