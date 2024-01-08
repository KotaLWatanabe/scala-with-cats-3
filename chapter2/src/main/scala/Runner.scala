import adder.SuperAdder
import group.MonoidSyntax.*

object Runner {
  def main(args: Array[String]): Unit = {
    val set1 = Set(1, 2, 3)
    val set2 = Set(2, 3, 4)
    {
      import group.MonoidInstances.setUnionMonoid
      println("union set result")
      println(s"$set1 combine $set2: ${set1 |+| set2}")
    }
    {
      import group.MonoidInstances.setSymDiffMonoid
      println("symmetric difference set result")
      println(s"$set1 combine $set2: ${set1 |+| set2}")
    }
    {
      import group.MonoidInstances.{intAdditionMonoid, optionMonoid}
      println("super adder result")
      val total =  SuperAdder.add(List(Some(1), None, Some(3)))
      println(s"total of List(Some(1), None, Some(3)): $total")
    }
  }
}
