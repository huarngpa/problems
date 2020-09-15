import java.util.*;

class RevisedSolution {

  public static List<Integer> accumulateProducts(List<Integer> input) {
    List<Integer> result = new ArrayList<>(input);
    for (int i = 0; i < result.size(); i++) {
      if (i == 0) {
        continue;
      } else {
        result.set(i, result.get(i) * result.get(i - 1));
      }
    }
    return result;
  }

  public static List<Integer> solution(List<Integer> input) {
    List<Integer> prefixes = accumulateProducts(input);
    List<Integer> suffixes = new ArrayList<>(input);
    Collections.reverse(suffixes);
    suffixes = accumulateProducts(suffixes);
    Collections.reverse(suffixes);
    System.out.printf("prefixes=%s\n", prefixes.toString());
    System.out.printf("suffixes=%s\n", suffixes.toString());
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      int prev = i - 1;
      int next = i + 1;
      Integer ret = Integer.valueOf(1);
      if (prev >= 0) {
        ret *= prefixes.get(prev);
      }
      if (next < input.size()) {
        ret *= suffixes.get(next);
      }
      result.add(ret);
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> input = new ArrayList<Integer>(
        Arrays.asList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)));
    System.out.printf("input=%s\n", input.toString());
    System.out.printf("solution=%s\n", solution(input).toString());
  }
}