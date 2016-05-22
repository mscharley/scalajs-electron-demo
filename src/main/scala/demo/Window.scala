package demo

import nodejs.Require
import electron._
import autowire._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => JSGlobal}
import scala.scalajs.js.annotation.JSExport
import scala.concurrent.Future

@JSExport("Demo.Window")
class Window(val require: Require = JSGlobal.require.asInstanceOf[Require])
  extends RendererProcess(require)
{
  import scala.concurrent.ExecutionContext.Implicits.global
  val jQuery = require("jquery").asInstanceOf[org.scalajs.jquery.JQueryStatic]

  val api: ApiClient = new ApiClient(electron.ipcRenderer)
  val pong: Future[String] = api[Api].ping().call()

  pong foreach { v =>
    JSGlobal.console.log(v)
  }

  // global.console.log(electron.ipcRenderer.sendSync("synchronous-message", "ping")); // prints "pong"
  //
  // electron.ipcRenderer.on("asynchronous-reply") { (event: ipc.Event, arg: js.Any) =>
  //   global.console.log(arg); // prints "pong"
  // };
  // electron.ipcRenderer.send("asynchronous-message", "ping");
}
