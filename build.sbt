import java.nio.charset.Charset
import org.scalajs.core.tools.io.{IO => toolsIO}
enablePlugins(ScalaJSPlugin)
// Use Node.
scalaJSUseRhino in Global := false

lazy val ElectronDemo = (project in file(".")).
  settings(
    name := "scalajs-electron-demo",
    version := "1.0.0",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    mainClass in Compile := Some("Demo.App"),
    persistLauncher in Compile := true,
    persistLauncher in Test := false,
    artifactPath in (Compile, fastOptJS) :=
      ((crossTarget in (Compile, fastOptJS)).value /
          ((moduleName in (Compile, fastOptJS)).value + ".js")),
    artifactPath in (Compile, fullOptJS) := (artifactPath in (Compile, fastOptJS)).value,
    packageScalaJSLauncher in Compile <<= Def.task {
      (mainClass in Compile).value map { mainCl =>
        val log = streams.value.log
        val file: sbt.File = (artifactPath in (Compile, packageScalaJSLauncher)).value
        val code = s"""'use strict';
require("source-map-support").install();
require('./${name.value}-jsdeps');
require('./${name.value}');
$mainCl(__dirname, require).main();
"""

        log.info(s"Creating launcher $file")
        IO.write(file, code, Charset.forName("UTF-8"))

        // Attach the name of the main class used, (ab?)using the name key
        Attributed(file)(AttributeMap.empty.put(name.key, mainCl))
      } getOrElse {
        sys.error("Cannot write launcher file, since there is no or multiple mainClasses")
      }
    },

    resolvers += Resolver.sonatypeRepo("public"),
    libraryDependencies ++= Seq(
      "org.scala-js"  %%% "scalajs-dom"      % "0.9.1",
      "be.doeraene"   %%% "scalajs-jquery"   % "0.9.0",
      "com.lihaoyi"   %%% "autowire"         % "0.2.5",
      "com.lihaoyi"   %%% "upickle"          % "0.4.1",
      "com.mscharley" %%% "scalajs-electron" % "0.1.2",
      "com.mscharley" %%% "scalajs-nodejs"   % "0.1.1"
    ),
    dependencyUpdatesExclusions := moduleFilter(organization = "org.eclipse.jetty")
  )
