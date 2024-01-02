import sbt.*

object Dependencies {
  // Versions
  lazy val catsVersion = "2.10.0"
  lazy val catsEffectVersion = "3.5.2"

  // Libraries
  val catsCore = "org.typelevel" %% "cats-core" % catsVersion
  val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion

  // Projects
  val backendDeps: Seq[ModuleID] = Seq(catsCore, catsEffect)
}
