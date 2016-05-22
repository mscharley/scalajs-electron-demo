package demo

import nodejs.Require
import electron._
import autowire._
import org.scalajs.jquery.JQueryStatic

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport
import scala.concurrent.Future

@JSExport("Demo.Window")
class Window(val require: Require = global.require.asInstanceOf[Require])
  extends RendererProcess(require)
{
  import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
  val jQuery = require("jquery").asInstanceOf[JQueryStatic]

  val api: ApiClient = new ApiClient(electron.ipcRenderer)
  val pong: Future[String] = api[Api].ping().call()

  pong foreach { v =>
    global.console.log(v)
  }

  pong recover {
    case e => global.console.error(s"${e.getClass.getName}: ${e.getMessage}")
  }
}
