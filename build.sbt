// This is a Scala.js project.
enablePlugins(ScalaJSPlugin)
// Use Node.
scalaJSUseRhino in Global := false

name := "electron-scala"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0"
)
