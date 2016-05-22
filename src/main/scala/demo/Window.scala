package electron.quickstart

import nodejs.Require
import electron._
import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.annotation.JSExport

@JSExport("Demo.Window")
class Window(val require: Require = global.require.asInstanceOf[Require])
  extends electron.RendererProcess(require)
{
  val jQuery = require("jquery").asInstanceOf[org.scalajs.jquery.JQueryStatic]

  global.console.log(electron.ipcRenderer.sendSync("synchronous-message", "ping")); // prints "pong"

  electron.ipcRenderer.on("asynchronous-reply") { (event: ipc.Event, arg: js.Any) =>
    global.console.log(arg); // prints "pong"
  };
  electron.ipcRenderer.send("asynchronous-message", "ping");
}
