import model.Cat
import print.Printable
import print.PrintableInstances.given
import print.PrintableSyntax.print

@main def main(): Unit = {
  println("print int:")
  4.print
  println("print string:")
  "hello".print
  println("print cat:")
  Cat("mimi", 2, "white").print
}
