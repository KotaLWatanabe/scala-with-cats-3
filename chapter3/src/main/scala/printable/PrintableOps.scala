package printable

import model.Box
import print.Printable

object PrintableOps {
  extension [A](value: Printable[A])
    def contramap[B](fc: B => A): Printable[B] = new Printable[B] {
        override def format: B => String = v => value.format(fc(v))
    }

  given booleanPrintable: Printable[Boolean] with
    override def format: Boolean => String = if (_) "yes" else "no"

  given boxPrintable[A](using p: Printable[A]): Printable[Box[A]] = p.contramap(_.value)
}
