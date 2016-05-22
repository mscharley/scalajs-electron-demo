package demo

import autowire._
import autowire.electron.ElectronIpcWireServer
import nodejs.raw
import upickle.{default => upickle}

import scala.scalajs.js.Dynamic.global

class ApiServer(protected val ipcMain: raw.EventEmitter)
  extends autowire.Server[String, upickle.Reader, upickle.Writer]
  with ElectronIpcWireServer
{
  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)
}

// API for cross-process communication.
object ApiImpl extends Api {
  def ping() = {
    global.console.log("ping")
    "pong"
  }
}
