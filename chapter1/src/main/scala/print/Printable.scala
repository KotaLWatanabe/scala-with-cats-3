package print

trait Printable[A]:
  def format: A => String

object Printable:
  def format[A](value: A)(using printable: Printable[A]): String =
    printable.format(value)

  def print[A](value: A)(using printable: Printable[A]): Unit =
    println(format(value))

object PrintableInstances:
  given stringPrintable: Printable[String] with
    def format: String => String = identity

  given intPrintable: Printable[Int] with
    def format: Int => String = _.toString

object PrintableSyntax:
  extension [A](value: A)
    def format(using printable: Printable[A]): String =
      printable.format(value)

    def print(using printable: Printable[A]): Unit =
      println(format)
