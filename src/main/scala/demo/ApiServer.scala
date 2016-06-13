package demo

import autowire.electronipc.ElectronIpcWireServer
import nodejs.raw
import upickle.{default => upickle}

import scala.scalajs.js.Dynamic.global

class ApiServer(protected val ipcMain: raw.EventEmitter)
  extends ElectronIpcWireServer[upickle.Reader, upickle.Writer]
{
  protected val apiImpl = new ApiImpl
  protected val router = this.route[Api](apiImpl)

  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)
}

// API for cross-process communication.
class ApiImpl extends Api {
  def ping() = {
    global.console.log("ping")
    "pong"
  }
}
