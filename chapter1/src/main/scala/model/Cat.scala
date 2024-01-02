package model

import print.Printable
import print.PrintableSyntax.format
import print.PrintableInstances.given

final case class Cat(name: String, age: Int, color: String)

object Cat {
  given catPrintable: Printable[Cat] with
    def format: Cat => String = cat =>
      s"""${cat.name.format} is a ${cat.age.format} year-old ${cat.color.format} cat."""
}
