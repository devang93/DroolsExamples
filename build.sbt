import Dependencies._

lazy val commonSettings = Seq(
  name := "DroolsPOC",
  version := "1.0.0",
  scalaVersion := "2.10.6"
)

lazy val root = (project in file("./"))
  .settings(commonSettings:_*)
  .settings(resolvers ++= resolutionRepos)
  .settings(libraryDependencies ++= Seq(
    Libraries.droolsLib, Libraries.droolsKnw, Libraries.droolsComp,
    Libraries.sparkCore,
    Libraries.kryo
  ))
    