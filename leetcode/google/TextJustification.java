import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/text-justification/
 */
public class TextJustification {

  // This question is pretty much a waste of time, requires you to handle various
  // specific requirements and edge cases.
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<List<String>> lines = new ArrayList<>();
    List<String> line = new ArrayList<>();
    int width = 0;
    for (String word : words) {
      int length = word.length();
      int spaces = line.size() > 0 ? line.size() : 0;
      // Flush the line to lines
      if (width + length + spaces > maxWidth) {
        lines.add(line);
        line = new ArrayList<>();
        width = 0;
      }
      line.add(word);
      width += length;
    }
    // Flush the last line
    if (line.size() > 0) {
      lines.add(line);
    }
    // Make the results
    List<String> result = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      if (i == lines.size() - 1) {
        result.add(leftJustifyLine(lines.get(i), maxWidth));
      } else {
        result.add(justifyLine(lines.get(i), maxWidth));
      }
    }
    return result;
  }

  private String justifyLine(List<String> line, int maxWidth) {
    int currentWidth = line.stream().mapToInt(String::length).sum();
    int rem = maxWidth - currentWidth;
    int between = line.size() - 1;
    int spaces = between > 0 ? rem / between : 0;
    StringBuilder sb = new StringBuilder();
    if (between == 0) {
      return leftJustifyLine(line, maxWidth);
    }
    String spacesString = makeSpacesString(spaces);
    int moreSpaces = rem % between;
    for (String word : line) {
      if (sb.length() == 0) {
        // Do nothing
      } else if (moreSpaces > 0) {
        sb.append(spacesString + " ");
        moreSpaces--;
      } else {
        sb.append(spacesString);
      }
      sb.append(word);
    }
    return sb.toString();
  }

  private String leftJustifyLine(List<String> line, int maxWidth) {
    StringBuilder sb = new StringBuilder();
    for (String word : line) {
      sb.append(sb.length() == 0 ? word : " " + word);
    }
    int rem = maxWidth - sb.length();
    if (rem > 0) {
      char[] spaces = new char[rem];
      Arrays.fill(spaces, ' ');
      sb.append(new String(spaces));
    }
    return sb.toString();
  }

  private String makeSpacesString(int size) {
    char[] spaces = new char[size];
    Arrays.fill(spaces, ' ');
    return new String(spaces);
  }
}
