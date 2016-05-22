package demo

import electron.ipc
import autowire._
import autowire.electronipc.ElectronIpcWireClient
import upickle.{default => upickle}

class ApiClient(protected val ipcRenderer: ipc.IpcRenderer)
  extends ElectronIpcWireClient[upickle.Reader, upickle.Writer]
{
  def write[Result: upickle.Writer](r: Result) = upickle.write(r)
  def read[Result: upickle.Reader](p: String) = upickle.read[Result](p)
}
