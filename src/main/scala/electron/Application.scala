package electron

import nodejs.EventEmitter

import scala.scalajs.js

@js.native
abstract class Application extends js.Object with EventEmitter {
  def quit(): Unit = js.native
}
