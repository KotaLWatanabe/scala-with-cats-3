import model.{Box, Tree}
import model.TreeSyntax.*
import print.PrintableInstances.given
import print.PrintableSyntax.*
import codec.CodecInstances.given
import codec.CodecSyntax.*

object Runner {
  def main(args: Array[String]): Unit = {
    println(s"Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2) is ${Tree
        .branch(Tree.leaf(10), Tree.leaf(20))
        .map(_ * 2)}")
    Box(10).print
    Box("Hello").print
    println(123.4.encode)
    println("123.4".decode[Double])
    println(Box(123.4).encode)
    println("123.4".decode[Box[Double]])
  }
}
