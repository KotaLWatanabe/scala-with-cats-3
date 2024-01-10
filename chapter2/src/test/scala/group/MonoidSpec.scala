package group

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import group.MonoidLaws
import org.scalacheck.Gen

class MonoidSpec extends AnyFunSpec with Matchers with ScalaCheckPropertyChecks:
  describe("Boolean Monoid"):
    it("booleanAndMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.booleanAndMonoid
      forAll { (b1: Boolean, b2: Boolean, b3: Boolean) =>
        MonoidLaws.associativeLaw(b1, b2, b3) shouldBe true
        MonoidLaws.identityLaw(b1) shouldBe true
      }

    it("booleanOrMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.booleanOrMonoid
      forAll { (b1: Boolean, b2: Boolean, b3: Boolean) =>
        MonoidLaws.associativeLaw(b1, b2, b3) shouldBe true
        MonoidLaws.identityLaw(b1) shouldBe true
      }

    it("booleanXorMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.booleanXorMonoid
      forAll { (b1: Boolean, b2: Boolean, b3: Boolean) =>
        MonoidLaws.associativeLaw(b1, b2, b3) shouldBe true
        MonoidLaws.identityLaw(b1) shouldBe true
      }

    it("booleanXnorMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.booleanXnorMonoid
      forAll { (b1: Boolean, b2: Boolean, b3: Boolean) =>
        MonoidLaws.associativeLaw(b1, b2, b3) shouldBe true
        MonoidLaws.identityLaw(b1) shouldBe true
      }

  describe("Set Monoid"):
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

    it("setUnionMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.setUnionMonoid
      forAll(intSetsGen) { (si1, si2, si3) =>
        MonoidLaws.associativeLaw(si1, si2, si3) shouldBe true
        MonoidLaws.identityLaw(si1) shouldBe true
      }
      forAll(stringSetGen) { (ss1, ss2, ss3) =>
        MonoidLaws.associativeLaw(ss1, ss2, ss3) shouldBe true
        MonoidLaws.identityLaw(ss1) shouldBe true
      }

    it("setSymDiffMonoid instance satisfies monoid laws"):
      import group.MonoidInstances.setSymDiffMonoid
      forAll(intSetsGen) { (si1, si2, si3) =>
        MonoidLaws.associativeLaw(si1, si2, si3) shouldBe true
        MonoidLaws.identityLaw(si1) shouldBe true
      }
      forAll(stringSetGen) { (ss1, ss2, ss3) =>
        MonoidLaws.associativeLaw(ss1, ss2, ss3) shouldBe true
        MonoidLaws.identityLaw(ss1) shouldBe true
      }
