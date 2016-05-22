package demo

import autowire._
import autowire.electronipc.ElectronIpcWireServer
import nodejs.raw
import upickle.{default => upickle}

import scala.scalajs.js.Dynamic.global

class ApiServer(protected val ipcMain: raw.EventEmitter)
  extends ElectronIpcWireServer[Api, upickle.Reader, upickle.Writer]
{
  override type Request = autowire.Core.Request[String]
  protected val apiImpl = new ApiImpl
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
