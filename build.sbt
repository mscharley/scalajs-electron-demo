lazy val electronScala = (project in file(".")).
  settings(
    // Use Node.
    scalaJSUseRhino in Global := false,

    name := "scalajs-electron-quick-start",
    version := "1.0.0",
    scalaVersion := "2.11.8",

    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0"
    )
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(scalaJSNode, scalaJSElectron)

lazy val scalaJSNode = (project in file("scalajs-node")).
  settings(
    // Use Node.
    scalaJSUseRhino in Global := false,

    name := "scalajs-node",
    version := "1.0.0",
    scalaVersion := "2.11.8"
  )
  .enablePlugins(ScalaJSPlugin)

lazy val scalaJSElectron = (project in file("scalajs-electron")).
  settings(
    // Use Node.
    scalaJSUseRhino in Global := false,

    name := "scalajs-electron",
    version := "1.0.0",
    scalaVersion := "2.11.8"
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(scalaJSNode)
