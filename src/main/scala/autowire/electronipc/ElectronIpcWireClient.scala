package autowire.electronipc

import scala.language.higherKinds

import electron.ipc

import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Dynamic
import scala.concurrent._

trait ElectronIpcWireClient[Reader[_], Writer[_]]
  extends autowire.Client[String, Reader, Writer]
{
  protected val ipcRenderer: ipc.IpcRenderer

  def doCall(req: Request) = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
    val returnEvent = s"autowire.${req.path.last}"
    val promise = Promise[String]()
    ipcRenderer.once(returnEvent) { (ev: ipc.Event, value: String) =>
      Dynamic.global.console.log(value)
      // TODO: Handle undef as a failure from the server.
      promise.success(value)
    }
    ipcRenderer.send("autowire", returnEvent, req.path.toJSArray, req.args.toJSDictionary)

    promise.future
  }
}
