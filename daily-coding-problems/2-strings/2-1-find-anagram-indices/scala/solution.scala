import scala.collection.mutable

object Solution {
  def solution(w: String, s: String): List[Int] = {
    val a = 'a'.toInt
    val size = s.length

    def makeMapping(w: String): Array[Int] = {
      var res = new Array[Int](26)
      w.foreach(c => {
        res(c.toInt - a) += 1
      })
      return res
    }

    val ref = makeMapping(w)
    var curr = makeMapping("")
    var result = new mutable.ListBuffer[Int]()

    for (end <- 0 to (size - 1)) {
      val endC = s.charAt(end).toInt
      val start = end - w.length + 1

      curr(endC - a) += 1

      if (ref.sameElements(curr)) {
        result += start
      }

      if (start >= 0) {
        val startC = s.charAt(start).toInt
        curr(startC - a) -= 1
      }
    }

    return result.toList
  }

  def main(args: Array[String]): Unit = {
    val w = "ab"
    val s = "abxaba"
    println("w: " + w)
    println("s: " + s)

    println("solution: " + solution(w, s))
  }
}
