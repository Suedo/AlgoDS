import math.Ordering

object BasicSorts extends App {
  val bs = new BasicSorts()
  val arr = Array(4, 2, 7, 6, 4, 5, 1, 1, 9, 9, 15, 14);
  var li: List[Int] = arr.toList
  val op = bs.mergeSort(li)(Ordering[Int]).mkString(" ")
  println(op)
}

class BasicSorts {

  def swap(a: Array[Int], i: Int, j: Int) = {
    val tmp = a(i)
    a(i) = a(j)
    a(j) = tmp;
  }

  def bubbleSort(arr: Array[Int]): Array[Int] = {
    for (i <- 0 until arr.length - 1) {
      for (j <- 0 until arr.length - 1 - i) {
        if (arr(j) > arr(j + 1)) {
          swap(arr, j, j + 1)
        }
      }
    }
    arr
  }

  def selectionSort(arr: Array[Int]): Array[Int] = {
    for (i <- 0 until arr.length - 1) {
      var minPos = i;
      var localMin = arr(i)
      for (j <- i + 1 until arr.length) {
        if (arr(j) < localMin) {
          localMin = arr(j)
          minPos = j;
        }
      }
      swap(arr, i, minPos)
      println(arr.mkString(" "))
    }
    arr
  }

  def insertionSort(arr: Array[Int]): Array[Int] = {
    for (right <- 1 until arr.length) {
      var left = right - 1
      while (left >= 0) {
        if (arr(left) > arr(left + 1)) swap(arr, left, left + 1)
        left -= 1
      }
      println(arr.mkString("  "))
    }
    arr
  }

  def merge[T](left: List[T], right: List[T])(implicit order: Ordering[T]): List[T] = (left, right) match {
    case (Nil, _) => right
    case (_, Nil) => left
    // left = leftHead :: leftTail
    // right = rightHead :: rightTail
    case (leftHead :: leftTail, rightHead :: rightTail) =>
      if (order.lt(leftHead, rightHead)) leftHead :: merge(leftTail, right)
      else rightHead :: merge(left, rightTail)
  }

  def mergeSort[T](l: List[T])(implicit order: Ordering[T]): List[T] = {
    val mid = l.length / 2
    if (mid == 0) l
    else {
      val (a, b) = l splitAt mid
      val mergeResult = merge(mergeSort(a)(order), mergeSort(b)(order))
      println(mergeResult)
      mergeResult
    }
  }

}


