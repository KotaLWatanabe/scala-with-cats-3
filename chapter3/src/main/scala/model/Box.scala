package model

import print.Printable

final case class Box[A](value: A)

object Box:
  import print.PrintableOps.*
  given boxPrintable[A](using p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)
