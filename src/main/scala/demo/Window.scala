package electron.quickstart

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport

@JSExport("Demo.Window")
class Window {
  val require = global.require.asInstanceOf[js.Function1[String, js.Any]]
  val jQuery = require("jquery").asInstanceOf[org.scalajs.jquery.JQueryStatic]

  global.console.log(jQuery)
}
