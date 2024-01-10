package model

import cats.syntax.show.*
import cats.syntax.eq.*
import model.Cat
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import print.PrintableSyntax.*

class CatSpec extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks:
  describe("Cat"):
    val catGenerator = for {
      name <- arbitrary[String]
      age <- arbitrary[Int]
      color <- arbitrary[String]
    } yield Cat(name, age, color)

    it("catPrintable instance satisfies Printable."):
      forAll(catGenerator) { cat =>
        cat.format shouldBe a[String]
        cat.format shouldBe s"""${cat.name} is a ${cat.age} year-old ${cat.color} cat."""
      }

    it("catShow instance satisfies Show."):
      forAll(catGenerator) { cat =>
        cat.show shouldBe a[String]
      }

    it("catEq instance satisfies Eq."):
      forAll(catGenerator) { cat =>
        cat === cat shouldBe true
        cat === cat.copy(name = "other") shouldBe false
        Option(cat) === Option(cat) shouldBe true
        Option(cat) === Option(cat.copy(name = "other")) shouldBe false
      }
