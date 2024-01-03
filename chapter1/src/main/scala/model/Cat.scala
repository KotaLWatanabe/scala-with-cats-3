package model

import cats.Eq
import print.Printable
import print.PrintableInstances.given
import print.PrintableSyntax.*

final case class Cat(name: String, age: Int, color: String)

object Cat {
  given catPrintable: Printable[Cat] with
    def format: Cat => String = cat =>
      s"""${cat.name.format} is a ${cat.age.format} year-old ${cat.color.format} cat."""
      
  given catEq: Eq[Cat] with
    def eqv(cat1: Cat, cat2: Cat): Boolean =
      cat1.name == cat2.name &&
      cat1.age == cat2.age &&
      cat1.color == cat2.color
  
}
