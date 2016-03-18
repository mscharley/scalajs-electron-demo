package electron

import scala.scalajs.js

@js.native
class Application extends js.Object with Observable {
  def quit(): Unit = js.native
}
