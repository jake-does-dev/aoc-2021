ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "aoc-2021",
    idePackagePrefix := Some("org.jakedoes.dev")
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"