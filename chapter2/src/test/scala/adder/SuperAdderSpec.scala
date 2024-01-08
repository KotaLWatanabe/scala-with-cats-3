package adder

import group.MonoidLaws
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalacheck.Prop.forAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import group.MonoidInstances.{intAdditionMonoid, stringMonoid, optionMonoid}

class SuperAdderSpec
  extends AnyFunSpec
    with Matchers
    with ScalaCheckPropertyChecks {
  describe("SuperAdder") {
    it("add method combine list elements all") {
      forAll(Gen.listOf(arbitrary[Int])) { intList =>
        SuperAdder.add(intList) shouldBe intList.sum
      }
      forAll(Gen.listOf(arbitrary[String])) { strList =>
        SuperAdder.add(strList) shouldBe strList.mkString
      }
      forAll(Gen.listOf(arbitrary[Option[Int]])) { optIntList =>
        val allNone = optIntList.forall(_.isEmpty)
        val total = optIntList.filter(_.isDefined).map(_.get).sum
        SuperAdder.add(optIntList) shouldBe { if(allNone) None else Some(total) }
      }
      forAll(Gen.listOf(arbitrary[Option[String]])) { optStrList =>
        val allNone = optStrList.forall(_.isEmpty)
        val total = optStrList.filter(_.isDefined).map(_.get).mkString
        SuperAdder.add(optStrList) shouldBe {
          if (allNone) None else Some(total)
        }
      }
    }
  }
}
