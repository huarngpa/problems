import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/robot-room-cleaner/
 */
public class RobotRoomCleaner {

  interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current
    // cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
  }

  private static int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

  /**
   * The trick to clearing this problem is understanding you children nodes in DFS
   * and figuring out how we must "backtrack". Note that we use the concept of an
   * orientation to figure out what direction we should move and return.
   */
  public void cleanRoom(Robot robot) {
    cleanRoomHelper(robot, 0, 0, 0, new HashSet<>());
  }

  /**
   * We keep track of our positions, but use the concept of orientation to decide
   * how we should DFS.
   */
  private void cleanRoomHelper(Robot robot, int x, int y, int orientation, Set<String> visited) {
    // Clean the room
    robot.clean();
    visited.add(x + " " + y);
    // Explore each direction (child node)
    for (int curr = orientation; curr < orientation + 4; curr++) {
      int next = curr % 4; // next orientation
      int[] direction = directions[next];
      int nx = x + direction[0];
      int ny = y + direction[1];
      if (!visited.contains(nx + " " + ny) && robot.move()) {
        cleanRoomHelper(robot, nx, ny, next, visited);
      }
      robot.turnRight();
    }
    // Backtrack step
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }
}
