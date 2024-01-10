package model

import cats.syntax.eq.*
import cats.syntax.show.*
import model.Box
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import print.PrintableInstances.given
import print.PrintableSyntax.*

class BoxSpec extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks:
  describe("Box"):
    def boxGenerator[A](using a: Arbitrary[A]) = arbitrary[A].map(Box.apply)

    it("boxPrintable instance satisfies Printable."):
      import model.Box.given
      forAll(boxGenerator[Int], boxGenerator[String]) { (intBox, strBox) =>
        intBox.format shouldBe a[String]
        strBox.format shouldBe a[String]
      }
