package model

import functor.Functor

import scala.annotation.tailrec

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

object Tree:
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)

  given treeFunctor: Functor[Tree] with
    override def map[A, B](value: Tree[A])(f: A => B): Tree[B] = value match
      case Leaf(v)             => leaf(f(v))
      case Branch(left, right) => branch(map(left)(f), map(right)(f))

object TreeSyntax:
  import Tree.given

  extension [A](value: Tree[A])
    def map[B](f: A => B)(using functor: Functor[Tree]): Tree[B] =
      functor.map(value)(f)
