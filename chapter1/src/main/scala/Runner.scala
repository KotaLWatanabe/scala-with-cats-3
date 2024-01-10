import model.Cat
import print.PrintableInstances.given
import print.PrintableSyntax._
import cats.syntax.show.*
import cats.syntax.eq.*

object Runner:
  def main(args: Array[String]): Unit = {
    println("print int:")
    4.print
    println("print string:")
    "hello".print
    println("print cat:")
    val cat1 = Cat("mimi", 2, "white")
    cat1.print
    val cat2 = Cat("Garfield", 38, "ginger and black")
    println(cat2.show)

    println("cat1 === cat2:")
    println(cat1 === cat2)
    println("cat1 =!= cat2:")
    println(cat1 =!= cat2)

    println("Option(cat1) === Option(cat2):")
    println(Option(cat1) === Option(cat2))
    println("Option(cat1) =!= Option(cat2):")
    println(Option(cat1) =!= Option(cat2))
  }
