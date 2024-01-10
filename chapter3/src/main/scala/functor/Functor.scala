package functor

trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

object Functor:
  def apply[F[_]](using functor: Functor[F]): Functor[F] = functor

object FunctorSyntax:
  extension [A, F[_]](fa: F[A])
    def map[B](f: A => B)(using functor: Functor[F]): F[B] = functor.map(fa)(f)

object FunctorLaws:
  import FunctorSyntax._
  def identityLaw[F[_], A](fa: F[A])(using functor: Functor[F]): Boolean =
    fa.map(identity) == fa

  def compositionLaw[F[_], A, B, C](fa: F[A], f: A => B, g: B => C)(using
      functor: Functor[F]
  ): Boolean =
    fa.map(f).map(g) == fa.map(f andThen g)
