package group

import scala.annotation.targetName

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](using monoid: Monoid[A]): Monoid[A] = monoid
}

object MonoidSyntax {
  extension[A] (x: A)
    @targetName("combine")
    def |+|(y: A)(using m: Monoid[A]): A = m.combine(x, y)
}

object MonoidInstances {
  import SemigroupInstances.*

  given intAdditionMonoid: Monoid[Int] with
    override def empty: Int = 0
    override def combine: (Int, Int) => Int = SemigroupInstances.intAdditionSemigroup.combine

  given stringMonoid: Monoid[String] with
    override def empty: String = ""
    override def combine: (String, String) => String = SemigroupInstances.stringSemigroup.combine

  given optionMonoid[A](using m: Monoid[A]): Monoid[Option[A]] with
    override def empty: Option[A] = None
    override def combine: (Option[A], Option[A]) => Option[A] =
      case (None, a) => a
      case (a, None) => a
      case (Some(a1), Some(a2)) => Some(m.combine(a1, a2))
  
  given booleanOrMonoid: Monoid[Boolean] with
    override def empty: Boolean = false
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => b1 || b2

  given booleanAndMonoid: Monoid[Boolean] with
    override def empty: Boolean = true
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => b1 && b2
  
  given booleanXorMonoid: Monoid[Boolean] with
    override def empty: Boolean = false
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => (b1 && !b2) || (!b1 && b2)

  given booleanXnorMonoid: Monoid[Boolean] with
    override def empty: Boolean = true
    override def combine: (Boolean, Boolean) => Boolean = (b1, b2) => (b1 || !b2) && (!b1 || b2)
  
  given setUnionMonoid[A]: Monoid[Set[A]] with
      override def empty: Set[A] = Set.empty
      override def combine: (Set[A], Set[A]) => Set[A] = (s1, s2) => s1 union s2
  
  given setSymDiffMonoid[A]: Monoid[Set[A]] with
      override def empty: Set[A] = Set.empty
      override def combine: (Set[A], Set[A]) => Set[A] = (s1, s2) => (s1 diff s2) union (s2 diff s1)
}

object MonoidLaws {
    import MonoidSyntax.*
    def associativeLaw[A](x: A, y: A, z: A)(using m: Monoid[A]): Boolean =
      (x |+| (y |+| z)) == ((x |+| y) |+| z)

    def identityLaw[A](x: A)(using m: Monoid[A]): Boolean =
      (x |+| m.empty) == x && (m.empty |+| x) == x
}