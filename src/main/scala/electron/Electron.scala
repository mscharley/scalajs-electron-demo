package electron

import scala.scalajs.js

@js.native
abstract class Electron extends js.Object {
  val app: Application = js.native
  val BrowserWindow: js.Dynamic = js.native
}
