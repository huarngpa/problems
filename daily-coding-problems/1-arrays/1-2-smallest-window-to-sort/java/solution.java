import java.util.Arrays;

class Answer {
  int low;
  int high;

  Answer() {
    this.low = -1;
    this.high = -1;
  }

  Answer(int low, int high) {
    this.low = low;
    this.high = high;
  }

  /**
   * @param high the high to set
   */
  public void setHigh(int high) {
    this.high = high;
  }

  /**
   * @param low the low to set
   */
  public void setLow(int low) {
    this.low = low;
  }

  /**
   * @return the high
   */
  public int getHigh() {
    return high;
  }

  /**
   * @return the low
   */
  public int getLow() {
    return low;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("(");  str.append(Integer.toString(this.low));
    str.append(", "); str.append(Integer.toString(this.high));
    str.append(")");
    return str.toString();
  }
}

class Solution {
  public static Answer solution(int[] input) {
    int size = input.length;
    Answer res = new Answer();
    if (size == 0) {
      return res;
    }

    int low, high, min, max;
    low = -1;
    high = -1;
    min = input[size - 1];
    max = input[0];

    for (int i = 0; i < size; i++) {
      int num = input[i];
      if (num < max) {
        high = i;
      }
      max = Math.max(num, max);
    }

    for (int i = size - 1; i >= 0; i--) {
      int num = input[i];
      if (num > min) {
        low = i;
      }
      min = Math.min(num, min);
    }

    res.setHigh(high);
    res.setLow(low);

    return res;
  }

  public static void main(String[] args) {
    int[] example = new int[]{3, 7, 5, 6, 9};
    System.out.println("example: " + Arrays.toString(example));

    Answer res = solution(example);
    System.out.println("solution: " + res.toString());
  }
}
