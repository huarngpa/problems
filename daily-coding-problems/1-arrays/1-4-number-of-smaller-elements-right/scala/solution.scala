import scala.collection.Searching._

object Solution {
  def solution(input: List[Int]): List[Int] = {
    val reversed = input.reverse
    var sorted = List[Int]()

    def insert[T](list: List[T], i: Int, value: T) = {
      val (front, back) = list.splitAt(i)
      front ++ List(value) ++ back
    }

    // Walk in reversed, creates sorted list as side-effect and counts
    // elements that are smaller, reverse the results
    return reversed.map(x => {
      val smallerThan = sorted.search(x).insertionPoint
      sorted = insert(sorted, smallerThan, x)
      smallerThan
    }).reverse
  }

  def main(args: Array[String]): Unit = {
    val example = List(3, 4, 9, 6, 1)

    println("example: " + example)
    println("solution: " + solution(example))
  }
}
