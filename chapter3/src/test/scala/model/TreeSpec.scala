package model

import cats.syntax.eq.*
import cats.syntax.show.*
import functor.FunctorLaws
import functor.Functor
import model.Tree.*
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class TreeSpec extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks:
  describe("Tree"):
    def treeGenerator: Gen[Tree[Int]] = {
      def createNode: Gen[Tree[Int]] = for {
        rootOpt <- arbitrary[Option[Int]]
        node <- rootOpt match
          case Some(v) => Gen.const(leaf(v))
          case None =>
            createNode.flatMap(left =>
              createNode.map(right => branch(left, right))
            )
      } yield node

      createNode
    }
    def f: Int => Int = _ + 1
    def g: Int => String = _.toString

    it("treeFunctor instance satisfies functor laws."):
      forAll(treeGenerator) { tree =>
        import Tree.given
        FunctorLaws.identityLaw(tree) shouldBe true
        FunctorLaws.compositionLaw(tree, f, g) shouldBe true
      }
