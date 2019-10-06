import java.util.ArrayList;
import java.util.Arrays;

class Solution {

  interface anonFunc {
    int[] makeFreq(String w);
  }

  public static int[] solution(String w, String s) {
    int size = s.length();
    int a = 'a';

    anonFunc func = new anonFunc() {
      public int[] makeFreq(String w) {
        int[] res = new int[26];
        for(int c : w.toLowerCase().toCharArray()) {
          res[c - a]++;
        }
        return res;
      }
    };

    int[] ref = func.makeFreq(w);
    int[] curr = func.makeFreq("");
    ArrayList<Integer> result = new ArrayList<>();

    for(int end = 0; end < size; end++) {
      int start = end - w.length() + 1;

      int endC = s.charAt(end);
      curr[endC - a]++;

      if (Arrays.equals(ref, curr)) {
        result.add(start);
      }

      if (start >= 0) {
        int startC = s.charAt(start);
        curr[startC - a]--;
      }
    }

    return result.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) {
    String w = "ab";
    String s = "abxaba";
    System.out.println("w: " + w);
    System.out.println("s: " + s);

    System.out.println("solution: " + Arrays.toString(solution(w, s)));
  }
}