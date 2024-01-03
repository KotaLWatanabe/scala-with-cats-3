package monoid

import scala.annotation.targetName

trait Semigroup[A]:
  def combine: (A, A) => A

trait Monoid[A] extends Semigroup[A]:
  def empty: A

object MonoidSyntax {
  extension[A] (x: A)
    @targetName("combine")
    def |+|(y: A)(using m: Monoid[A]): A = m.combine(x, y)
}

object MonoidInstances {
  given intMonoid: Monoid[Int] with
    override def empty: Int = 0
    override def combine: (Int, Int) => Int = (i1, i2) => i1 + i2

  given stringMonoid: Monoid[String] with
    override def empty: String = ""
    override def combine: (String, String) => String = (s1, s2) => s1 + s2

  given optionMonoid[A](using m: Monoid[A]): Monoid[Option[A]] with
    override def empty: Option[A] = None
    override def combine: (Option[A], Option[A]) => Option[A] =
      case (None, a) => a
      case (a, None) => a
      case (Some(a1), Some(a2)) => Some(m.combine(a1, a2))

  given booleanMonoid1: Monoid[Boolean] with
    override def empty: Boolean = false
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => b1 || b2

  given booleanMonoid2: Monoid[Boolean] with
    override def empty: Boolean = true
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => b1 && b2

  given setMonoid[A]: Monoid[Set[A]] with
      override def empty: Set[A] = Set.empty
      override def combine: (Set[A], Set[A]) => Set[A] = (s1, s2) => s1 ++ s2
}

object MonoidLaws {
    import MonoidSyntax.*
    def associativeLaw[A](x: A, y: A, z: A)(using m: Monoid[A]): Boolean =
      x.|+|(y.|+|(z)) == x.|+|(y).|+|(z)

    def identityLaw[A](x: A)(using m: Monoid[A]): Boolean =
      x.|+|(m.empty) == x && m.empty.|+|(x) == x
}