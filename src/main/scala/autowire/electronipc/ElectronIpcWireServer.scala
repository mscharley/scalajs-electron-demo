package autowire.electronipc

import scala.language.higherKinds

import electron.ipc
import nodejs.raw

import scala.collection.immutable
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Dynamic

trait ElectronIpcWireServer[ApiType, Reader[_], Writer[_]]
  extends autowire.Server[String, Reader, Writer]
{
  protected val ipcMain: raw.EventEmitter
  protected val apiImpl: ApiType
  protected val router: Router = this.route[ApiType](apiImpl)

  ipcMain.on("autowire") { (event: ipc.Event, returnPath: String, path: js.Array[String], args: js.Dictionary[String]) =>
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
    Dynamic.global.console.log(path)

    // TODO: Request never seems to be routable.
    val request = new Request(immutable.Seq(path: _*), immutable.Map(args.toSeq: _*))
    if (router.isDefinedAt(request)) {
      val reply = router.apply(request)
      reply.onFailure {
        case e =>
        e.printStackTrace()
        event.sender.send(returnPath)
      }

      reply.foreach { v =>
        event.sender.send(returnPath, v)
      }
    }
    else {
      Dynamic.global.console.log(s"Invalid autowire request recieved: $request")
      event.sender.send(returnPath)
    }
  }
}
