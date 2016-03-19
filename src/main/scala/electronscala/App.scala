package electronscala

import electron._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.{literal => JsObject}

@JSExport("electronscala.App")
class App(dirName: String)(implicit electron: Electron) {
  lazy val process = js.Dynamic.global.process

  // Keep a global reference of the window object, if you don't, the window will
  // be closed automatically when the JavaScript object is garbage collected.
  var mainWindow: Option[BrowserWindow] = None

  val createWindow = () => {
    // Create the browser window.
    mainWindow = Some(BrowserWindow(JsObject(width = 800, height = 600)))
    mainWindow foreach { window =>
      // and load the index.html of the app.
      window.loadURL("file://" + dirName + "/index.html")

      // Open the DevTools.
      window.webContents.openDevTools()

      // Emitted when the window is closed.
      // Dereference the window object, usually you would store windows
      // in an array if your app supports multi windows, this is the time
      // when you should delete the corresponding element.
      window.on("closed", () => mainWindow = None);
    }
  }

  @JSExport
  def main() = {
    // Module to control application life.
    val app = electron.app

    // This method will be called when Electron has finished initialization and is ready to create browser windows.
    app.on("ready", createWindow)

    process.platform.asInstanceOf[String] match {
      case "darwin" =>
        // On OS X it is common for applications and their menu bar to stay active until the user quits explicitly
        // with Cmd + Q and re-create a window in the app when the dock icon is clicked and there are no other
        // windows open.
        app.on("activate", () => {
          if (mainWindow.isEmpty) {
            createWindow()
          }
        })

      case _ =>
        // For everyone else, we just die immediately.
        app.on("window-all-closed", () => app.quit())
    }
  }
}
