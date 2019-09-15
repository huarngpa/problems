object Solution {

  def solution(input: List[Int]): List[Int] = {
    val prefixes = input.scanLeft(1)(_ * _).tail
    val suffixes = input.reverse.scanLeft(1)(_ * _).tail.reverse

    def mapfn(i: Int): Int = {
      val prev = i - 1
      val next = i + 1
      var res = 1
      if (prev >= 0) res *= prefixes(prev)
      if (next < input.length) res *= suffixes(next)
      res
    }

    return 0.to(input.length - 1).map(mapfn).toList
  }

  def main(args: Array[String]): Unit = {
    val example = List(1, 2, 3, 4, 5)
    println("example: " + example)
    println("result: " + solution(example))
  }
}