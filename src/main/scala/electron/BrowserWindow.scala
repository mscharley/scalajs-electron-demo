package electron

import scala.scalajs.js

object BrowserWindow {
  def apply(options: js.Dynamic)(implicit electron: js.Dynamic): js.Dynamic =
    js.Dynamic.newInstance(electron.BrowserWindow)(options)
}
