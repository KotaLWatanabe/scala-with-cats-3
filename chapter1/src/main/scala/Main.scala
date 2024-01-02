import cats.effect.{ExitCode, IO, IOApp}
import model.Cat
import print.Printable
import print.PrintableInstances.given
import print.PrintableSyntax.print

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = IO {
    println("print int:")
    4.print
    println("print string:")
    "hello".print
    println("print cat:")
    Cat("mimi", 2, "white").print
    ExitCode.Success
  }
}
