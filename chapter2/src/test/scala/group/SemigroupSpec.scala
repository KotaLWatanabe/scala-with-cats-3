package group

import group.SemigroupLaws
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class SemigroupSpec
  extends AnyFunSpec
    with Matchers
    with ScalaCheckPropertyChecks {
  describe("Set Semigroup") {
    val intSetsGen = for {
      i1 <- Gen.listOf(arbitrary[Int])
      i2 <- Gen.listOf(arbitrary[Int])
      i3 <- Gen.listOf(arbitrary[Int])
    } yield (i1.toSet, i2.toSet, i3.toSet)
    val stringSetGen = for {
      s1 <- Gen.listOf(arbitrary[String])
      s2 <- Gen.listOf(arbitrary[String])
      s3 <- Gen.listOf(arbitrary[String])
    } yield (s1.toSet, s2.toSet, s3.toSet)

    it("setInterSectionSemigroup instance satisfies semigroup laws") {
      import group.SemigroupInstances.setInterSectionSemigroup
      forAll(intSetsGen) { (si1, si2, si3) =>
        SemigroupLaws.associativeLaw(si1, si2, si3) shouldBe true
      }
      forAll(stringSetGen) { (ss1, ss2, ss3) =>
        SemigroupLaws.associativeLaw(ss1, ss2, ss3) shouldBe true
      }
    }
  }
}
