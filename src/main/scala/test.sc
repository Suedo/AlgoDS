sealed trait Tree {
  def printTree: Unit
  def sum: Int
}
final case class Node(l: Tree, r: Tree) extends Tree {
  def sum = l.sum + r.sum
  def printTree = {
    l.printTree
    r.printTree
  }
}
final case class Leaf(value: Int) extends Tree {
  def sum = value
  def printTree = print(s" $value")
}

object TreeOps {
  def sum(tree: Tree): Int = {
    tree match {
      case Leaf(value) => value
      case Node(l, r) => l.sum + r.sum
    }
  }
  def print(tree: Tree) = {
    tree match {
      case l: Leaf => l.printTree
      case n: Node => n.printTree
    }
  }
}

val three = Leaf(3)
val two = Leaf(2)
val root = Node(two, three)

TreeOps.sum(root)
//TreeOps.print(root)

root.printTree
root.sum


//import scala.annotation.tailrec
//
//@tailrec
//def recsum(xs: List[Int], total: Int = 0): Int = {
//  xs match {
//    case Nil => total
//    case hd :: tl => recsum(tl, hd + total)
//  }
//}
//
//recsum(List(1,2,3))
//
//
////def func (str: String, pos: Int): String = {
////  str.split(" ")(pos)
////}
////
////func("amar nam som", 2)
////
////
////val func2 = (a: String, pos: Int) => {
////  a.split(" ")(pos)
////}
////
////func2("amar nam gogo", 2)
////
////
////
////val func3: (String, Int) => String = (a, pos) => {
////  a.split(" ")(pos)
////}
////
////func3("sob moho mamya", 2)