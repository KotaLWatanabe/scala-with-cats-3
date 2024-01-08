package group

import scala.annotation.targetName

trait Semigroup[A] {
  def combine: (A, A) => A
}

object Semigroup {
  def apply[A](using semigroup: Semigroup[A]): Semigroup[A] = semigroup
}

object SemigroupSyntax {
  extension[A] (x: A)
    @targetName("combine")
    def |+|(y: A)(using s: Semigroup[A]): A = s.combine(x, y)
}

object SemigroupInstances {
  given intAdditionSemigroup: Semigroup[Int] with
    override def combine: (Int, Int) => Int = (i1, i2) => i1 + i2
    
  given stringSemigroup: Semigroup[String] with
    override def combine: (String, String) => String = (s1, s2) => s1 ++ s2
  
  given setInterSectionSemigroup[A]: Semigroup[Set[A]] with
    override def combine: (Set[A], Set[A]) => Set[A] = (s1, s2) => s1 intersect s2
}

object SemigroupLaws {
  import SemigroupSyntax.*
  def associativeLaw[A](x: A, y: A, z: A)(using m: Semigroup[A]): Boolean =
    (x |+| (y |+| z)) == ((x |+| y) |+| z)
}