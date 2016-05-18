// Use Node.
scalaJSUseRhino in Global := false

lazy val electronScala = (project in file(".")).
  settings(
    name := "scalajs-electron-quick-start",
    version := "1.0.0",
    scalaVersion := "2.11.8",

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "com.mscharley" %%% "scalajs-electron" % "0.1.0"
    )
  ).enablePlugins(ScalaJSPlugin)
