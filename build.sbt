val scala3Version = "3.3.1"

lazy val commonSettings = Seq(
  scalaVersion := scala3Version,
  version := "0.1.0-SNAPSHOT"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-with-cats-3",
    commonSettings
  )
  .aggregate(chapter1)

lazy val chapter1 = project
  .in(file("chapter1"))
  .settings(
    name := "chapter1",
    commonSettings,
    libraryDependencies ++= Dependencies.chapter1
  )
