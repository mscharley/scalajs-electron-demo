import scala.scalajs.js

object Node {
  // Export Node's require() function.
  lazy val require: js.Dynamic = js.Dynamic.global.require
}
