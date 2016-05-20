package electron.quickstart

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport

@JSExport("ElectronQuickStart.Window")
class Window {
  global.document.getElementsByTagName("BODY").asInstanceOf[js.Array[js.Dynamic]].apply(0).style = "background: #eee;"
}
