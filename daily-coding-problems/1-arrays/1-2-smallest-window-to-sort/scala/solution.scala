object Solution {
  def solution(input: List[Int]): (Int, Int) = {
    var (lower, upper) = (-1, -1)
    val size = input.length

    if (size == 0) {
      return (lower, upper)
    }

    var max = input(0)

    for (i <- 0 to size - 1) {
      val current = input(i)
      if (current < max) {
        upper = i
      }
      max = math.max(current, max)
    }

    val reversed = input.reverse
    var min = reversed(0)

    for (i <- 0 to size - 1) {
      val current = reversed(i)
      if (current > min) {
        lower = size - i - 1
      }
      min = math.min(current, min)
    }
    
    return (lower, upper)
  }

  def main(args: Array[String]): Unit = {
    val example = List(3, 7, 5, 6, 9);
    println("example: " + example)

    println("solution: " + solution(example))
  }
}