import model.Tree
import model.TreeSyntax.*

object Runner {
  def main(args: Array[String]): Unit = {
    println(s"Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2) is ${Tree
        .branch(Tree.leaf(10), Tree.leaf(20))
        .map(_ * 2)}")
  }
}
