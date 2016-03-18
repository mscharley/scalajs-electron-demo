package electron

import scala.scalajs.js

@js.native
trait Observable extends js.Any {
  def on(event: String, callback: js.Function): Unit = js.native
}
