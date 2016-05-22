package demo

import electron.ipc
import autowire._
import autowire.electron.ElectronIpcWireClient
import upickle.{default => upickle}

import scala.scalajs.js.Dynamic
import scala.concurrent.Future

class ApiClient(protected val ipcRenderer: ipc.IpcRenderer)
  extends autowire.Client[String, upickle.Reader, upickle.Writer]
  with ElectronIpcWireClient
{
  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)

  override def doCall(req: Request) = {
    import scala.concurrent.ExecutionContext.Implicits.global
    Dynamic.global.console.log(req.toString())
    Future.successful(write("pong"))
  }
}
