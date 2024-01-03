package adder

import monoid.Monoid

object SuperAdder {
  def add[A](items: List[A])(using m: Monoid[A]): A =
    items.foldLeft(m.empty)(m.combine)
}
