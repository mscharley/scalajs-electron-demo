package autowire.electronipc

import electron.ipc
import nodejs.raw
import upickle.{default => upickle}

import scala.collection.immutable
import scala.concurrent.ExecutionContext
import scala.language.higherKinds
import scala.scalajs.js
import scala.scalajs.js.Dynamic

trait ElectronIpcWireServer[Reader[_], Writer[_]]
  extends autowire.Server[String, Reader, Writer]
{
  implicit val ec: ExecutionContext = scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
  protected val ipcMain: raw.EventEmitter
  protected val router: Router

  ipcMain.on("autowire") { (event: ipc.Event, returnPath: String, path: js.Array[String], args: js.Dictionary[String]) =>
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
      Dynamic.global.console.log(s"Invalid autowire request received: $request")
      event.sender.send(returnPath)
    }
  }
}
