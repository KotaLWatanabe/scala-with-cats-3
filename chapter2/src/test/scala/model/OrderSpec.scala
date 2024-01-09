package model

import cats.syntax.eq.*
import cats.syntax.show.*
import group.MonoidLaws
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class OrderSpec extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks {
  describe("Order") {
    val orderGenerator = for {
      totalCost <- arbitrary[Double]
      quantity <- arbitrary[Double]
    } yield Order(totalCost, quantity)

    it("orderMonoid instance satisfies monoid laws.") {
      forAll(orderGenerator, orderGenerator, orderGenerator) { (o1, o2, o3) =>
        MonoidLaws.associativeLaw(o1, o2, o3) shouldBe true
        MonoidLaws.identityLaw(o1) shouldBe true
      }
    }
  }
}
