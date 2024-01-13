package model

import codec.Codec
import print.Printable

final case class Box[A](value: A)

object Box:
  import print.PrintableOps.*
  given boxPrintable[A](using p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](_.value)

  given boxCodec[A](using c: Codec[A]): Codec[Box[A]] = c.imap[Box[A]](Box(_), _.value)
    