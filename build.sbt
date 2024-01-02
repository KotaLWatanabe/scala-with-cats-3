import Dependencies.*

scalaVersion := "3.3.1"
name := "ScalaWithCats"
version := "0.1.0-SNAPSHOT"

lazy val root = project
  .in(file("."))
  .aggregate(chapter1)

lazy val chapter1 = project
  .in(file("chapter1"))
  .settings(
    name := "chapter1",
    libraryDependencies ++= backendDeps
  )
