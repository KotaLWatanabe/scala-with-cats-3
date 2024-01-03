import sbt.*

object Dependencies {
  val Test = "test"
  // Versions
  private lazy val catsVersion = "2.10.0"
  private lazy val catsEffectVersion = "3.5.2"

  // Libraries
  private val catsCore = "org.typelevel" %% "cats-core" % catsVersion
  private val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion
  private val scalaTest = "org.scalatest" %% "scalatest" % "3.2.17" % Test
  private val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.17.0" % Test

  // Projects
  val common: Seq[ModuleID] = Seq(scalaTest, scalaCheck)
  val chapter1: Seq[ModuleID] = Seq(catsCore, catsEffect)
}
