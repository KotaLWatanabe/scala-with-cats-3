import model.Cat
import print.PrintableInstances.given
import print.PrintableSyntax._

object Runner {
  def main(args: Array[String]): Unit = {
    println("print int:")
    4.print
    println("print string:")
    "hello".print
    println("print cat:")
    Cat("mimi", 2, "white").print
  }
}
