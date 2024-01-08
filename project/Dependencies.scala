import sbt.*

object Dependencies {
  object Version {
    val cats = "2.10.0"
    val catsEffect = "3.5.2"
    val scalaTest = "3.2.17"
  }

  // Libraries
  private val catsCore = "org.typelevel" %% "cats-core" % Version.cats
  private val catsEffect = "org.typelevel" %% "cats-effect" % Version.catsEffect
  private val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % Test
  private val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.17.0" % Test
  private val scalaTestCheck =  "org.scalatestplus" %% "scalacheck-1-17" % "3.2.15.0" % Test

  // Projects
  val common: Seq[ModuleID] = Seq(catsCore, scalaTest, scalaCheck, scalaTestCheck)
  val chapter1: Seq[ModuleID] = Seq(catsEffect)
}
