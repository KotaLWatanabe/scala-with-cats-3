package print

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import print.PrintableInstances.given
import print.PrintableSyntax.*

class PrintableSpec
    extends AnyFunSpec
    with Matchers
    with ScalaCheckPropertyChecks:
  describe("Printable"):
    it("intPrintable instance satisfies Printable."):
      forAll { (i: Int) =>
        i.format shouldBe a[String]
      }

    it("stringPrintable instance satisfies Printable."):
      forAll { (s: String) =>
        s.format shouldBe a[String]
      }
