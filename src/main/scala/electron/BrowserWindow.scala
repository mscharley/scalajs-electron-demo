package electron

import scala.scalajs.js

object BrowserWindow {
  def apply(options: js.Object)(implicit electron: Electron): BrowserWindow =
    js.Dynamic.newInstance(electron.BrowserWindow)(options).asInstanceOf[BrowserWindow]
}

@js.native
abstract class BrowserWindow extends js.Object with Observable {
  def loadURL(url: String): Unit = js.native
  val webContents: js.Dynamic = js.native
}
