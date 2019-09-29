object Solution {
  def maxSubArray(input: Array[Int]): Int = {
    val (max, _) = input.foldLeft[(Int, Int)]((0, 0))((res, num) => {
      var (maxSeen, maxHere) = res
      maxHere += num
      maxHere = math.max(maxHere, 0)
      maxSeen = math.max(maxSeen, maxHere)
      (maxSeen, maxHere)
    })
    return max
  }

  def minSubArray(input: Array[Int]): Int = {
    val (min, _) = input.foldLeft[(Int, Int)]((0, 0))((res, num) => {
      var (minSeen, minHere) = res
      minHere += num
      minHere = math.min(minHere, 0)
      minSeen = math.min(minSeen, minHere)
      (minSeen, minHere)
    })
    return min
  }

  def circularMaxArray(input: Array[Int]): Int = {
    val sum = input.reduceLeft(_ + _)
    val circularSubArray = sum - minSubArray(input)
    return math.max(circularSubArray, maxSubArray(input))
  }

  def main(args: Array[String]): Unit = {
    val ex1 = Array(34, -50, 42, 14, -5, 86)
    val ex2 = Array(-5, -1, -8, -9)

    println("ex1: [" + ex1.mkString(", ") + "]")
    println("ex2: [" + ex2.mkString(", ") + "]")

    println("solution1: " + maxSubArray(ex1))
    println("solution2: " + maxSubArray(ex2))

    println("circular1: " + circularMaxArray(ex1))
    println("circular2: " + circularMaxArray(ex2))
  }
}
